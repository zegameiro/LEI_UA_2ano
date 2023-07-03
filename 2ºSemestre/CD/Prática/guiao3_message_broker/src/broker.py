"""Message Broker"""
import enum
from typing import Dict, List, Any, Tuple

import socket
import selectors
import sys
import json
import pickle
import xml.etree.ElementTree as ET


class Serializer(enum.Enum):
    """Possible message serializers."""

    JSON = 0
    XML = 1
    PICKLE = 2



class Broker:
    """Implementation of a PubSub Message Broker."""

    def __init__(self):
        """Initialize broker."""

        self.canceled = False
        self._host = "localhost"
        self._port = 5000

        # Initialize socket
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.bind((self._host, self._port))
        self.sock.listen(100)
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        # Initialize selector
        self.sel = selectors.DefaultSelector()
        self.sel.register(self.sock, selectors.EVENT_READ, self.accept)

        # Additional data structures
        self.sockets = {}
            # key -> connection
            # value -> serialization

        self.subscriptions = {}
            # key -> (connection, serialization)
            # value -> [topic1, topic2, ...]

        self.topics = {}
            # key -> topic
            # value -> [value1, value2, ...]


    def list_topics(self) -> List[str]:
        """Returns a list of strings containing all topics."""

        topics_list = []

        for k in self.topics:
            topics_list.append(k)
        
        return topics_list


    def get_topic(self, topic):
        """Returns the currently stored value in topic."""

        # Run through all the topics and check if the topic is a substring of any of them
        for t, values in self.topics.items():
            if topic.startswith(t):
                if values:
                    return values[-1]
        return None


    def put_topic(self, topic, value):
        """Store in topic the value."""

        if topic in self.topics:
            self.topics[topic].append(value)
        else:
            self.topics[topic] = [value]
            

    def list_subscriptions(self, topic: str) -> List[socket.socket]:
        """Provide list of subscribers to a given topic."""

        subscribers = []

        for t, socks in self.subscriptions.items():
            if topic in socks:
                subscribers.append(t)

        return subscribers


    def subscribe(self, topic: str, address: socket.socket, _format: Serializer = None):
        """Subscribe to topic by client in address."""

        subscription = (address, _format)
    
        if subscription not in self.subscriptions:
            self.subscriptions[subscription] = [topic]

        elif topic not in self.subscriptions[subscription]:
            self.subscriptions[subscription].append(topic)


    def unsubscribe(self, topic, address):
        """Unsubscribe to topic by client in address."""

        # Creates a list of keys that contains the address
        keys_to_remove = [key for key in self.subscriptions if address in key]

        for key in keys_to_remove:

            # Removes the topic from the corresponding subscription list for each key in the data structure keys_to_remove
            if topic in self.subscriptions[key]:
                self.subscriptions[key].remove(topic)

            # If the subscription list corresponding to the key is empty, remove the key from the dictionary
            if not self.subscriptions[key]:
                del self.subscriptions[key]


    def format_protocol(self, method, topic: None, value, serialization):
        """Formats the data for sending over the network based on the specified serialization."""

        if serialization == "xml":
            data = "<data><method> {} </method>".format(method)

            if topic is not None:
                data += "<topic> {} </topic>".format(topic)

            data += "<value> {} </value></data>".format(value)

        else:
            data = {"method": method, "value": value}

            if topic is not None:
                data["topic"] = topic
        
        return data


    def encode(self, message, serialization):
        """Encodes data based on the specified serialization."""

        if serialization == "json":
            data = json.dumps(message).encode("utf-8")
        
        elif serialization == "xml":
            data = message.encode("utf-8")
        
        elif serialization == "pickle":
            data = pickle.dumps(message)

        return data


    def decode(self, data, serialization):
        """Decodes data based on the specified serialization."""

        if serialization == "json":
            message = json.loads(data.decode("utf-8"))
        
        elif serialization == "xml":
            tree = ET.ElementTree(ET.fromstring(data.decode("utf-8")))    
            message = {}
            
            for el in tree.iter():
                message[el.tag] = el.text

        elif serialization == "pickle":
            message = pickle.loads(data)

        return message
            

    def accept(self, sock, mask):
        """Accept function for the selector"""

        (conn, addr) = sock.accept()
        self.sel.register(conn, selectors.EVENT_READ, self.read) 


    def recv_msg(self, conn, mask):
        """Function to receive message"""  
        header = conn.recv(2) # get first two bytes to know how many bytes to get after
        header = int.from_bytes(header, "big")
        body = conn.recv(header) # return the exact bytes needed

        if len(body) != 0:
            if conn in self.sockets:
                serialization = self.sockets[conn]
            else:
                serialization = "json"
            
            data = self.decode(body, serialization)
        else:
            data = None

        return data
    

    def read(self, conn, mask):
        """Read function for the selector"""

        data = self.recv_msg(conn, mask)
            
        if data != None:
            if (conn not in self.sockets):
                self.sockets[conn] = data["value"]

            if data["method"] == "publish":
                self.put_topic(data["topic"], data["value"])

                for key, topics in self.subscriptions.items():
                    connection = key[0]

                    for t in topics:
                        if data["topic"].startswith(t):
                            try:
                                response = self.format_protocol("response", data["topic"], data["value"], self.sockets[connection])
                                response = self.encode(response, self.sockets[connection])
                                response = len(response).to_bytes(2, 'big') + response
                                connection.send(response)

                            except:
                                pass # socket was closed during iteration
            
            elif data["method"] == "topics":
                response = self.format_protocol("response", None, str(self.list_topics()), self.sockets[conn])
                response = self.encode(response, self.sockets[conn])
                response = len(response).to_bytes(2, 'big') + response
                conn.send(response)
            
            elif data["method"] == "subscribe":
                self.subscribe(data["topic"], conn)
                last_topic = self.get_topic(data["topic"])

                if last_topic != None:
                    response = self.format_protocol("response", data["topic"], last_topic, self.sockets[conn])
                    response = self.encode(response, self.sockets[conn])
                    response = len(response).to_bytes(2, 'big') + response
                    conn.send(response)
            
            elif data["method"] == "cancel":
                self.unsubscribe(data["topic"], conn)
        else:
            self.sel.unregister(conn)
            conn.close()
  

    def run(self):
        """Run until canceled."""

        while not self.canceled:
            try:
                for key, mask in self.sel.select():
                    callback = key.data
                    callback(key.fileobj, mask)

            except KeyboardInterrupt:
                sys.exit(0)

            except socket.error:
                sys.exit(0)    


                