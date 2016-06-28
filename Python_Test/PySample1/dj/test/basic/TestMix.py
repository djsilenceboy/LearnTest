'''
Created on Feb 23, 2016

@author: dj
'''

i = 3
x = eval("i > 5")
print('i = {0}, x = {1}'.format(i, x))

print("-" * 40)

alist = ['apple', 'orange']
exec("print('alist =', alist)")
exec("print('alist = {0}'.format(alist))")

print("-" * 40)

a, b = 0, 1
_, d = 2, 3

print(a, b, d)

print("-" * 40)

print("5 =", 5)
print("'5' =", '5')
print("_repr_(5) = {!r}".format(5))
print("_repr_('5') = {!r}".format('5'))

print("-" * 40)

if __name__ == '__main__':
    pass
