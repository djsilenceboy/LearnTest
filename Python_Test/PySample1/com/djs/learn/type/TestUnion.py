
from typing import Union

class Movie():
    pass

def checkObj(obj) -> Union[Movie, None]:
    if obj is None:
       return obj
    return Movie()

print("-" * 40)

obj1: Union[Movie, None] = Movie()
obj2: Union[Movie, None] = None

print("obj1 =", obj1)
print("obj2 =", obj2)

print("-" * 40)

print("obj1 =", checkObj(obj1))
print("obj2 =", checkObj(obj2))

print("-" * 40)

# Run following command to check.
# $ mypy TestUnion.py
