'''
Created on Apr 26, 2017

@author: dj
'''

from concurrent.futures import ProcessPoolExecutor
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

print("Run in ProcessPoolExecutor.")
start = time()
pool = ProcessPoolExecutor(max_workers=2)
results = list(pool.map(gcd, numbers))
stop = time()
print("results =", results)
print("duration time (s) =", stop - start)

# Currently, there is error:
# concurrent.futures.process.BrokenProcessPool: A process in the process
# pool was terminated abruptly while the future was running or pending.

if __name__ == '__main__':
    pass
