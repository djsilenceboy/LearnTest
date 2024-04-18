
from typing import Literal


print("-" * 40)

fruits = ['avacados', 'bananas', 'oranges', 'grapes']

print("fruits[] =", fruits)

fruits.append('managos')

print("fruits[] =", fruits)


print("-" * 40)

fruits2 = Literal['avacados', 'bananas', 'oranges', 'grapes']

print("fruits2[] =", fruits2)

# fruits2.append('managos')

print("-" * 40)


if __name__ == '__main__':
    pass
