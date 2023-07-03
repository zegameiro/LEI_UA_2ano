"""CD Chat client program"""

import fcntl
import logging
import sys
import os
import socket
import selectors

from .protocol import CDProto


# Log file configuration
logging.basicConfig(filename=f"{sys.argv[0]}.log", level=logging.DEBUG) 

class Client:
    """Chat Client process."""
    
    def __init__(self, name: str = "Foo"):
        """Initializes chat client."""
        self.name = name

        # Current channel
        self.currChannel = None

        # Socket initialization
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # Creating a selector
        self.sel = selectors.DefaultSelector()

    def connect(self):
        """Connect to chat server and setup stdin flags."""
        print("CLIENT ", self.name, ": connecting to server...")
        self.sock.connect(("localhost", 2000))

        # send register message
        msg = CDProto.register(self.name)
        CDProto.send_msg(self.sock, msg)
        print("CLIENT ", self.name, " : register successful")

    # Function to be called when enter is pressed
    def got_keyboard_data(self, stdin, mask):
        inMessage = stdin.read()
        inMessage = inMessage[:len(inMessage)-1]

        # Send message to server
        if inMessage:
            # Exit message
            if inMessage == "exit":
                # Close socket connection
                self.sock.close()

                # Unregister socket
                self.sel.unregister(self.sock)
                print("CLIENT", self.name, ": is going shutdown")
                sys.exit(0)
            
            # Join message
            elif inMessage[:6] == "/join ":
                # Added channel to the list
                channel = inMessage[6:]
                self.currChannel = channel
                msg = CDProto.join(channel)
                CDProto.send_msg(self.sock,msg)
                print("CLIENT: joinned server ", channel)

            # Text message
            else:
                msg = CDProto.message(inMessage, self.currChannel)
                CDProto.send_msg(self.sock,msg)

            print(msg)


    def read_message(self, conn, mask):
        msg = CDProto.recv_msg(conn)
        # If the message is not empty
        if msg != "":
            print("Message Received: ", msg)


    def loop(self):
        """Loop indefinetely."""
        # set sys.stdin none-blocking
        orig_fl = fcntl.fcntl(sys.stdin, fcntl.F_GETFL)
        fcntl.fcntl(sys.stdin, fcntl.F_SETFL, orig_fl | os.O_NONBLOCK)

        # Register for reading data from keyboard
        self.sel.register(sys.stdin, selectors.EVENT_READ, self.got_keyboard_data)

        # Register for reading data from the socket
        self.sel.register(self.sock, selectors.EVENT_READ, self.read_message)

        # loop for sending messages
        while True:
            sys.stdout.write('Type something and hit enter: ')
            sys.stdout.flush()
            for k, mask in self.sel.select():
                callback = k.data
                callback(k.fileobj, mask)
    
    