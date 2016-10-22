'''
Created on Oct 21, 2016

@author: dj
'''


def create_product_class():
    class Product():

        def __init__(self, name):
            self.name = name

        def make(self):
            print("Make a", self.name)

    return Product

ProductA = create_product_class()
ProductB = create_product_class()

print("ProductA = ", ProductA)
print("ProductB = ", ProductB)
print("ProductA == ProductB = ", ProductA == ProductB)

print("-" * 40)

product1 = ProductA("Dog")
product2 = ProductB("Cat")

print("product1 = ", product1)
print("product2 = ", product2)

print("-" * 40)

product1.make()
product2.make()

print("-" * 40)

if __name__ == '__main__':
    pass
