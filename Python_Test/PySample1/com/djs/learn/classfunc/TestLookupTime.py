'''
Created on Apr 8, 2016

@author: djs
'''

import dis
from math import sin
import math
from timeit import timeit, repeat


def testA1():
    for i in range(100000):
        math.sin(3.14)


def testA2():
    for i in range(100000):
        sin(3.14)


def testA3():
    local_sin = sin
    for i in range(100000):
        local_sin(3.14)


def testB1():
    math.sin(3.14)


def testB2():
    sin(3.14)


def testB3(local_sin=sin):
    local_sin(3.14)


print("testA1 time =", timeit(testA1, number=1), "seconds")
print("testA2 time =", timeit(testA2, number=1), "seconds")
print("testA3 time =", timeit(testA3, number=1), "seconds")

print("-" * 40)

print("testB1 time =", timeit(testB1, number=100000), "seconds")
print("testB2 time =", timeit(testB2, number=100000), "seconds")
print("testB3 time =", timeit(testB3, number=100000), "seconds")

print("-" * 40)

dis.dis(testB1)

print("-" * 40)

dis.dis(testB2)

print("-" * 40)

dis.dis(testB3)

print("-" * 40)

if __name__ == '__main__':
    pass
