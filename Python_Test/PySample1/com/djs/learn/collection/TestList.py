'''
Created on Feb 23, 2016

@author: djs
'''

print("-" * 40)

blank_list1 = []
blank_list2 = list()
print("blank_list1 [] =", blank_list1)
print("blank_list2 list() =", blank_list2)

print("-" * 40)


print("list('bananas') =", list('bananas'))

print("-" * 40)

fruits = ['avacados', 'bananas', 'oranges', 'grapes', 'managos']
print("fruits[] =", fruits)
print("fruits[2] =", fruits[2])
print("fruits[-1] =", fruits[-1])
print("fruits[2:-2] =", fruits[2:-2])
print("fruits[1:4:2] =", fruits[1:4:2])
print("fruits[4:4:2] =", fruits[4:4:2])
print("fruits[::-1] =", fruits[::-1])
print("'apple' in fruits =", 'apple' in fruits)
print("'apple' not in fruits =", 'apple' not in fruits)

print("-" * 40)

a = [num for num in range(10)]
print("a[original] =", a)

b = a[:]
print("b[clone_of_a] =", b)
print("a == b =", a == b)
print("a is b =", a is b)

b[1] = 99
print("a[original] =", a)
print("b[modified] =", b)

print("a[even] =", a[::2])
print("a[odd] =", a[1::2])
print("a[reverse] =", a[::-1])

print("a[upper_bound] =", a[:20])
print("a[lower_bound] =", a[-20:])
# IndexError: list index out of range
# print("a[out_of_index] =", a[20])

print("-" * 40)

fruits_names = " | ".join(fruits)
print("fruits_names[] =", fruits_names)

fruits2 = fruits_names.split(' | ')
print("fruits2[] =", fruits2)

print("-" * 40)

vegetable_names = "carrots, potatoes, onions, leeks, celery"
print("vegetable_names =", vegetable_names)
vegetables = vegetable_names.split(", ")
print("vegetables[] =", vegetables)

print("-" * 40)

print("min(fruits) =", min(fruits))
print("max(fruits) =", max(fruits))
print("min(vegetables) =", min(vegetables))
print("max(vegetables) =", max(vegetables))

print("-" * 40)

print("fruits > vegetables =", fruits > vegetables)
print("fruits == vegetables =", fruits == vegetables)
print("fruits < vegetables =", fruits < vegetables)

print("-" * 40)

inventory = fruits.copy()
inventory[::2] = ['carrots', 'potatoes', 'onions']
print("replace inventory[::2] =", inventory)

inventory.append('apple')
print("inventory.append() =", inventory)

inventory.extend(['leeks', 'celery'])
print("inventory.extend() =", inventory)

del inventory[5:]
print("del inventory[5:] =", inventory)

print("inventory.index('onions') =", inventory.index('onions'))

inventory.insert(4, 'apple')
print("inventory.insert() =", inventory)

inventory.remove('grapes')
print("inventory.remove() =", inventory)

inventory.pop(-2)
print("inventory.pop(-2) =", inventory)

inventory.pop()
print("inventory.pop() =", inventory)

print("-" * 40)

vegetables2 = sorted(vegetables)
print("sorted(vegetables) =", vegetables2)

vegetables2.reverse()
print("vegetables2.reverse() =", vegetables2)

vegetables3 = list(vegetables)
vegetables3.sort(reverse=True)
print("vegetables3.sort(reverse=True) =", vegetables3)

print("-" * 40)

numbers = [2, 1, 4.0, 3]
print("numbers =", numbers)

numbers.sort()
print("numbers.sort() =", numbers)

numbers.sort(reverse=True)
print("numbers.sort(reverse=True) =", numbers)

print("-" * 40)

for item in fruits:
    print(item)

print("-" * 40)

for i, item in enumerate(fruits):
    print(i, item)

print("-" * 40)

for i, item in enumerate(fruits, 1):
    print(i, item)

print("-" * 40)

vegetables.pop()

for fruit, vegetable in zip(fruits, vegetables):
    print(fruit, vegetable)

print("-" * 40)

print([fruit.upper() for fruit in fruits])
# [(x, y) for x in range(X) for y in range(Y)]
print([(fruit, vegetable) for fruit in fruits for vegetable in vegetables])
print([(fruit, vegetable)
       for fruit in fruits for vegetable in vegetables if fruit is 'avacados'])

print("-" * 40)


if __name__ == '__main__':
    pass
