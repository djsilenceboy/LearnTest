'''
Created on Oct 19, 2016

@author: dj
'''


def init(self, name):
    self.name = name


def make(self):
    print("Make a", self.name)

Product = type("Product", (object,), {
    "__doc__": "Just a simple product.", "__init__": init, "make": make})

ProductB = type("ProductB", (object,), {
    "__doc__": "Just a simple product.", "__init__": init, "make": make})

print("-" * 40)

print("type(Product) =", type(Product))
print("Product = ", Product)

print("-" * 40)

prodA = Product("Phone")
print("type(prodA) =", type(prodA))
print("prodA = ", prodA)

prodA.make()

print("-" * 40)


class ProductC(Product, ProductB):
    pass

print("type(ProductC) =", type(ProductC))

print("-" * 40)

if __name__ == '__main__':
    pass
