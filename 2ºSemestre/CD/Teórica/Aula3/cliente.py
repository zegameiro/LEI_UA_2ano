import grpc

from chat_pb2 import Mensagem
from chat_pb2_grpc import ChatStub

with grpc.insecure_channel("localhost:5001") as channel:
    # Create message
    msg = Mensagem(nickname="dgomes", texto="Ola Mundo")   
 
    stub = ChatStub(channel)
    
    response = stub.enviar(msg)

    print(type(response))
    print(response.texto)
