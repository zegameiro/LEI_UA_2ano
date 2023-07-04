import grpc
from concurrent import futures
import time

from lei_pb2 import Prato
from lei_pb2_grpc import CantinaServicer, add_CantinaServicer_to_server


# Inherit from CantinaServicer
class Cantina(CantinaServicer):
    
    # Define Method to be invoked remotely
    def Refeicao(self, request, context):
        print(type(request))
        print(request.nome)

        # Return Prato message
        return Prato(sopa="feijao", prato="arroz", sobremesa="aletria")


def serve():
    # Create a pool of 10 threads to serve remote calls
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))

    # Register a Cantina Object into server
    add_CantinaServicer_to_server(Cantina(), server)

    # Bind server & start
    server.add_insecure_port("[::]:5001")
    server.start()
    try:
        while True:
            time.sleep(60)
    except KeyboardInterrupt:
        server.stop(0)


if __name__ == "__main__":
    serve()
