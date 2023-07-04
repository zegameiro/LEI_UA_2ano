import grpc

from lei_pb2 import Menu
from lei_pb2_grpc import CantinaStub

with grpc.insecure_channel("localhost:5001") as channel:
    # Create message
    menu = Menu(nome="normal")
    
    # Create stub to Cantina (server)
    stub = CantinaStub(channel)
    
    # Invoke remote method Refeicao with Menu message
    response = stub.Refeicao(menu)

    print(type(response))
    print(response.sopa)
    print(response.prato)
    print(response.sobremesa)
