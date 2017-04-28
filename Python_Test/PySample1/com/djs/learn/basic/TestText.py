'''
Created on Mar 2, 2016

@author: dj
'''

from string import capwords

print("-" * 40)

text0 = "a" + "b"
print("text0 =", text0)

print("-" * 40)

text1 = "This is a long working day"

print("text1 =", text1)

print("text1.strip() =", text1.strip('This'))
print("text1.rstrip() =", text1.strip('day'))

print("text1.split() =", text1.split(' '))
print("text1.split(,2) =", text1.split(' ', 2))
print("text1.rsplit(,2) =", text1.rsplit(' ', 2))
print("text1.partition() =", text1.partition(' '))
print("text1.rpartition() =", text1.rpartition(' '))

print("-" * 40)

print("text1.count() =", text1.count('a'))
print("text1.replace() =", text1.replace('a', 'A').replace('i', 'I'))

print("text1.find() =", text1.find('a'))
print("text1.rfind() =", text1.rfind('a'))
print("text1.index() =", text1.index('a'))
print("text1.rindex() =", text1.rindex('a'))

print("-" * 40)

text2 = "eXamPLe tEXt It's O'clock"

print("text2 =", text2)
print("text2.capitalize() =", text2.capitalize())
print("text2.title() =", text2.title())
print("capwords(text2) =", capwords(text2))
print("text2.lower() =", text2.lower())
print("text2.upper() =", text2.upper())
print("text2.swapcase() =", text2.swapcase())

print("-" * 40)

print("join list =", '-'.join(['This', 'is', 'a', 'long', 'working', 'day']))
print("join string =", '-'.join('This is'))

print("-" * 40)

text3 = "Example Text"

print("text3 =", text3)
print("text3.center() =", text3.center(40))
print("text3.ljust() =", text3.ljust(40))
print("text3.rjust() =", text3.rjust(40))
print("text3.center(*) =", text3.center(40, '*'))
print("text3.ljust(*) =", text3.ljust(40, '*'))
print("text3.rjust(*) =", text3.rjust(40, '*'))
print("text3.zfill() =", text3.zfill(40))

print("-" * 40)

text4 = "123\tABCD\t12345\tABCDEF\t1"

print("text4 =", text4)

for i in range(8, 0, -1):
    print("text4.expandtabs({0}) = {1}".format(i, text4.expandtabs(i)))

print("-" * 40)

print("format{0} =", "{0}".format(123))
print("format{0} =", "{0}".format('abc'))
print("format{0!s} =", "{0!s}".format(123))
print("format{0!r} =", "{0!r}".format('abc'))

print("-" * 40)


def show_format_just(value):
    for c in ['^', '<', '>', '=']:
        format_str = "".join(['{0:*', c, '10}'])
        print("format{0} = {1}".format(format_str, format_str.format(value)))


show_format_just(123)
print("-" * 20)
show_format_just(-123)
print("-" * 20)
show_format_just(123.45)
print("-" * 20)
show_format_just(-123.45)

print("-" * 40)


def show_precise(width, value):
    for i in range(1, 7):
        format_str = "".join(['{0:', str(width), '.', str(i), '}'])
        print("format{0} = {1}".format(format_str, format_str.format(value)))


show_precise(6, 123.45)
print("-" * 20)
show_precise(6, -123.45)
print("-" * 20)
show_precise(10, 123.45)
print("-" * 20)
show_precise(10, -123.45)

print("-" * 40)

if __name__ == '__main__':
    pass
