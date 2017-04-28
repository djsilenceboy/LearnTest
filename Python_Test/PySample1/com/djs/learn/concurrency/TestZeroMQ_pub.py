'''
Created on Apr 6, 2016

@author: dj
'''

import time
import zmq


HOST = "*"
PORT = 8004

ctx = zmq.Context()
pub = ctx.socket(zmq.PUB)
pub.bind("tcp://{}:{}".format(HOST, PORT))

time.sleep(10)

for i in range(4):
    key = "Id" + str(i)
    value = "Message" + str(i)
    print("Publish:", key, "/", value)
    pub.send_multipart([key.encode("utf-8"), value.encode("utf-8")])


if __name__ == '__main__':
    pass
