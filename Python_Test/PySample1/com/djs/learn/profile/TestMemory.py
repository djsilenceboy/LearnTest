'''
Created on Apr 20, 2016

@author: dj
'''

import tracemalloc


tracemalloc.start(10)
time1 = tracemalloc.take_snapshot()
print("time1 = ", time1)
x = [i for i in range(10000)]
y = {i for i in range(1000)}
time2 = tracemalloc.take_snapshot()
print("time2 = ", time2)

print("-" * 40)

print("Top 10")
print("----------")
stats = time2.compare_to(time1, "lineno")
for stat in stats[:10]:
    print(stat)

print("-" * 40)

stats = time2.compare_to(time1, "traceback")
for stat in stats[:10]:
    print("\n".join(stat.traceback.format()))
    print("----------")


if __name__ == '__main__':
    pass
