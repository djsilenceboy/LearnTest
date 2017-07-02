'''
Created on Apr 4, 2016

@author: dj
'''

import csv
from os import path

input_file_path = "../../../../etc"
output_file_path = "../../../../Temp"


with open(path.join(input_file_path, "SampleInput.csv")) as fileR1:
    print('fileR1 =', fileR1)
    cin = csv.reader(fileR1)
    recordR1 = [line for line in cin]
    print("recordR1 =", recordR1)

print("-" * 40)

with open(path.join(output_file_path, "SampleOutput.csv"), "wt") as fileW1:
    print("fileW1 =", fileW1)
    cout = csv.writer(fileW1, lineterminator="\n")
    cout.writerows(recordR1)

print("-" * 40)

with open(path.join(input_file_path, "SampleInput.csv")) as fileR2:
    print('fileR2 =', fileR2)
    cin = csv.DictReader(fileR2)
    print("fieldnames =", cin.fieldnames)
    # There is no header line record.
    recordR2 = [line for line in cin]
    print("recordR2 =", recordR2)

print("-" * 40)

with open(path.join(output_file_path, "SampleOutput2.csv"), "wt") as fileW2:
    print('fileW2 =', fileW2)
    cout = csv.DictWriter(fileW2, ["Type", "Name"], lineterminator="\n")
    # Write header line, if needed.
    cout.writeheader()
    cout.writerows(recordR2)

print("-" * 40)

with open(path.join(input_file_path, "SampleInput.csv")) as fileR3:
    print('fileR3 =', fileR3)
    cin = csv.DictReader(fileR3, fieldnames=["Type", "Name"])
    print("fieldnames =", cin.fieldnames)
    # There is a header line record.
    recordR3 = [line for line in cin]
    print("recordR3 =", recordR3)

print("-" * 40)

with open(path.join(output_file_path, "SampleOutput3.csv"), "wt") as fileW3:
    print('fileW3 =', fileW3)
    cout = csv.DictWriter(fileW3, ["Type", "Name"], lineterminator="\n")
    # writerows() will also write header line.
    cout.writerows(recordR3)

print("-" * 40)

if __name__ == '__main__':
    pass
