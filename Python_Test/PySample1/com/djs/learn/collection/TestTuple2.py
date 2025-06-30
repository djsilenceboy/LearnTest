people: tuple[str, ...] = ('aa', 'bb', 'cc', 'dd', 'ee')
first, last = people[0], people[-1]
print(first, last)
print("-" * 40)

first, *_, last = people
print(first, last)
print("-" * 40)

first, *_, last_2, last = people
print(first, last_2, last)
print("-" * 40)

first, second, *_, last = people
print(first, second, last)
print("-" * 40)

first, *_ = people
print(first)
print("-" * 40)

*_, last = people
print(last)
print("-" * 40)

first, *middle, last = people
print(first, middle, last)
print("-" * 40)

first, second, *middle, last = people
print(first, second, middle, last)
print("-" * 40)

first, *middle, last_2, last = people
print(first, middle, last_2, last)
print("-" * 40)
