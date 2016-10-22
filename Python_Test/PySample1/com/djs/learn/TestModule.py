'''
Created on Feb 23, 2016

@author: djs
'''

from random import random

from com.djs.learn.bitwise import Bits as Bits
from com.djs.learn.bitwise.Bitmask import BitMask


print("Hello World")
print(random())

print("-" * 40)

print(Bits)
print(BitMask)

print("-" * 40)

d1 = 0b0101
d2 = 0b1110

print(bin(d1))
print(bin(d2))
print(Bits.list_bits(d1))
print(Bits.list_bits(d2))

print(bin(Bits.NOT(d1)))
print(bin(Bits.AND(d1, d2)))
print(bin(Bits.OR(d1, d2)))
print(bin(Bits.XOR(d1, d2)))

print("-" * 40)

bm0 = BitMask()
bm1 = BitMask(d1)
bm2 = BitMask(d2)

print(bin(bm0))
print(bin(bm1))
print(bin(bm2))
print(bm1.list_bits())
print(bm2.list_bits())

print(bin(bm1.NOT()))
print(bin(bm1.AND(bm2)))
print(bin(bm1.OR(bm2)))
print(bin(bm1.XOR(bm2)))

print("-" * 40)

print(oct(d1))
print(hex(d1))


if __name__ == '__main__':
    pass
