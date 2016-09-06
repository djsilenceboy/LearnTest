'''
Created on Apr 20, 2016

@author: djs
'''

from queue import deque

fifo = deque()
print("fifo =", fifo)

fifo.append(1)
fifo.append(2)
print("fifo =", fifo)
fifo.append(3)
print("fifo =", fifo)

fifo.popleft()
print("fifo =", fifo)

fifo.pop()
print("fifo =", fifo)

if __name__ == '__main__':
    pass