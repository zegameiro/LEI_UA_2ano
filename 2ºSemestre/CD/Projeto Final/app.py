import time
from uuid import uuid4
from flask import Flask, abort, request, jsonify, send_from_directory
from tinytag import TinyTag
from pydub import AudioSegment
from flask_cors import CORS
import base64
import json
import shutil
import os
import pika
import re
import threading

from flask import make_response
import random



app = Flask(__name__)
CORS(app)


# Instruments ID
TRACK_DRUMS = 1
TRACK_BASS = 2
TRACK_VOICE = 3
TRACK_OTHERS = 4

# Number of workers
NUM_WORKERS = 4


# Data structure's
musics = {}
    # key -> music_id
    # value -> metadata


musics_files = {}
    # key -> music_id
    # value -> file_path


chunks_files = {}
    # key -> music_id
    # value -> [(chunk_id, chunk_path)]


total_jobs = {}
    # key -> worker_id
    # value -> [job_id1, job_id2, ...]


total_jobs_informations = {}
    # key -> job_id
    # value -> {"size": size, "time": time, "music_id": music_id, "track_id": track_id}


music_track_requests = {}
    # key -> music_id
    # value -> list of instruments

music_status = {}
    # key -> music_id
    # value -> status

processed_music_data = None

all_workers_done = threading.Event()

connection = None
channel = None


@app.route('/')
def hello_world():  
    return 'Its All Good Man!'


@app.route('/music', methods=['GET', 'POST'])
def music():
    if request.method == 'POST':
        # Get music file and save it in a temporary directory in memory
        mp3_file = request.files['file']

        if mp3_file is None:
            return jsonify({"error": "No file provided"}), 400
        
        os.makedirs("musics", exist_ok=True)

        # Generate a music ID
        music_id = random.randint(0, 1000000)

        while music_id in musics:
            music_id = random.randint(0, 1000000)

        file_path = os.path.join("musics/" + str(music_id) + ".mp3")
        mp3_file.save(file_path)
            
        musics_files[music_id] = mp3_file.filename.rsplit(".mp3", 1)[0]

        print("Music ID before:", music_id)

        # Save the music file path
        musics_files[music_id] = file_path

        chunks_files[music_id] = []


        split_music_file(file_path, music_id)

        # Get music metadata from audio file
        audio = TinyTag.get(file_path)

        metadata = {
                    "music_id": music_id,
                    "name": audio.title,
                    "band": audio.artist,
                    "tracks": [
                        {"track_id": TRACK_DRUMS, "name": "Drums"},
                        {"track_id": TRACK_BASS, "name": "Bass"},
                        {"track_id": TRACK_VOICE, "name": "Voice"},
                        {"track_id": TRACK_OTHERS, "name": "Others"}
                    ]
        }   

        musics[music_id]=metadata


                
        return jsonify(metadata), 200

    elif request.method == 'GET':
        result = []
        for v in musics:
            result.append(musics[v])

        return jsonify(result), 200


@app.route('/music/<int:music_id>', methods=['POST', 'GET'])
def process_music(music_id):
    if request.method == 'POST':
        # Check if the music_id exists in the musics_files_name dictionary
        if music_id not in musics_files:
            return jsonify({"error": "Invalid music_id"}), 400

        instruments_list = request.json['instruments']
        
        # Create a new entry in the music_track_requests dictionary with the given music_id and instruments_list
        if music_id not in music_track_requests:
            music_track_requests[music_id] = instruments_list
        else:
            # If the music_id already exists, update the instruments list
            music_track_requests[music_id].extend(instruments_list)

        # Get the name of the directory with all the music files
        dir_name = "musics/" + str(music_id) + "_chunks/"
        
        total_chunks = len(os.listdir(dir_name))

        # Get all the music files and send them to rabbitmq
        for file in os.listdir(dir_name):
            # Check only for .mp3 files
            if file.endswith('.mp3'):
                for chunk_path in chunks_files[music_id]:
                    if str(file) in str(chunk_path[1]):
                        send_file(dir_name + file, music_id, total_chunks, chunk_path[0])
                        print("File sent to RabbitMQ")
                    else:
                        continue

        # Start consuming messages from the queue processed_music_queue
        consume()

    
    elif request.method == 'GET':

        input_folder = f"musics/{str(music_id)}_processed_music/"
        output_folder = f'musics/{str(music_id)}_processed_music_final/'

        # If there aren
        if len(musics) == 0:
            return jsonify({"error": "No musics found"}), 400
        
        # If the directory input folder doesn't exist, it means that the music has been processed
        if not os.path.exists(input_folder) and os.path.isdir(output_folder):
            return jsonify({"method": "music process state", "status": 100})
        
        else:
            # Get the number of files in the input folder
            total_files_processed = len(os.listdir(input_folder))

            # Get the number of chunks in the music
            total_chunks = len(os.listdir(f"musics/{str(music_id)}_chunks/"))

            # Calculate the percentage of the music processed
            percentage = ((total_files_processed / 4) / total_chunks) * 100

            return jsonify({"progress": percentage}), 200

