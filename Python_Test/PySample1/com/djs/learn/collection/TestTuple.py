'''
Created on Feb 29, 2016

@author: dj
'''

print("-" * 40)

blank_tuple1 = ()
blank_tuple2 = tuple()
print("blank_tuple1 () =", blank_tuple1)
print("blank_tuple2 tuple() =", blank_tuple2)

print("-" * 40)

single_wrong = ('Apple')
print("single wrong =", single_wrong)
print("single wrong =", type(single_wrong))

single = ('Apple',)
print("single =", single)
print("single =", type(single))

single2 = 'Apple',
print("single2 =", single2)

print("-" * 40)

many = 'Apple', 'Orange', 'Bananas'
print("many =", many)

apple, orange, bananas = many
print(apple, orange, bananas)

print("-" * 40)


if __name__ == '__main__':
    pass
