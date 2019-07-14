'''
Created on Apr 15, 2016

@author: dj
'''

# Use Meta class to help.


class Product(object):

    def __init__(self):
        self.name = None
        self.internal_name = None
        print("class, name, internal_name = {0}, {1}, {2}".format(
            self.__class__.__name__, self.name, self.internal_name))

    def __get__(self, instance, instance_type):
        print("__get__: internal_name, instance, instance_type = {0}, {1}, {2}".format(
            self.internal_name, instance, instance_type))
        if instance is None:
            return self
        return getattr(instance, self.internal_name, "")

    def __set__(self, instance, value):
        print("__set__: internal_name, instance, value = {0}, {1}, {2}".format(
            self.internal_name, instance, value))
        setattr(instance, self.internal_name, value)


class Meta(type):
    def __new__(cls, name, baseclass, class_dict):
        for key, value in class_dict.items():
            if isinstance(value, Product):
                value.name = key
                value.internal_name = "_" + key
        return super().__new__(cls, name, baseclass, class_dict)


class ProductWrapper(object, metaclass=Meta):
    pass


class House(ProductWrapper):
    door = Product()
    window = Product()


house1 = House()

print("-" * 40)

print("house1.door =", house1.door)
print("house1.window =", house1.window)
print("house1.__dict__ =", house1.__dict__)

print("-" * 40)

house1.door = "SingleDoor"
print("house1.door =", house1.door)
print("house1.__dict__ =", house1.__dict__)

print("-" * 40)

house1.window = "DoubleWindow"
print("house1.window =", house1.window)
print("house1.__dict__ =", house1.__dict__)


if __name__ == '__main__':
    pass
