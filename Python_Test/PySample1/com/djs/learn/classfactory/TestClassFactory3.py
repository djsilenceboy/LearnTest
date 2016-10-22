'''
Created on Oct 21, 2016

@author: dj
'''


def create_product_class(new_name):
    class Product():
        name = new_name

        def make(self):
            print("Make a", Product.name)

    return Product

ProductA = create_product_class("Cat")
ProductB = create_product_class("Dog")

print("ProductA = ", ProductA)
print("ProductB = ", ProductB)

print("-" * 40)

product1 = ProductA()
product2 = ProductB()

print("product1 = ", product1)
print("product2 = ", product2)

print("-" * 40)

product1.make()
product2.make()

print("-" * 40)

if __name__ == '__main__':
    pass
