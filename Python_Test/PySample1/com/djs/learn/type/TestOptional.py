from typing import Optional

a = None
b = 1
c: Optional[int] = None
d: Optional[int] = 1

print("type(a) =", type(a))
print("type(b) =", type(b))
print("type(c) =", type(c))
print("type(d) =", type(d))

def checkNu m_1(i: int) -> int:
   return i

def checkNum_2(i: Optional[int]) -> Optional[int]:
   return i

print("checkNum_1 (", a, ") =", checkNum_1(a))
print("checkNum_1 (", b, ") =", checkNum_1(b))

print("checkNum_2 (", c, ") =", checkNum_2(c))
print("checkNum_2 (", d, ") =", checkNum_2(d))


# Run following command to check.
# $ mypy TestOptional.py
# TestOptional.py:14: error: Argument 1 to "checkNum_1" has incompatible type "None"; expected "int"  [arg-type]
# Found 1 error in 1 file (checked 1 source file)
