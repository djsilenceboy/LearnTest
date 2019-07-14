'''
Created on Apr 20, 2016

@author: dj
'''

from bisect import bisect_left
from time import time


data_set = list(range(10**8))
print("List size =", len(data_set))
index = 99990000
print("Find index =", index)

start = time()
data = data_set.index(index)
stop = time()
print("index(): duration time (s) =", stop - start)

start = time()
data = bisect_left(data_set, index)
stop = time()
print("bisect_left(): duration time (s) =", stop - start)

if __name__ == '__main__':
    pass
