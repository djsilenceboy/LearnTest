'''
Created on Apr 4, 2016

@author: dj
'''

import pickle


class Person(object):
    name = ""
    age = 0

    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __str__(self):
        return self.__class__.__name__ + ":{name}, {age}".format(**vars(self))


def main():
    personA = Person("Tom", 12)
    print("personA =", personA)

    print("-" * 40)

    serialized = pickle.dumps(personA)
    print("serialized =", serialized)

    print("-" * 40)

    personB = pickle.loads(serialized)
    print("personB =", personB)


if __name__ == '__main__':
    main()
