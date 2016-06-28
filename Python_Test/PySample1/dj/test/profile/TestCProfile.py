'''
Created on Apr 20, 2016

@author: dj
'''

from bisect import bisect_left
from cProfile import Profile
from pstats import Stats
from random import randint


def insertion_sort(data_array, func):
    sorted_array = []
    for data in data_array:
        func(sorted_array, data)
    return sorted_array


def insert_value(sorted_array, data):
    for i, existing in enumerate(sorted_array):
        if existing > data:
            sorted_array.insert(i, data)
            return
    sorted_array.append(data)


def insert_value_ex(sorted_array, data):
    i = bisect_left(sorted_array, data)
    sorted_array.insert(i, data)


max_size = 10000
data_array = [randint(0, max_size) for _ in range(max_size)]
test_func = lambda: insertion_sort(data_array, insert_value)

profiler = Profile()
print("profiler =", profiler)
profiler.runcall(test_func)

stats = Stats(profiler)
print("stats =", stats)

stats.strip_dirs()
stats.sort_stats("cumulative")

stats.print_stats()
stats.print_callees()

print("-" * 40)

profiler2 = Profile()
print("profiler2 =", profiler2)
test_func_2 = lambda: insertion_sort(data_array, insert_value_ex)

profiler2.runcall(test_func_2)

stats2 = Stats(profiler2)
print("stats2 =", stats2)

stats2.strip_dirs()
stats2.sort_stats("cumulative")

stats2.print_stats()
stats2.print_callees()


if __name__ == '__main__':
    pass
