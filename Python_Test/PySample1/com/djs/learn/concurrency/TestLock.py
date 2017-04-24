'''
Created on Apr 16, 2016

@author: djs
'''


from threading import Lock, Thread


class CounterNTS(object):

    def __init__(self):
        self.count = 0

    def increment(self, offset):
        self.count += offset


class CounterTS(object):

    def __init__(self):
        self.lock = Lock()
        self.count = 0

    def increment(self, offset):
        with self.lock:
            self.count += offset


def worker(index, loops, counter):
    # print(index, ": start")
    for _ in range(loops):
        counter.increment(1)
    # print(index, ": stop")


def run_threads(loops, counter):
    threads = []
    for i in range(5):
        thread = Thread(target=worker, args=(i, loops,  counter))
        thread.start()
        threads.append(thread)

    for thread in threads:
        thread.join()


counter1 = CounterNTS()
run_threads(10000, counter1)
print("Non-Thread-Safe final count =", counter1.count)

print("-" * 40)

counter2 = CounterNTS()
run_threads(100000, counter2)
print("Non-Thread-Safe final count =", counter2.count)

print("-" * 40)

counter3 = CounterTS()
run_threads(10000, counter3)
print("Thread-Safe final count =", counter3.count)

print("-" * 40)

counter4 = CounterTS()
run_threads(100000, counter4)
print("Thread-Safe final count =", counter4.count)

print("-" * 40)


if __name__ == '__main__':
    pass