@app.route('/music/<int:music_id>_processed_music_final/<path:filename>', methods=['GET'])
def serve_audio_file(music_id, filename):
    if request.method == 'GET':
        directory = f"musics/{music_id}_processed_music_final"
        file_path = os.path.join(directory, filename)
        if os.path.exists(file_path):
            return send_from_directory(directory, filename)
        else:
            response = make_response("Arquivo não encontrado", 404)
            return response
        


@app.route('/jobs', methods=['GET'])
def list_jobs():
    if request.method == 'GET':

        # Create a new connection to RabbitMQ in case the connection closes
        connection = pika.BlockingConnection(pika.ConnectionParameters(host='192.168.0.104', port=5672))
        channel = connection.channel()

        message = {"method": "list jobs"}
        for i in range(NUM_WORKERS):
            channel.basic_publish(exchange='', routing_key='music_queue', body=json.dumps(message))
        print("Sent request to list jobs to RabbitMQ")
        
        if len(total_jobs) == NUM_WORKERS:
            all_workers_done.wait()
    
            all_jobs_ids = []
            for worker_job_id in total_jobs.values():
                for job_id in worker_job_id:
                    print("Added job_id to list")
                    all_jobs_ids.append(job_id)

        return jsonify({"job_ids": all_jobs_ids}), 200
    
@app.route('/jobs/<int:job_id>', methods=['GET'])
def get_information_job(job_id):
    if request.method == 'GET':

        job_info = total_jobs_informations[job_id]
        size = job_info['size']
        time = job_info['time']
        music_id = job_info['music_id']
        track_id = job_info['track_id']

        information = {
            "job_id": job_id,
            "size": size,
            "time": time,
            "music_id": music_id,
            "track_id": track_id,
        }

        return jsonify(information), 200



@app.route('/reset', methods=['POST'])
def reset():
    if request.method == 'POST':
        # Clear dictionaries and lists
        musics.clear()
        musics_files.clear()
        music_track_requests.clear()

        # Delete sound files on the server-side
        for filename in os.listdir('musics'):
            file_path = os.path.join('musics', filename)
            try:
                if os.path.isfile(file_path) or os.path.islink(file_path):
                    os.unlink(file_path)
                elif os.path.isdir(file_path):
                    shutil.rmtree(file_path)
            except Exception as e:
                print(f'Failed to delete {file_path}. Reason: {e}')

        # Send stop messages to all workers
        delete_worker_files()
        send_stop_message()

        connection.close()

        return 'Success on POST request in reset'

def send_stop_message():
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='192.168.0.104', port=5672))
    channel = connection.channel()
    message = {"method": "stop"}
    for i in range(NUM_WORKERS):
        channel.basic_publish(exchange='', routing_key='music_queue', body=json.dumps(message))
    print("Sent stop message to RabbitMQ")

def delete_worker_files():
    connection = pika.BlockingConnection(pika.ConnectionParameters('192.168.0.104', port=5672))
    channel = connection.channel()
    message = {"method": "delete files"}
    for i in range(NUM_WORKERS):
        channel.basic_publish(exchange='', routing_key='music_queue', body=json.dumps(message))
    print("Sent delete files message to RabbitMQ")




