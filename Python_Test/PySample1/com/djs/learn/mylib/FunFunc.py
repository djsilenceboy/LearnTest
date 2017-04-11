'''
Created on Apr 11, 2017

@author: dj
'''


def index_words(text):
    result = []

    if text:
        result.append(0)

    for index, letter in enumerate(text):
        if letter == ' ':
            result.append(index + 1)

    return result


def index_words_iter(text):
    if text:
        yield 0

    for index, letter in enumerate(text):
        if letter == ' ':
            yield index + 1


def main():
    print("-" * 40)

    text = "This is a sunny day"
    words_index = index_words(text)
    print("Text = ", text)
    print("Word index = ", words_index)

    print("-" * 40)

    words_index_iter = index_words_iter(text)

    for i in range(1, 4):
        print("Word index {0} = {1}".format(i, next(words_index_iter)))

    print("-" * 40)


if __name__ == '__main__':
    main()
