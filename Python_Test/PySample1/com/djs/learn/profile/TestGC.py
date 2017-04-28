'''
Created on Apr 20, 2016

@author: dj
'''

import gc


found_objects = gc.get_objects()
print("objects = ", len(found_objects))
x = []
y = {}
found_objects = gc.get_objects()
print("objects = ", len(found_objects))

print("-" * 40)

print("Top 10")
print("----------")
for obj in found_objects[:10]:
    print(repr(obj))


if __name__ == '__main__':
    pass
