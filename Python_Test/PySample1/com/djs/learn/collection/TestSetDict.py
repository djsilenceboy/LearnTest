'''
Created on Mar 1, 2016

@author: jiangdu
'''

# set and dict(key) saved order will be random.

print("-------------------- Set --------------------")

blank_set_wrong = {}
blank_set = set()

print("blank_set Wrong {} =", blank_set_wrong)
print("blank_set set() =", blank_set)

print("-" * 40)

fruits = set(['avacados', 'bananas', 'oranges', 'grapes', 'managos'])
print("fruits{} set() =", fruits)
fruits2 = {'avacados', 'bananas', 'oranges', 'grapes', 'managos'}
print("fruits2{} {} =", fruits2)

vegetables = set(['carrots', 'oranges', 'onions', 'managos', 'celery'])
print("vegetables{} =", vegetables)

print("-" * 40)

print("fruits - vegetables =", fruits - vegetables)
print("vegetables - fruits =", vegetables - fruits)

print("fruits | vegetables =", fruits | vegetables)
print("vegetables | fruits =", vegetables | fruits)

print("fruits & vegetables =", fruits & vegetables)
print("vegetables & fruits =", vegetables & fruits)

print("-" * 40)

words = "Hello, Kitty!"
print("words =", words)

letters1 = {letter for letter in words}
print("letters1 =", letters1)

letters2 = set(words)
print("letters2 =", letters2)

print("-------------------- Dict --------------------")

blank_dict1 = {}
blank_dict2 = dict()

print("blankDict 1 {} =", blank_dict1)
print("blankDict 2 dict() =", blank_dict2)

print("-" * 40)

animals = {'Cat': 1, 'Dog': 2, 'Chicken': 3}
animals2 = dict(Cat=1, Dog=2, Chicken=3)
print("animals {} =", animals)
print("animals2 dict() =", animals2)

print("-" * 40)

print("animals.keys() =", animals.keys())
print("animals.values() =", animals.values())
print("animals.items() =", animals.items())

print("max(animals) =", max(animals))
print("min(animals) =", min(animals))
print("len(animals) =", len(animals))

print("sorted(animals) =", sorted(animals))
print("sorted(animals.items()) =", sorted(animals.items()))

print("-" * 40)

print("animals['Dog'] = ", animals['Dog'])
print("animals.get('Tiger') = ", animals.get('Tiger'))
print("animals.get('Tiger', 'Not exist') = ",
      animals.get('Tiger', 'Not exist'))

print("-" * 40)

for key in animals:
    print(key, "=", animals.get(key))

print("-" * 40)

for key, value in animals.items():
    print(key, "=", value)

print("-" * 40)

animals3 = animals.fromkeys(animals)
print("animals3 .fromkeys(animals) =", animals3)

print("-" * 40)

animals4 = animals.fromkeys(animals, 0)
print("animals4 .fromkeys(animals, 0) =", animals4)

print("-" * 40)

animals.popitem()
print("animals.popitem() =", animals)

del animals2['Chicken']
print("del animals2['Chicken'] =", animals2)

animals2.pop('Dog')
print("animals2.pop('Dog') =", animals2)

animals2.clear()
print("animals2.clear() =", animals2)

print("-" * 40)

sentence = "This is a good day to work!"
print("sentence =", sentence)
characters = {}
print("characters{} =", characters)
for character in sentence:
    characters[character] = characters.get(character, 0) + 1
print("characters{} =", characters)
print("sorted(characters.items() =", sorted(characters.items()))

print("-" * 40)

# letter_count = {letter : sentence.count(letter) for letter in sentence}
letter_count = {letter: sentence.count(letter) for letter in set(sentence)}

print("letter_count =", letter_count)

print("-" * 40)


if __name__ == '__main__':
    pass
