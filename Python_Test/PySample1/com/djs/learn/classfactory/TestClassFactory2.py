'''
Created on Oct 21, 2016

@author: dj
'''


def create_product_class(isBig=False):
    if isBig:
        keys = ["BigA", "BigB"]
    else:
        keys = ["SmallA"]

    class Product():
        expected_keys = set(keys)

        def __init__(self, **kwargs):
            print("Expected keys =", set(self.expected_keys))
            if self.expected_keys != set(kwargs.keys()):
                raise ValueError("Keys do not match.")
            for k, v in kwargs.items():
                setattr(self, k, v)

    return Product

print("-" * 40)

ProductA = create_product_class(True)
ProductB = create_product_class(False)

print("ProductA = ", ProductA)
print("ProductB = ", ProductB)

print("-" * 40)

product1 = ProductA(BigA=1, BigB=2)
product2 = ProductB(SmallA=3)

print("product1 = ", product1)
print("product2 = ", product2)

print("-" * 40)

try:
    product3 = ProductA(BigA=1)
except Exception as e:
    print("Exception: {}".format(e))

print("-" * 40)

if __name__ == '__main__':
    pass
