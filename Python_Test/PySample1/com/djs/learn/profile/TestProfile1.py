'''
Created on July 13, 2016

> python -m cProfile -s cumulative TestProfile1.py

@author: dj
'''

from cProfile import Profile
from pstats import Stats
from random import randint

max_size = 5000


def calculate(x_array, y_array):
    matrix = [[0 for x in range(max_size)] for y in range(max_size)]
    for y in range(max_size):
        for x in range(max_size):
            matrix[y][x] = y_array[y] * x_array[x]
    return matrix


def do_test():
    x_array = [randint(-max_size, max_size) for _ in range(max_size)]
    y_array = [randint(-max_size, max_size) for _ in range(max_size)]

    matrix = calculate(x_array, y_array)

    '''
    for y in range(max_size):
        for x in range(max_size):
            print("{0:-10}".format(matrix[y][x]), end="")
        print("")
    '''


profiler = Profile()
profiler.runcall(do_test)

stats = Stats(profiler)
stats.strip_dirs()
stats.sort_stats("cumulative")

print("-" * 40)

stats.print_stats()

print("-" * 40)

stats.print_callees()

print("-" * 40)

stats.print_callers()

print("-" * 40)

if __name__ == '__main__':
    pass
