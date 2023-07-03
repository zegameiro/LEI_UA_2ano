"""Protocol for chat server - Computação Distribuida Assignment 1."""
import json
from datetime import datetime
from socket import socket


class Message:
    """Message Type."""

    # Class constructor initialization
    def __init__(self,command):
        self.command = command

    # Definition of the __repr__() function
    def __repr__(self):
        self.__str__()
    
class JoinMessage(Message):
    """Message to join a chat channel."""

    # Class constructor initialization
    def __init__(self,command,channel):
        super().__init__(command)
        self.channel = channel
    
    # String representation of the object
    def __str__(self):
        return f'{{"command": "{self.command}", "channel": "{self.channel}"}}'
        
    

class RegisterMessage(Message):
    """Message to register username in the server."""

    # Class constructor initialization
    def __init__(self,command,username):
        super().__init__(command)
        self.username = username

    # String representation of the object
    def __str__(self):
        return f'{{"command": "{self.command}", "user": "{self.username}"}}'

    
class TextMessage(Message):
    """Message to chat with other clients."""

    # Class constructor initialization
    def __init__(self,command,message,channel=None,ts=None):
        super().__init__(command)
        self.message = message
        self.channel = channel
        self.ts = ts

    # String representation of the object
    def __str__(self):
        if self.channel:
            return f'{{"command": "{self.command}", "message": "{self.message}", "channel": "{self.channel}", "ts": {self.ts}}}'
        else:
            return f'{{"command": "{self.command}", "message": "{self.message}", "ts": {self.ts}}}'


class CDProto:
    """Computação Distribuida Protocol."""

    @classmethod
    def register(cls, username: str) -> RegisterMessage:
        """Creates a RegisterMessage object."""
        return RegisterMessage("register",username)

    @classmethod
    def join(cls, channel: str) -> JoinMessage:
        """Creates a JoinMessage object."""
        return JoinMessage("join", channel)

    @classmethod
    def message(cls, message: str, channel: str = None) -> TextMessage:
        """Creates a TextMessage object."""
        return TextMessage("message",message,channel,int(datetime.now().timestamp()))

    @classmethod
    def send_msg(cls, connection: socket, msg: Message):
        """Sends through a connection a Message object."""

        if isinstance(msg, JoinMessage):
            json_message = json.dumps(str(msg))

        elif isinstance(msg, RegisterMessage):
            json_message = json.dumps(str(msg))

        elif isinstance(msg, TextMessage):
            json_message = json.dumps(str(msg))
        
        # Get header of the message which contais the size of the message
        h = len(json_message).to_bytes(2,'big')

        # Send the message to the server
        connection.send(h + json_message.encode('utf-8'))


    @classmethod
    def recv_msg(cls, connection: socket) -> Message:
        """Receives through a connection a Message object."""

        # Receive the message from the server and store it in a dictionary
        h = int.from_bytes(connection.recv(2),'big')

        if h == 0:
            return None
        
        message = connection.recv(h).decode('utf-8')

        try:
            json_message = json.loads(message)

            if type(json_message) is not dict: # Check if the json_message is dict type
                dictionary = json.loads(json_message)
            
            else:
                dictionary = json_message
        
        except json.JSONDecodeError:
            raise CDProtoBadFormat(message)

               
        if dictionary["command"] == "register":
            userName = dictionary["user"]
            return CDProto.register(userName)

        elif dictionary["command"] == "join":
            channel = dictionary["channel"]
            return CDProto.join(channel)
        
        elif dictionary["command"] == "message":
            msg = dictionary["message"]

            # Check if the channel atribute exists
            try:
                channel = dictionary["channel"]
            except KeyError:
                return CDProto.message(msg)
            
            return CDProto.message(msg,channel)

        
class CDProtoBadFormat(Exception):
    """Exception when source message is not CDProto."""

    def __init__(self, original_msg: bytes=None):
        """Store original message that triggered exception."""
        self._original = original_msg

    @property
    def original_msg(self) -> str:
        """Retrieve original message as a string."""
        return self._original.decode("utf-8")
    