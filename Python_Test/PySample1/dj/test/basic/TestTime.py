'''
Created on Apr 8, 2016

@author: dj
'''

from datetime import datetime, timezone
from time import time, localtime, strftime, strptime, mktime
from timeit import timeit, repeat


def make_list1():
    result = []
    for value in range(1000):
        result.append(value)
    return result


def make_list2():
    result = [value for value in range(1000)]
    return result

counter = 0


def count1():
    global counter
    counter += 1
    print("Count = {}".format(counter))


t1 = time()
make_list1()
t2 = time()
print("make_list1() time =", t2 - t1)

t1 = time()
make_list2()
t2 = time()
print("make_list2() time =", t2 - t1)

print("-" * 40)

print("make_list1(1000) time =", timeit(make_list1, number=1000), "seconds")
print("make_list2(1000) time =", timeit(make_list2, number=1000), "seconds")

print("-" * 40)

print("make_list1(2000) time =", timeit(make_list1, number=2000), "seconds")
print("make_list2(2000) time =", timeit(make_list2, number=2000), "seconds")

print("-" * 40)

print("count1(3, 2) time =", repeat(count1, repeat=3, number=2), "seconds")

print("-" * 40)

print("timeit(2) time =", timeit(
    'print("123")', setup='print("abc")', number=2), "seconds")
print("timeit(2) time =", timeit(
    'sum += 1; print("Sum =", sum)', setup='sum = 0; print("Start sum =", sum)', number=2), "seconds")

print("-" * 40)

time_point1 = 1500000000
print("time point1 =", time_point1)
time_tuple = localtime(time_point1)
print("time point1 (tuple) =", time_tuple)
time_format = "%Y-%m-%d %H:%M:%S"
print("time point1 (format) =", time_format)
time_str = strftime(time_format, time_tuple)
print("time point1 (formatted) =", time_str)

print("-" * 40)

time_tuple2 = strptime(time_str, time_format)
time_point2 = mktime(time_tuple2)
print("time point2 =", time_point2)

print("-" * 40)

time_point3 = datetime(2016, 10, 1, 15, 30, 25)
print("time point3 =", time_point3)
time_utc = time_point3.replace(tzinfo=timezone.utc)
print("time point3 (utc) =", time_utc)
time_local = time_utc.astimezone()
print("time point3 (utc.astimezone) =", time_local)

print("-" * 40)

time_point4 = time_str
print("time point4 =", time_point4)
time_now = datetime.strptime(time_point4, time_format)
print("time point4 (now) =", time_now)
time_tuple = time_now.timetuple()
print("time point4 (tuple) =", time_tuple)
time_utc = mktime(time_tuple)
print("time point4 (utc) =", time_utc)

print("-" * 40)

print(strftime("%Y%m%d_%H%M%S", localtime(time())))


if __name__ == '__main__':
    pass
