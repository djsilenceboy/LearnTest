'''
Created on Apr 15, 2016

@author: djs
'''


class Product(object):

    def __init__(self, name):
        self.name = name
        self.internal_name = "_" + name
        print("class, name, internal_name = {0}, {1}, {2}".format(
            self.__class__.__name__, self.name, self.internal_name))

    def __get__(self, instance, instance_type):
        print("__get__: instance, instance_type = {0}, {1}".format(
            instance, instance_type))
        if instance is None:
            return self
        return getattr(instance, self.internal_name, "")

    def __set__(self, instance, value):
        print("__set__: instance, value = {0}, {1}".format(
            instance, value))
        setattr(instance, self.internal_name, value)


class House(object):
    door = Product("Door")

product1 = House()

print("-" * 40)

print("product1.door =", product1.door)
print("product1.__dict__ =", product1.__dict__)

print("-" * 40)

product1.door = 12
print("product1.door =", product1.door)
print("product1.__dict__ =", product1.__dict__)

print("-" * 40)

product1.door = 22
print("product1.door =", product1.door)
print("product1.__dict__ =", product1.__dict__)


if __name__ == '__main__':
    pass
