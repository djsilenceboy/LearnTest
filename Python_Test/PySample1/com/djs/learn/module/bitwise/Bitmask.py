#! /bin/env python3
''' Class that represents a bit mask.
It has methods representing all
the bitwise operations plus some
additional features. The methods
return a new BitMask object or
a boolean result. See the bits
module for more on the operations
provided.
'''

# For testing API, "BitMask" API can be "see".
__all__ = ['BitMask']


class BitMask(int):

    def AND(self, bm):
        return BitMask(self & bm)

    def OR(self, bm):
        return BitMask(self | bm)

    def XOR(self, bm):
        return BitMask(self ^ bm)

    def NOT(self):
        return BitMask(~self)

    def shift_left(self, num):
        return BitMask(self << num)

    def shift_right(self, num):
        return BitMask(self >> num)

    def bit(self, num):
        mask = 1 << num
        return bool(self & mask)

    def set_bit(self, num):
        mask = 1 << num
        return BitMask(self | mask)

    def zero_bit(self, num):
        mask = ~(1 << num)
        return BitMask(self & mask)

    def list_bits(self, start=0, end=None):
        if end:
            end = end if end < 0 else end + 2
        return [int(c) for c in bin(self)[start + 2:end]]
