
class ProductBase(object):

    def __init__(self, name):
        self.name = name
        print("ProductBase:name = {0}".format(self.name))

    def print(self):
        pass


class ProductImpl(ProductBase):

    def __init__(self, name):
        self.name = name

    def print(self):
        print("ProductImplA:name = {0}".format(self.name))


class ProductFinalA(ProductImpl):

    def __init__(self, name):
        self.name = name

    def print(self):
        print("ProductFinalA:name = {0}".format(self.name))


class ProductFinalB(ProductImpl):

    def __init__(self, name):
        self.name = name


def print_name(obj: ProductBase) -> None:
    obj.print()


print("-" * 40)

product1 = ProductImpl("ProductImplA")
product1.print()

print("-" * 40)

product2 = ProductFinalA("ProductFinalA")
product2.print()

print("-" * 40)

product3 = ProductFinalB("ProductFinalB")
product3.print()

print("-" * 40)

print_name(product1)

print("-" * 40)

print_name(product2)

print("-" * 40)

print_name(product3)

print("-" * 40)


if __name__ == '__main__':
    pass
