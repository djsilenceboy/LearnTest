'''
Created on Apr 6, 2016

@author: dj
'''

import socket

ADDRESS = ("localhost", 8006)
MAX_SIZE = 1000

print("Connect to server...")
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(ADDRESS)
print("client =", client)

request = b"Anything?"
print("request =", request)
client.sendall(request)

data = client.recv(MAX_SIZE)
print("data =", data)

client.close()

if __name__ == '__main__':
    pass
