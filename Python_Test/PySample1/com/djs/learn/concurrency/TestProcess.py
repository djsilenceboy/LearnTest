'''
Created on Apr 18, 2016

@author: dj
'''

from concurrent.futures import ThreadPoolExecutor
from time import time


def gcd(pair):
    a, b = pair
    low = min(a, b)
    for i in range(low, 0, -1):
        if (a % i == 0) and (b % i == 0):
            return i


numbers = [(1963309, 2265973), (2030677, 3814172),
           (1551645, 2229620), (2039045, 2020802)]

print("-" * 40)

print("Run in sequence.")
start = time()
results = list(map(gcd, numbers))
stop = time()
print("results =", results)
print("duration time (s) =", stop - start)

print("-" * 40)

print("Run in ThreadPoolExecutor.")
start = time()
pool = ThreadPoolExecutor(max_workers=4)
results = list(pool.map(gcd, numbers))
stop = time()
print("results =", results)
print("duration time (s) =", stop - start)

print("-" * 40)

if __name__ == '__main__':
    pass
