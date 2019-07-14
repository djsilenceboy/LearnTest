'''
Created on Apr 20, 2016

@author: dj
'''


from collections import defaultdict

key = "Dog"
key2 = "Cat"

print("-" * 40)

a = {}
print("a =", a)

if key not in a:
    a[key] = 0
    print("a =", a)
a[key] += 2
print("a =", a)

try:
    a[key2] += 3
    print("a =", a)
except Exception as e:
    print("e =", e)
    pass

print("-" * 40)

# The default value for int in defaultdict() is 0.
b = defaultdict(int)

print("b =", b)

b[key] += 4
print("b =", b)

b[key2] += 5
print("b =", b)

print("-" * 40)

if __name__ == '__main__':
    pass
