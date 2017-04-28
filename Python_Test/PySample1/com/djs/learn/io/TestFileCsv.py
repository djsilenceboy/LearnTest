'''
Created on Apr 4, 2016

@author: dj
'''

import csv

with open('../../../etc/SampleInput.csv') as file1:
    print('file1 =', file1)
    cin = csv.reader(file1)
    animals = [line for line in cin]
    print("animals =", animals)

print("-" * 40)

with open('../../../etc/SampleOuput.csv', 'wt') as file2:
    print('file2 =', file2)
    cout = csv.writer(file2, lineterminator='\n')
    cout.writerows(animals)

print("-" * 40)

with open('../../../etc/SampleInput.csv') as file3:
    cin = csv.DictReader(file3, fieldnames=["Type", "Name"])
    animals2 = [line for line in cin]
    print("animals2 =", animals2)

print("-" * 40)

with open("../../../etc/SampleOuput2.csv", "wt") as file4:
    cout = csv.DictWriter(file4, ["Type", "Name"], lineterminator="\n")
    cout.writeheader()
    cout.writerows(animals2)

print("-" * 40)

if __name__ == '__main__':
    pass
