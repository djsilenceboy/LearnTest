'''
Created on Apr 10, 2016

@author: dj
'''

a = [num for num in range(10)]
print("a =", a)

b = {num for num in range(10)}
print("b =", b)

c1 = {'Cat': 1, 'Dog': 2, 'Chicken': 3}
print("c1 =", c1)

c2 = {id: name for name, id in c1.items()}
print("c2 =", c2)

print("-" * 40)

d = [x for x in a if x % 2 == 0]
print("d =", d)

e = [x for x in a if x % 2 == 0 if x > 4]
print("e =", e)

print("-" * 40)

matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
print("matrix =", matrix)

flat = [item for row in matrix for item in row]
print("flat =", flat)

convert = [[item ** 2 for item in row] for row in matrix]
print("convert =", convert)

filtered = [[item for item in row if item % 3 == 0]
            for row in matrix if sum(row) >= 10]
print("filtered =", filtered)

print("-" * 40)

# [[v for y in range(Y)] for x in range(X)]
matrix = [[y * 10 + x for x in range(10)] for y in range(10)]
# print(matrix)

for x in matrix:
    for y in x:
        print("{0:-4}".format(y), end="")
    print("")

print("-" * 40)

for x in range(10):
    for y in range(10):
        print("{0:-4}".format(matrix[x][y]), end="")
    print("")

print("-" * 40)

if __name__ == '__main__':
    pass
