"""CD Chat server program."""
import logging
import socket
import selectors

from .protocol import CDProto, CDProtoBadFormat

logging.basicConfig(filename="server.log", level=logging.DEBUG)

class Server:
    """Chat Server process."""
    def __init__(self):
        print("SERVER: Initializing server")

        # Socket initialization
        self.sock = socket.socket()
        self.sock.bind(('localhost', 2000))
        self.sock.listen(100)

        # Selector initialization
        self.sel = selectors.DefaultSelector()
        self.sel.register(self.sock, selectors.EVENT_READ, self.accept)

        # Additional data structures
        self.users = {}
        # key   -> username
        # value -> list of channels
        self.connections = {}
        # key   -> connection
        # value -> username
    
    def read(self, conn, mask):
        
        try:
            data = CDProto.recv_msg(conn) # Receive message
        except CDProtoBadFormat:
            print("SERVER: Bad format found")
            exit(1)

        print(data)

        if data:
            # Process Register
            if(data.command == "register"):
                # Add a new user to the users dict and inicialize the list of channels
                print("SERVER: Added user ", str(data.username))
                self.users[data.username] = []
                self.connections[conn] = data.username

            # Process Join
            elif(data.command == "join"):
                # Get username
                username = self.connections[conn]

                # Add a new channel to the user's list of channels
                self.users[username].append(data.channel)
                print("SERVER: Added channel ", str(data.channel), " to user", username)

            # Process message
            elif(data.command == "message"):
                # Check if the atribute channel exists
                if data.channel:
                    for c in self.connections:
                        if data.channel in self.users[self.connections[c]]:
                            print("SERVER: Echoing message to user ", self.connections[c], " on channel", data.channel)
                            CDProto.send_msg(c,data) # Send the message to all users in the same channel
                else:
                    for c in self.connections:
                        print("SERVER: Echoing message to user", self.connections[c])
                        CDProto.send_msg(c,data) # Send the message to all users  
        # Exit message
        else:
            name = self.connections[conn]
            self.users.pop(name)
            self.connections.pop(conn)
            self.sel.unregister(conn)
            conn.close()
            print("SERVER: Client ", name, " disconnected")
 
    def accept(self, sock, mask):
        (conn, addr) = sock.accept()
        conn.setblocking(False)
        self.sel.register(conn, selectors.EVENT_READ, self.read)

    
    def loop(self):
        """Loop indefinetely."""

        print("SERVER: Starting server loop")

        while True:
            events = self.sel.select()
            for key, mask in events:
                callback = key.data
                callback(key.fileobj, mask)
