'''
Created on Apr 16, 2016

@author: dj
'''

'Directly call system function, can be multi-thread/process.'

import subprocess
from time import time


def run_sleep(no, period):
    print("start sleep #" + str(no))
    proc = subprocess.Popen(["sleep", str(period)])
    return proc


def test_proc_1():
    start = time()
    print("Start time (s) =", start)
    procs = []
    for i in range(10):
        proc = run_sleep(i, 3)
        procs.append(proc)

    for proc in procs:
        proc.communicate()
        # If want output.
        # out, err = proc.communicate()
        print("Exit =", proc.poll())

    stop = time()
    print("Stop time (s) =", stop)
    print("Duration time (s) =", stop - start)


def test_proc_2():
    proc = run_sleep(1, 2)
    start = time()
    print("Start time (s) =", start)

    try:
        proc.communicate(timeout=1)
        print("Succeeded")
    except subprocess.TimeoutExpired:
        proc.terminate()
        proc.wait()
        print("Failed")

    print("Exit =", proc.poll())
    stop = time()
    print("Stop time (s) =", stop)
    print("Duration time (s) =", stop - start)


print("-" * 40)

test_proc_1()

print("-" * 40)

test_proc_2()

print("-" * 40)

if __name__ == '__main__':
    pass
