'''
Created on July 13, 2016

@author: dj
'''

# Test this from command line:
# python -m cProfile -s cumulative TestProfile0.py

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


do_test()

if __name__ == '__main__':
    pass
