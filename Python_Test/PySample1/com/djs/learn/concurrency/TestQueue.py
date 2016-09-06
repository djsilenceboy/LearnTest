'''
Created on Apr 6, 2016

@author: jiangdu
'''

from queue import Queue
from threading import Thread
import time


def producer(id, product_queue):
    for i in range(0, 4):
        product = "Product" + str(i)
        print(id, ": Make product =", product)
        product_queue.put(product)
        time.sleep(1)


def consumer(id, product_queue):
    while True:
        product = product_queue.get()
        print(id, ": Take product =", product)
        time.sleep(1)
        product_queue.task_done()


product_queue = Queue()

consumer_thread = Thread(target=consumer, args=(1, product_queue,))
# consumer_thread.daemon = True
consumer_thread.start()

producer_thread = Thread(target=producer, args=(0, product_queue,))
# producer_thread.daemon = True
producer_thread.start()

producer_thread.join()
consumer_thread.join()

if __name__ == '__main__':
    pass
