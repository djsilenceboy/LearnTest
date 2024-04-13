'''
Created on May 2, 2017

@author: dj
'''

# This import also import APIs defined in "__all__" in "__init__.py".
from bitwise import *

d1 = 0b0101
d2 = 0b1110

# "NOT" function can be "see" directly.
print("NOT(d1) =", bin(NOT(d1)))

# "AND" function cannot be "see" directly.
# print(bin(AND(d1, d2)))
# "AND" function can only be "see" indirectly.
print("AND(d1,d2) =", bin(Bits.AND(d1, d2)))

bm1 = BitMask(d1)
print("BitMask(bm1) =", bin(bm1))


if __name__ == '__main__':
    pass
