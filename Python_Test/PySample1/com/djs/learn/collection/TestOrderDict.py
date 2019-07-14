'''
Created on Apr 20, 2016

@author: dj
'''

from collections import OrderedDict

# set and dict(key) saved order will be random.
a = {}
a["foo"] = 1
a["bar"] = 3
a["dog"] = 2

print("a =", a)

# OrderedDict saved order as created order.
b = OrderedDict()
b["foo"] = 1
b["bar"] = 3
b["dog"] = 2

print("b =", b)

# But they are equal if with same contents!
print("a == b?", a == b)

if __name__ == '__main__':
    pass