def connect_to_rabbitmq():
    global connection, channel
    while connection is None:
        try:
            connection = pika.BlockingConnection(pika.ConnectionParameters(host='192.168.0.104', port=5672))
            channel = connection.channel()
            channel.queue_declare(queue='music_queue', durable=True)
            channel.queue_declare(queue='processed_music_queue', durable=True)
            print("Connected to RabbitMQ")

            channel.basic_qos(prefetch_count=1)
            channel.basic_consume(queue='music_queue', on_message_callback=callback, auto_ack=True)
            channel.start_consuming()
        except Exception as e:
            print("Failed to connect to RabbitMQ. Retrying in 5 seconds...")
            time.sleep(5)

connect_to_rabbitmq()

def callback(ch, method, properties, body):
        body = json.loads(body)

        if body == None:
            return 

        if body['method'] == 'music chunk processed':
            # Get the music id
            music_id = body['music_id']

            fname = body['file_name'] + ".wav"
            total_chunks = body['total_chunks']

            folder_path = "musics/" + str(music_id) + "_processed_music/"
            os.makedirs(folder_path, exist_ok=True)
            output_path = os.path.join(folder_path, fname)

            with open(output_path, 'wb') as f:
                f.write(base64.b64decode(body['music_data']))

            if len(os.listdir(folder_path)) == (total_chunks * 4):
                join_instrument_files(music_id, music_track_requests)

        elif body['method'] == "reply list jobs":
            worker_id = body['worker_id']

            if worker_id not in total_jobs:
                total_jobs[worker_id] = []

            jobs = body['jobs_ids']
            for j in jobs:
                total_jobs[worker_id].append(j)

            jobs_info = body['jobs_informations']

            for info in jobs_info:
                job_id = info['job_id']
                job_infor = info['job_information']
                if job_id not in total_jobs_informations:
                    total_jobs_informations[job_id] = job_infor

            if len(total_jobs) == NUM_WORKERS:
                all_workers_done.set()


def consume():
    while True:
        try:
            connection = pika.BlockingConnection(pika.ConnectionParameters('192.168.0.104', port=5672))
            channel = connection.channel()

            # Declare the 'processed_music_queue'
            channel.queue_declare(queue='processed_music_queue', durable=True)
            print("Connected to RabbitMQ")
            
            # Set up basic_consume for the 'processed_music_queue'
            channel.basic_qos(prefetch_count=1)
            channel.basic_consume(queue='processed_music_queue', on_message_callback=callback, auto_ack=True)

            channel.start_consuming()
        except (pika.exceptions.AMQPConnectionError, pika.exceptions.StreamLostError) as e:
            print("Connection lost, trying to reconnect in 5 seconds...")
            time.sleep(5)
            continue
        break




