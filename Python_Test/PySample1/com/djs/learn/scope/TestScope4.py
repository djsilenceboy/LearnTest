'''
Created on Oct 22, 2016

@author: dj
'''


class Product():
    name = "Cat"


def main():
    print("-" * 40)

    print("Product.name =", Product.name)

    print("-" * 40)

    productA = Product()
    productB = Product()

    print("productA.name =", productA.name)
    print("productB.name =", productB.name)

    print("productA.__dict__ =", productA.__dict__)
    print("productB.__dict__ =", productB.__dict__)

    print("-" * 40)

    # This action create a new local member "name", which hidden the class one.
    productA.name = "Dog"

    print("Product.name =", Product.name)
    print("productA.name =", productA.name)
    print("productB.name =", productB.name)

    print("productA.__dict__ =", productA.__dict__)
    print("productB.__dict__ =", productB.__dict__)

    print("-" * 40)

    Product.name = "Tiger"

    print("Product.name =", Product.name)
    print("productA.name =", productA.name)
    print("productB.name =", productB.name)

    print("-" * 40)

if __name__ == '__main__':
    main()
