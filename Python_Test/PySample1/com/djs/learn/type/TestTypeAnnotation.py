
a: int = 5
print("a =", a)

a = "hello"
print("a =", a)


def do_sum_1(a, b):
    return a + b

def do_sum_2(a: int, b: int) -> int:
    return a + b

def do_sum_3(a: int, b: int) -> int:
    return str(a + b)

print("do_sum_1 =", do_sum_1(1, 2))

# print("do_sum_1 =", do_sum_1(1, "2"))
# TypeError: unsupported operand type(s) for +: 'int' and 'str'

print("do_sum_2 =", do_sum_2(1, 2))

# print("do_sum_2 =", do_sum_2(1, "2"))
# TypeError: unsupported operand type(s) for +: 'int' and 'str'

print("do_sum_3 =", do_sum_3(1, 2))


# Run following command to check.
# $ mypy TestTypeAnnotation.py
# TestTypeAnnotation.py:7: error: Incompatible types in assignment (expression has type "str", variable has type "int")  [assignment]
# TestTypeAnnotation.py:18: error: Incompatible return value type (got "str", expected "int")  [return-value]
# Found 2 errors in 1 file (checked 1 source file)
