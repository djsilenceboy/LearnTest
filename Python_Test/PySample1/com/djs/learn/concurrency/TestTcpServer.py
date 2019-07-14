'''
Created on Apr 6, 2016

@author: dj
'''

import socket

ADDRESS = ("localhost", 8006)
MAX_SIZE = 1000

print("Wait for client...")
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(ADDRESS)
print("server =", server)
server.listen()

client, clientAddress = server.accept()
print("client =", client, "/", clientAddress)

data = client.recv(MAX_SIZE)
print("data =", data)

reply = b"Got it!"
print("reply =", reply)
client.sendall(reply)

client.close()
server.close()

if __name__ == '__main__':
    pass
