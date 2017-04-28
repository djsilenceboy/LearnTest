'''
Created on Apr 16, 2016

@author: dj
'''

'''
Due to GIL (Global Interpreter Lock), CPython bytecode interpreter run in one thread.
'''


from threading import Thread
from time import time


numbers = [2139079, 1214759, 1516637, 1852285]


def factorize(number):
    for i in range(1, number + 1):
        if number % i == 0:
            # print(number, ":", i)
            yield i


def factor_list(number):
    fact_list = list(factorize(number))
    print("fact_list =", fact_list)


def run_in_sequence():
    start = time()
    for number in numbers:
        factor_list(number)
    end = time()
    print("duration time (s) =", end - start)


def run_in_threads():
    start = time()
    threads = []
    for number in numbers:
        thread = Thread(target=factor_list, args=(number,))
        thread.start()
        threads.append(thread)

    for thread in threads:
        thread.join()

    end = time()
    print("duration time (s) =", end - start)


run_in_sequence()

print("-" * 40)

run_in_threads()

print("-" * 40)

if __name__ == '__main__':
    pass
