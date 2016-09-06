'''
Created on Apr 20, 2016

@author: djs
'''

from collections import OrderedDict

a = {}
a["foo"] = 1
a["bar"] = 3

print("a =", a)

b = OrderedDict()
b["foo"] = 2
b["bar"] = 4

print("a =", a)
print("b =", b)

if __name__ == '__main__':
    pass