def join_instrument_files(music_id, music_track_requests):
    """Join all the instrument files into 4 files (one for each instrument)"""
    print("ENTREI NO JOIN INSTRUMENT FILES")
    
    requested_instruments = music_track_requests.get(music_id, [])


    instruments = []
    drums = []
    bass = []
    vocals = []
    others = []

    drums_audio = AudioSegment.empty()
    bass_audio = AudioSegment.empty()
    vocals_audio = AudioSegment.empty()
    others_audio = AudioSegment.empty()

    
    for instrument in requested_instruments:
        instruments.append(instrument)


    input_folder = f"musics/{str(music_id)}_processed_music/"
    output_folder = f"musics/{str(music_id)}_processed_music_final/"
    os.makedirs(output_folder, exist_ok=True)

    # Get all the files in the processed files folder
    f = os.listdir(input_folder)
    files = []
    for file in f:
        files.append(input_folder + file)

    for file in files:
        if file.endswith(".wav"):
            if "drums" in str(file):
                drums.append(file)
            elif "bass" in str(file):
                bass.append(file)
            elif "vocals" in str(file):
                vocals.append(file)
            elif "other" in str(file):
                others.append(file)


            
    # Order the files by number ascending
    drums = sorted(drums, key=lambda x: int(re.findall(r'\d+', x)[-1]))
    bass = sorted(bass, key=lambda x: int(re.findall(r'\d+', x)[-1]))
    vocals = sorted(vocals, key=lambda x: int(re.findall(r'\d+', x)[-1]))
    others = sorted(others, key=lambda x: int(re.findall(r'\d+', x)[-1]))

    if drums is not None:
        joined = AudioSegment.empty()
        for file in drums:
            joined += AudioSegment.from_wav(file)
        drums_audio = joined

    if bass is not None:
        joined = AudioSegment.empty()
        for file in bass:
            joined += AudioSegment.from_wav(file)
        bass_audio = joined

    if vocals is not None:
        joined = AudioSegment.empty()
        for file in vocals:
            joined += AudioSegment.from_wav(file)
        vocals_audio = joined

    if others is not None:
        joined = AudioSegment.empty()
        for file in others:
            joined += AudioSegment.from_wav(file)
        others_audio = joined

    #output all the files
    drums_audio.export(output_folder + "drums.wav", format="wav")
    bass_audio.export(output_folder + "bass.wav", format="wav")
    vocals_audio.export(output_folder + "vocals.wav", format="wav")
    others_audio.export(output_folder + "other.wav", format="wav")
    # Initialize final_mix to an empty AudioSegment
    final_mix = AudioSegment.empty()

    if "drums" in instruments:
        if final_mix != AudioSegment.empty():
            final_mix = final_mix.overlay(drums_audio)
            print("overlay vazio")
        else:
            final_mix += drums_audio

    if "bass" in instruments:
        if final_mix != AudioSegment.empty():
            final_mix = final_mix.overlay(bass_audio)
        else:
            final_mix += bass_audio

    if "vocals" in instruments:
        if final_mix != AudioSegment.empty():
            final_mix = final_mix.overlay(vocals_audio)
        else:
            final_mix += vocals_audio

    if "other" in instruments:
        if final_mix != AudioSegment.empty():
            final_mix = final_mix.overlay(others_audio)
        else:
            final_mix += others_audio


    final_mix.export(output_folder + "final_output.wav", format="wav")
    
    # Set the status to "completed" when the final audio is created
    music_status[music_id] = "completed"

    # Delete the directory processed_music
    shutil.rmtree(input_folder)



def send_file(file_path, music_id, total_chunks, chunk_id):
    """Send file from server to broker (rabbitmq)"""
    connection = pika.BlockingConnection(pika.ConnectionParameters('192.168.0.104', port=5672))
    channel = connection.channel()


    with open(file_path,'rb') as file:
        music_data = file.read()
        music_data = base64.b64encode(music_data).decode('utf-8')

        file_name = os.path.basename(file_path)

        message = {
            "method": "process", 
            "file_name": file_name, 
            "music_id": music_id, 
            "total_chunks": total_chunks, 
            "chunk_id": chunk_id, 
            "music_data": music_data
        }
        channel.basic_publish(exchange='', routing_key='music_queue', body=json.dumps(message))
    connection.close()



def split_music_file(file_path, music_id):
    """Split the music file into several segments with smaller duration (max duration 10s)"""

    # Load the music file
    audio = AudioSegment.from_mp3(file_path)

    # Define max duration (10 seconds = 10000 miliseconds)
    max_duration = 10000

    chunks = []
    start_time = 0

    while start_time < len(audio):
        end_time = start_time + max_duration

        if end_time > len(audio):
            end_time = len(audio)

        chunk = audio[start_time:end_time]
        chunks.append(chunk)

        start_time += max_duration

    # Create directory to store music files
    dir = f'musics/{music_id}_chunks'
    os.makedirs(dir, exist_ok=True)

    # Save music file with the format of .mp3 file
    for i, chunk in enumerate(chunks):
        chunk_id = random.randint(0, 100000000)
        file = f'{dir}/{music_id}_chunk_{i}.mp3'
        chunk.export(file, format="mp3")
        chunks_files[music_id].append((chunk_id, str(file)))

    print("File splited with success")


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
