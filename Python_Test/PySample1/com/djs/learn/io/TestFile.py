'''
Created on Mar 2, 2016

@author: dj
'''

import glob
import os

input_file_path = "../../../../etc"
output_file_path = "../../../../Temp"

print("-" * 40)

with open(os.path.join(input_file_path, "SampleInput.txt")) as file1:
    print("file1 =", file1)

    for line in file1:
        print(line, end = '')

print("-" * 40)

with open(os.path.join(input_file_path, "SampleInput.txt"), "rb") as file2:
    print("file2 =", file2)

    for line in file2:
        print(line)

print("-" * 40)

file3 = open(os.path.join(input_file_path, "SampleInput.txt"))

print(file3.readline(), end = "")
print("-" * 20)
print(file3.read(), end = "")

print("-" * 20)

file3.seek(0)

print(file3.readline(6))
print("-" * 20)
print(file3.read(10))

file3.close()

print("-" * 40)

fileA = open(os.path.join(output_file_path, "SampleOutput.txt"), "w")

print("fileA.tell() =", fileA.tell())
fileA.write("Apple is not an orange.\n")
print("fileA.tell() =", fileA.tell())

fileA.close()

fileB = open(os.path.join(output_file_path, "SampleOutput.txt"), "a")

print("fileB.tell() =", fileB.tell())
fileB.write("Cat is not a dog.\n")
print("fileB.tell() =", fileB.tell())

fileB.close()

print("-" * 40)

file5 = open(os.path.join(output_file_path, "SampleOutput.txt"), "rb")

print(file5.read())

file5.close()

print("-" * 40)

file_list = os.listdir(input_file_path)
print("file_list = ", file_list)

print("-" * 40)

file_glob = glob.glob(os.path.join(input_file_path, "*.txt"))
print("file_glob (no change dir) = ", file_glob)

print("-" * 40)

os.chdir(input_file_path)
file_glob = glob.glob("*.txt")
print("file_glob (change dir) = ", file_glob)

print("-" * 40)

if __name__ == '__main__':
    pass
