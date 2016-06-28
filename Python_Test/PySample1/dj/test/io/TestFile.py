'''
Created on Mar 2, 2016

@author: dj
'''

with open('../../../etc/SampleInput.txt') as file1:
    print('file1 =', file1)

    for line in file1:
        print(line, end='')

print("-" * 40)

with open('../../../etc/SampleInput.txt', 'rb') as file2:
    print('file2 =', file2)

    for line in file2:
        print(line)

print("-" * 40)

file3 = open("../../../etc/SampleInput.txt")

print(file3.readline(), end="")
print("-" * 20)
print(file3.read(), end="")

print("-" * 20)

file3.seek(0)

print(file3.readline(6))
print("-" * 20)
print(file3.read(10))

file3.close()

print("-" * 40)

fileA = open("../../../etc/SampleOuput.txt", "w")

print("fileA.tell() =", fileA.tell())
fileA.write("Apple is not an orange.\n")
print("fileA.tell() =", fileA.tell())

fileA.close()

fileB = open("../../../etc/SampleOuput.txt", "a")

print("fileB.tell() =", fileB.tell())
fileB.write("Cat is not a dog.\n")
print("fileB.tell() =", fileB.tell())

fileB.close()

print("-" * 40)

file5 = open("../../../etc/SampleOuput.txt", "rb")

print(file5.read())

file5.close()

print("-" * 40)

if __name__ == '__main__':
    pass
