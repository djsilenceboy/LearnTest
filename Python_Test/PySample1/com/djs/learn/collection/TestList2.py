numbers: list[int] = [1, 2, 3, 4, 5, 6, 7]

*other, y, z = numbers
print(other, y, z)
print("-" * 40)

a, b, *other, y, z = numbers
print(a, b, other, y, z)
print("-" * 40)
