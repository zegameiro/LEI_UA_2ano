import shutil
import pika
import json
import torch
import os
import sys
import base64
import random
import time

from multiprocessing import Process
from demucs.apply import apply_model
from demucs.pretrained import get_model
from demucs.audio import AudioFile, save_audio


class Worker:
    def __init__(self, id):
        """Initialize the worker"""
        self.connection = None
        self.channel = None
        self.id = id
        self.worker_folder = f'{self.id}_worker'
        os.makedirs(self.worker_folder, exist_ok=True)

        self.jobs = {}
            # Key -> worker_id
            # Value -> [job_id1, job_id2, ...]

        self.jobs_informations = {}
            # Key -> job_id
            # Value -> {"size": size, "time": time, "music_id": music_id, "track_id": track_id}

        self.jobs[self.id] = []

    def run(self):
        """Connect to RabbitMQ"""
        self.connection = pika.BlockingConnection(pika.ConnectionParameters(host='172.17.0.2', port=5672))
        self.channel = self.connection.channel()
        self.channel.queue_declare(queue='music_queue', durable=True)
        self.channel.queue_declare(queue='processed_music_queue', durable=True)
        
        # Clear the music_queue before consuming new messages
        self.clear_queue()

        print('Connected to RabbitMQ')
        self.consume()

    def consume(self):
        """Consume the data from RabbitMQ"""
        while True:
            self.channel.basic_consume(queue='music_queue', on_message_callback=self.callback, auto_ack=False)
            self.consumer_tag = self.channel.basic_consume(queue='music_queue', on_message_callback=self.callback, auto_ack=False)
            print('[*] Waiting for messages...')
            self.channel.start_consuming()

    def delete_sound_files(self):
        # Define the folder where sound files are stored on the worker side
        sound_files_folder = 'worker_musics'
    
        for filename in os.listdir(sound_files_folder):
            file_path = os.path.join(sound_files_folder, filename)
            try:
                if os.path.isfile(file_path) or os.path.islink(file_path):
                    os.unlink(file_path)
                elif os.path.isdir(file_path):
                    shutil.rmtree(file_path)
            except Exception as e:
                print(f'Failed to delete {file_path}. Reason: {e}')

    def stop_worker(self):
        print("Stopping worker...")
        self.channel.basic_cancel(self.consumer_tag)
        self.connection.close()


    def callback(self, ch, method, properties, body):
        """Callback function"""
        try:
            body = json.loads(body)

            if body == None:
                return

            if body['method'] == 'delete_files':
                print("Received delete_files message, deleting sound files...")
                self.delete_sound_files()
                return

            if body['method'] == 'stop':
                print("Received stop message, stopping worker...")
                self.stop_worker()
                return

            if body['method'] == 'process':
                music_data = body['music_data']
                music_data = base64.b64decode(music_data)
                
                mp3_file = body['file_name']
                mp3_file = f'{self.worker_folder}/{mp3_file}'
                music_id = body['music_id']
                total_chunks = body['total_chunks']
                chunk_id = body['chunk_id']

                with open(mp3_file, 'wb') as f:
                    f.write(music_data)

                size = os.path.getsize(mp3_file)
                job_id = random.randint(1, 10000)
                print("Created job id: ", str(job_id))

                information = {
                    "size": size,
                    "time": None,
                    "music_id": music_id,
                    "track_id": chunk_id
                }

                if job_id not in self.jobs[self.id]:
                    self.jobs[self.id].append(job_id)

                self.jobs_informations[job_id] = information
                print("Added job information " + str(information) + " to job " + str(job_id))

                self.process_music(mp3_file, music_id, total_chunks, job_id) 

            elif body['method'] == 'list jobs':
                jobs_ids = []
                print("Jobs: ", str(self.jobs[self.id]))
                for job in self.jobs[self.id]:
                    jobs_ids.append(job)

                print("Jobs ids: ", str(jobs_ids))

                job_info = []

                for job_id in self.jobs_informations:
                    info = {"job_id": job_id, "job_information": self.jobs_informations[job_id]}
                    
                    if info not in job_info:
                        job_info.append(info)


                reply = {"method": "reply list jobs", "jobs_ids": jobs_ids, "worker_id": self.id, "jobs_informations": job_info}

                reply = json.dumps(reply)
                self.channel.basic_publish(exchange='', routing_key="processed_music_queue", body=reply)
                print("Worker ", self.id, " sent reply list jobs")
                
            elif body['method'] == 'cancel':
                self.stop_worker()
                return
        except Exception as e:
            print("Error processing message: ", str(e))
            ch.basic_ack(delivery_tag=method.delivery_tag, requeue=True)
        

    def process_music(self, music_file, music_id, total_chunks, job_id):
        """Process the music using dmucs algorithm"""

        start_time = time.time()


        torch.set_num_threads(1)

        # get the model
        model = get_model(name='htdemucs')
        model.cpu()
        model.eval()

        # load the audio file
        wav = AudioFile(music_file).read(streams=0, samplerate=model.samplerate, channels=model.audio_channels)
        ref = wav.mean(0)
        wav = (wav - ref.mean()) / ref.std()
        
        # apply the model
        sources = apply_model(model, wav[None], device='cpu', progress=True, num_workers=1)[0]
        sources = sources * ref.std() + ref.mean()

        # Get the file name
        file_name = music_file.split('.')[0]

        # store the model
        for source, name in zip(sources, model.sources):
            stem = f'{file_name}_{name}.wav'
            save_audio(source, str(stem),samplerate=model.samplerate)
            
            with open(stem, 'rb') as f:
                music_data = f.read()
                music_data = base64.b64encode(music_data).decode('utf-8') 

                fName = stem.split('/')[1].split('.')[0]
                reply = {"method": "music chunk processed", "file_name": fName, "music_id": music_id, "music_data": music_data, "total_chunks": total_chunks}
                reply = json.dumps(reply)

                self.channel.basic_publish(exchange='', routing_key="processed_music_queue", body=reply)
                print("Sent reply to RabbitMQ")
            
            # if len(os.listdir(self.worker_folder)) == (total_chunks * 4):
                
            #     # Delete all the files in the worker directory
            #     for file in os.listdir(self.worker_folder):
            #         os.remove(os.path.join(self.worker_folder, file))
            #     response = {"method": "concluded", "music_id": music_id}
            #     response = json.dumps(response)
            #     self.channel.basic_publish(exchange='', routing_key="processed_music_queue", body=response)
        
        end_time = time.time()

        total_time = end_time - start_time
        # Round the total time to 2 decimal places
        total_time = round(total_time, 2)
        print("Worker took " + str(total_time) + " seconds to execute the job " + str(job_id))

        # Save the execution time of the job
        self.jobs_informations[job_id]['time'] = total_time

    def clear_queue(self):
        """Clear the music_queue."""
        while True:
            method_frame, _, _ = self.channel.basic_get(queue='music_queue')
            if method_frame:
                self.channel.basic_ack(delivery_tag=method_frame.delivery_tag)
            else:
                break
        
    def stop_worker(self):
        """Stop the worker from working"""
        pass

    def __str__(self) -> str:
        pass

NUM_WORKERS = 4

def monitor_workers(processes, workers):
    while True:
        for i, process in enumerate(processes):
            if not process.is_alive():
                print(f"Worker {workers[i].id} morreu. Reiniciando...")
                new_process = Process(target=workers[i].run)
                new_process.start()
                processes[i] = new_process
        time.sleep(5)  # Verifica a cada 5 segundos


def main():
    """Main function"""
    workers = [Worker(random.randint(0, 10000)) for _ in range(NUM_WORKERS)]
    print(f"{NUM_WORKERS} workers created")

    # Start a process for each worker
    processes = [Process(target=worker.run) for worker in workers]
    
    for process in processes:
        process.start()

    # Monitora os processos dos workers e reinicia os que foram encerrados
    monitor_workers(processes, workers)

    # Wait for all processes to finish
    for process in processes:
        process.join()
    

if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print('Interrupted')
        try:
            sys.exit(0)
        except SystemExit:
            os._exit(0)

  
