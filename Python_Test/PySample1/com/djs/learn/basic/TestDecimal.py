'''
Created on Feb 23, 2016

@author: dj
'''

from decimal import Decimal, ROUND_UP


def test_normal(rate, seconds):
    cost = rate * seconds / 60
    print("{0:<4.2} x {1:3} / 60 = {2}".format(rate, seconds, cost))
    print("round(cost, 2)) =", round(cost, 2))


def test_decimal(rate, seconds):
    cost = Decimal(rate) * Decimal(seconds) / Decimal(60)
    print("{0:<4.2} x {1:3} / 60 = {2}".format(rate, seconds, cost))
    print("round(cost, 2)) =", cost.quantize(
        Decimal("0.01"), rounding=ROUND_UP))


print("-" * 40)

test_normal(1.45, 200)
test_normal(0.05, 5)

print("-" * 40)

test_decimal(1.45, 200)
test_decimal(0.05, 5)

print("-" * 40)

if __name__ == '__main__':
    pass
