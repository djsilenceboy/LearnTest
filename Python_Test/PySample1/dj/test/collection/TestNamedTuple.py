'''
Created on Apr 1, 2016

@author: dj
'''

from collections import namedtuple

print("-" * 40)

Person = namedtuple("Person", "name age gender")

tom = Person("Tom", 10, "M")
print("Tom is", tom)

jerry = Person(gender="M", name="Jerry", age=8)
print("Jerry is", jerry)

mary_info = {"name": "Mary", "age": 15, "gender": "F"}
mary = Person(**mary_info)
print("Mary is", mary)

print("-" * 40)

tom2 = tom._replace(age=11)
print("Now, Tom is", tom2)

print("-" * 40)

if __name__ == '__main__':
    pass
