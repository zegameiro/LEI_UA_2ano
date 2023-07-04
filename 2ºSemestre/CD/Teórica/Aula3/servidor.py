import grpc
from concurrent import futures
import time

from chat_pb2 import Mensagem 
from chat_pb2_grpc import ChatServicer, add_ChatServicer_to_server


class Chat(ChatServicer):
    
    # Define Method to be invoked remotely
    def enviar(self, request, context):
        msg = request
        return Mensagem(nickname=msg.nickname, texto=f"ECHO {msg.texto}")


def serve():
    # Create a pool of 10 threads to serve remote calls
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))

    # Register a Cantina Object into server
    add_ChatServicer_to_server(Chat(), server)

    # Bind server & start
    server.add_insecure_port("localhost:5001")
    server.start()
    try:
        while True:
            time.sleep(60)
    except KeyboardInterrupt:
        server.stop(0)


if __name__ == "__main__":
    serve()
