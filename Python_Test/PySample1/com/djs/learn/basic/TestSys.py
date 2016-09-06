'''
Created on Mar 4, 2016

@author: djs
'''

import sys

print("sys.argv =", sys.argv)

print("-" * 40)

for i, arg in enumerate(sys.argv):
    print("sys.argv[{0}] = {1}".format(i, arg))

print("-" * 40)

print("Input message and return: ")

message = sys.stdin.readline()

print("sys.stdin =", message)

print("-" * 40)

print("sys.stdout =")

message2 = "This is my message: {0}.\n".format(message.rstrip())

sys.stdout.writelines(message2)

print("-" * 40)

if __name__ == '__main__':
    pass