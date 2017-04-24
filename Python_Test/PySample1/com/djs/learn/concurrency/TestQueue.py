'''
Created on Apr 6, 2016

@author: jiangdu
'''

from queue import Queue
from threading import Thread
import time


SENTINEL = object()


def producer(id, product_queue):
    for i in range(0, 4):
        product = "Product" + str(i)
        print(id, ": Make product =", product)
        product_queue.put(product)
        time.sleep(0.1)
    print(id, ": Put SENTINEL")
    product_queue.put(SENTINEL)


def consumer(id, product_queue):
    while True:
        product = product_queue.get()
        if product == SENTINEL:
            print(id, ": Get SENTINEL")
            break
        else:
            print(id, ": Take product =", product)
            time.sleep(0.1)
    product_queue.task_done()


print("-" * 40)

product_queue = Queue()

consumer_thread = Thread(target=consumer, args=(1, product_queue,))
# consumer_thread.daemon = True
consumer_thread.start()

producer_thread = Thread(target=producer, args=(0, product_queue,))
# producer_thread.daemon = True
producer_thread.start()

product_queue.join()
producer_thread.join()
consumer_thread.join()

print("-" * 40)

if __name__ == '__main__':
    pass
