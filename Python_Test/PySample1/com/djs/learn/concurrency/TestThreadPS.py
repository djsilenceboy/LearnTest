'''
Created on Apr 6, 2016

@author: dj
'''

import queue
import threading
import time


def producer(product_queue):
    for i in range(0, 10):
        product = "Product" + str(i)
        print("Make product =", product)
        product_queue.put(product)
        time.sleep(1)


def consumer(id, product_queue):
    while True:
        product = product_queue.get()
        print(id, ": Take product =", product)
        time.sleep(2)
        product_queue.task_done()


product_queue = queue.Queue()
for i in range(2):
    consumerThread = threading.Thread(
        target=consumer, args=(i, product_queue,))
    consumerThread.start()

producer(product_queue)
product_queue.join()

if __name__ == '__main__':
    pass
