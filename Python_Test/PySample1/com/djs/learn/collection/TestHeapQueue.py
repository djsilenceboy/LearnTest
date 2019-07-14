'''
Created on Apr 20, 2016

@author: dj
'''

from heapq import heappush

hp = []

# Each time, adding new element will cause heap to adjust.
# The rule: Both child nodes should be larger than parent node.

heappush(hp, 11)
print("hp = ", hp)

heappush(hp, 3)
print("hp = ", hp)

heappush(hp, 5)
print("hp = ", hp)

heappush(hp, 21)
print("hp = ", hp)

heappush(hp, 8)
print("hp = ", hp)

heappush(hp, 2)
print("hp = ", hp)

# Sort will rebuild heap from small to large.

hp.sort()
print("sort() = ", hp)

if __name__ == '__main__':
    pass
