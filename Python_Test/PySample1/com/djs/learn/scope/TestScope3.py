'''
Created on Mar 2, 2016

@author: dj
'''

# In class, the member with double underscore prefix, such as "__value",
# is private. It will not be overrided.


class TestA(object):

    def __init__(self):
        self._value = 5
        self.__value = 5
        print("TestA:_value =", self._value)
        print("TestA:__value =", self.__value)

    def get_value(self):
        return self._value, self.__value


class TestB(TestA):

    def __init__(self):
        super().__init__()
        self._value = 10
        self.__value = 10
        print("TestB:_value =", self._value)
        print("TestB:__value =", self.__value)

    def get_valueB(self):
        return self._value, self.__value


objB = TestB()

print("-" * 40)

print("objB.get_value() =", objB.get_value())
print("objB.get_valueB() =", objB.get_valueB())

print("-" * 40)

print("objB._value =", objB._value)

# This line will cause error:
# AttributeError: 'TestB' object has no attribute '__value'
# Because "__value" is private.
try:
    print("objB.__value =", objB.__value)
except Exception as e:
    print("Exception: {}".format(e))

print("-" * 40)

# This is the way to access private member.
print("objB._TestA__value =", objB._TestA__value)
print("objB._TestB__value =", objB._TestB__value)

print("-" * 40)


if __name__ == '__main__':
    pass
