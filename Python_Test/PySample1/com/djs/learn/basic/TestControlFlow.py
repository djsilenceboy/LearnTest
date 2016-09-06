'''
Created on Mar 29, 2016

@author: djs
'''

my_condition = 1
if my_condition == 0:
    print("my_condition =", "Ok")
elif my_condition == 1:
    print("my_condition =", "Not bad")
elif my_condition == 2:
    print("my_condition =", "Good")
else:
    print("my_condition =", "So, so")

print("-" * 40)

numbers = list(range(0, 3))

number = 0
while number in numbers:
    if number == 2:
        break
    print("=", number)
    number += 1
else:
    print("while complete.")

print("-" * 40)

number2 = 0
while number2 in numbers:
    print("=", number2)
    number2 += 1
else:
    print("while complete.")

print("-" * 40)

for number3 in numbers:
    if number3 == 2:
        break
    print("=", number3)
else:
    print("for complete.")

print("-" * 40)

for number4 in numbers:
    print("=", number4)
else:
    print("for complete.")

print("-" * 40)

if __name__ == '__main__':
    pass
