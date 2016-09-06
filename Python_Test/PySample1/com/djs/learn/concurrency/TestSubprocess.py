'''
Created on Apr 16, 2016

@author: djs
'''

'Directly call system function, can be multi-thread/process.'

import subprocess
from time import time


def run_sleep(no, period):
    print("start sleep #" + str(no))
    proc = subprocess.Popen(["sleep", str(period)])
    return proc


start = time()
print("start time (s) =", start)
procs = []
for i in range(10):
    proc = run_sleep(i, 3)
    procs.append(proc)

for proc in procs:
    proc.communicate()
    # out, err = proc.communicate()
    print("Exit =", proc.poll())
stop = time()
print("stop time (s) =", stop)
print("duration time (s) =", stop - start)

print()

if __name__ == '__main__':
    pass
