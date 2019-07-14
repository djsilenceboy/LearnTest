'''
Created on Feb 22, 2016

@author: dj
'''


sample = lambda x, y, z: x * y + z
print(sample(1, 2, 3))

print("-" * 40)

names = ["Jerry", "Tom", "Mary"]
print("names =", names)
names.sort(key=lambda s: len(s))
print("names =", names)

print("-" * 40)


if __name__ == '__main__':
    pass
