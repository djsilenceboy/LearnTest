'''
Created on Apr 6, 2016

@author: jiangdu
'''

import zmq


HOST = "localhost"
PORT = 8004

ctx = zmq.Context()
sub = ctx.socket(zmq.SUB)
sub.connect("tcp://{}:{}".format(HOST, PORT))

for i in range(0, 4, 2):
    key = "Id" + str(i)
    print("Subscribe:", key)
    sub.setsockopt(zmq.SUBSCRIBE, key.encode("utf-8"))

while True:
    key, value = sub.recv_multipart()
    print("Recived:", key.decode("utf-8"), "/", value.decode("utf-8"))


if __name__ == '__main__':
    pass
