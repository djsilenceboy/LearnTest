'''
Created on Apr 15, 2016

@author: jiangdu
'''

import json


registry = {}


def register_class(target_class):
    print("register_class: class name =", target_class.__name__)
    registry[target_class.__name__] = target_class


class Serializable(object):

    def __init__(self, *args):
        print("Serializable: {0}.{1}".format(self.__class__.__name__, args))
        self.args = args

    def serialize(self):
        return json.dumps({
            "class": self.__class__.__name__,
            "args": self.args})


def deserialize(data):
    params = json.loads(data)
    print("deserialize: params =", params)
    name = params["class"]
    target_class = registry[name]
    return target_class(*params["args"])


class Meta(type):

    def __new__(meta, name, baseclass, class_dict):
        print("Meta.meta = {}".format(meta))
        print("Meta.name = {}".format(name))
        print("Meta.baseclass = {}".format(baseclass))
        print("Meta.class_dict = {}".format(class_dict))
        cls = super().__new__(meta, name, baseclass, class_dict)
        register_class(cls)
        return cls


class RegisteredSerializable(Serializable, metaclass=Meta):
    pass


class Point3D(RegisteredSerializable):

    def __init__(self, x, y, z):
        super().__init__(x, y, z)
        self.x = x
        self.y = y
        self.z = z
        print("Point3D: x, y, z = {}, {}, {}".format(x, y, z))


point1 = Point3D(10, 11, 12)
print("point1 =", point1)

print("-" * 40)

data = point1.serialize()
print("data =", data)

print("-" * 40)

point2 = deserialize(data)
print("point2 =", point2)

print("-" * 40)

point3 = Point3D(100, 111, 122)
print("point3 =", point3)


if __name__ == '__main__':
    pass
