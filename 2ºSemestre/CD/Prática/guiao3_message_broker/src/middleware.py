"""Middleware to communicate with PubSub Message Broker."""
from collections.abc import Callable
from enum import Enum
from queue import LifoQueue, Empty
from typing import Any

import socket
import json
import pickle
import xml.etree.ElementTree as ET


class MiddlewareType(Enum):
    """Middleware Type."""

    CONSUMER = 1
    PRODUCER = 2



class Queue:
    """Representation of Queue interface for both Consumers and Producers."""

    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        """Create Queue."""
        self.topic = topic
        self._type = _type

        # Initialize socket
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.connect(('localhost', 5000))


    def encode(self, data):
        """Encodes data to be sent to broker. Redefined below in specific queues"""
        pass
    

    def decode(self, data):
        """Decodes data received from broker. Redefined below in specific queues"""
        pass


    def format_protocol(self, method, topic, value): 
        """Formats the protocol to be sent to broker. Redefined below in specific queues"""
        pass


    def push(self, value):
        """Sends data to broker. Used by the producer """

        data = {"method": "publish", "topic": self.topic, "value": value}
        msg = self.encode(self.format_protocol(**data))
        header = len(msg).to_bytes(2, 'big')
        self.sock.send(header + msg)


    def pull(self) -> (str, tuple): # usado pelo consumer
        """Receives (topic, data) from broker. Used by the consumer.

        Should BLOCK the consumer!"""
   
        header = self.sock.recv(2) # get first two bytes to know how many bytes to get after
        header = int.from_bytes(header, "big")
        body = self.sock.recv(header) # return the exact bytes needed

        if len(body) != 0:
            data = self.decode(body)
            topic = data["topic"]
            value = data["value"]

            return topic, value


    def list_topics(self, callback: Callable):
        """Lists all topics available in the broker."""
        message = self.format_protocol("topics")
        message = self.encode(message)
        message = len(message).to_bytes(2, 'big') + message        
        self.sock.send(message) # Send message to broker to list the existing topics


    def cancel(self):
        """Cancel subscription."""
        message = self.format_protocol("cancel", str(self.topic))
        message = self.encode(message)
        message = len(message).to_bytes(2, 'big') + message
        self.sock.send(message) # Send message to broker to cancel subscription



class JSONQueue(Queue):
    """Queue implementation with JSON based serialization."""
    
    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        """Create JSONQueue."""

        super().__init__(topic, _type)

        message = self.format_protocol("serialization", value="json")
        message = self.encode(message)
        message = len(message).to_bytes(2, 'big') + message
        self.sock.send(message) # Send message to broker to set serialization to JSON

        if self._type == MiddlewareType.CONSUMER:
            message = self.format_protocol("subscribe", topic=str(self.topic))
            message = self.encode(message)
            message = len(message).to_bytes(2, 'big') + message
            self.sock.send(message) # Send message to broker to subscribe to topic
    

    def encode(self, data):
        """Encodes data to be sent to broker"""

        message = (json.dumps(data)).encode("utf-8")
        return message


    def decode(self, message):
        """Decodes data received from broker"""

        data = json.loads(message.decode("utf-8"))
        return data


    def format_protocol(self, method, topic=None, value=None):
        """Formats the protocol to be sent to broker"""

        protocol = {"method": method}
        
        if topic is not None:
            protocol["topic"] = topic

        if value is not None:
            protocol["value"] = value

        return protocol
        


class XMLQueue(Queue):
    """Queue implementation with XML based serialization."""

    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        super().__init__(topic, _type)
        msg = '{"method" : "serialization", "value" : "xml"}'
        self.sock.send(len(msg).to_bytes(2, 'big') + bytes(msg,"utf-8"))

        if self._type == MiddlewareType.CONSUMER:
            message = self.format_protocol("subscribe", topic=str(self.topic))
            message = self.encode(message)
            message = len(message).to_bytes(2, 'big') + message
            self.sock.send(message) # Send message to broker to subscribe to topic


    def encode(self, data):
        """Encodes data to be sent to broker"""
        return data.encode("utf-8")


    def decode(self, data):
        """Decodes data received from broker"""
        tree = ET.ElementTree(ET.fromstring(data.decode("utf-8")))           
        data = {}
        
        for el in tree.iter():
            data[el.tag] = el.text

        return data


    def format_protocol(self, method, topic=None, value=None):
        """Formats the message to xml"""
        if topic == None and value == None:
            message = "<data><method>" + str(method) + "</method></data>"
        
        elif topic != None and value == None:
            message = "<data><method>" + str(method) + "</method><topic>" + str(topic) + "</topic></data>"

        elif topic == None and value != None:
            message = "<data><method>" + str(method) + "</method><value>" + str(value) + "</value></data>"

        else:
            message = "<data><method>" + str(method) + "</method><topic>" + str(topic) + "</topic><value>" + str(value) + "</value></data>"

        return message



class PickleQueue(Queue):
    """Queue implementation with Pickle based serialization."""

    def __init__(self, topic, _type=MiddlewareType.CONSUMER):
        """Create PickleQueue."""

        super().__init__(topic, _type)
        
        msg = '{"method" : "serialization", "value" : "pickle"}'
        msg = len(msg).to_bytes(2, 'big') + bytes(msg,"utf-8")
        self.sock.send(msg)

        if self._type == MiddlewareType.CONSUMER:
            message = self.format_protocol("subscribe", topic=str(self.topic))
            message = self.encode(message)
            message = len(message).to_bytes(2, 'big') + message
            self.sock.send(message) # Send message to broker to subscribe to topic
        
    
    def encode(self, data):
        """Encodes data to be sent to broker"""
        
        message = pickle.dumps(data) 
        return message 


    def decode(self, message):
        """Decodes data received from broker"""

        data = pickle.loads(message)
        return data


    def format_protocol(self, method, topic=None, value=None):
        """Formats the protocol to be sent to broker"""

        protocol = {"method": method}
        
        if topic is not None:
            protocol["topic"] = topic

        if value is not None:
            protocol["value"] = value

        return protocol