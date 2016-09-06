'''
Created on Mar 29, 2016

@author: djs
'''

# Could be List or Set.
list1 = [1, 2]
listA = ['A', 'B', 'C']

print("list1 =", list1)
print("listA =", listA)

print("-" * 40)

tuple1 = zip(list1, listA)

for item in tuple1:
    print(item)

print("-" * 20)

tuple2 = zip(list1, listA)

for val1, val2 in tuple2:
    print(val1, "-", val2)

print("-" * 40)

set1 = {1, 2}
set2 = {2, 3}

print("set1 =", set1)
print("set2 =", set2)

print("set1 | set2 =", set1 | set2)
print("set1 & set2 =", set1 & set2)
print("set1 - set2 =", set1 - set2)
print("set2 - set1 =", set2 - set1)
print("set1 ^ set2 =", set1 ^ set2)

print("-" * 40)

list3 = list(range(0, 4))

print("list3 =", list3)

print("-" * 40)

dict1 = dict([['A', 1], ['B', 2]])

print("dict1 =", dict1)

dict2 = dict([('A', 1), ('B', 2)])

print("dict2 =", dict2)

dict3 = dict((['A', 1], ['B', 2]))

print("dict3 =", dict3)

dict4 = dict((('A', 1), ('B', 2)))

print("dict3 =", dict4)


print("-" * 40)

if __name__ == '__main__':
    pass
