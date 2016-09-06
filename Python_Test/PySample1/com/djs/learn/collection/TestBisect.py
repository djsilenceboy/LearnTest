'''
Created on Apr 20, 2016

@author: djs
'''

from bisect import bisect_left
from time import time

x = list(range(10**8))
j = 99990000
print("Find j =", j)

start = time()
i = x.index(j)
stop = time()
print("index: duration time (s) =", stop - start)

start = time()
i = bisect_left(x, j)
stop = time()
print("bisect_left: duration time (s) =", stop - start)

if __name__ == '__main__':
    pass
