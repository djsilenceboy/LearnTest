'''
Created on Feb 22, 2016

@author: dj
'''

from _operator import getitem


class Product(object):

    def __init__(self, price, strength, speed):
        self.price = price
        self.strength = strength
        self.speed = speed
        print("price = {0}, strength = {1}, speed = {2}".format(
            price, strength, speed))

    def __str__(self):
        return self.__class__.__name__ + ":[price = {price}, strength = {strength}, speed = {speed}]".format(**vars(self))

    def __len__(self):
        return len(vars(self))

    def __getitem__(self, key):
        names = vars(self)
        try:
            item = names[key]
        except:
            item = 0
        return item


class Weapon(Product):

    def __init__(self, price, strength, speed):
        super().__init__(price, strength, speed)
        self.damage = strength


class Armour(Product):

    def __init__(self, price, strength, speed):
        Product.__init__(self, price, strength, speed)
        self.defend = strength


class Word(object):

    def __init__(self, text):
        self.text = text
        print("Text is", text)

    def __str__(self):
        return self.text

    def __eq__(self, other):
        return self.text == other.text

    def __ne__(self, other):
        return self.text != other.text

    def __add__(self, other):
        return self.text + "|" + other.text


def main():
    thing = Product(1, 2, 3)
    print("Class instance = {0}".format(thing))
    print("len(0) = {1}".format(thing.__class__.__name__, len(thing)))

    print("Instance members = {0}".format(thing.__dict__))

    print("-" * 40)

    weapon = Weapon(11, 12, 13)
    print("Class instance = {0}".format(weapon))
    print("len(0) = {1}".format(weapon.__class__.__name__, len(weapon)))

    print("-" * 40)

    armour = Armour(21, 22, 23)
    print("Class instance = {0}".format(armour))
    print("armour.defend = {0}".format(armour.defend))
    print("armour[\"defend\"] = {0}".format(armour["defend"]))
    print("getitem(armour, \"defend\") = {0}".format(
        getitem(armour, "defend")))

    print("-" * 40)

    wordA = Word("Hello")
    wordB = Word("Hello")
    wordC = Word("HEllo")

    print(wordA, "==", wordB, "is", wordA == wordB)
    print(wordA, "==", wordC, "is", wordA == wordC)

    print(wordA, "!=", wordB, "is", wordA != wordB)
    print(wordA, "!=", wordC, "is", wordA != wordC)

    print(wordA, "+", wordC, "=", wordA + wordC)

    print("-" * 40)


if __name__ == '__main__':
    main()
