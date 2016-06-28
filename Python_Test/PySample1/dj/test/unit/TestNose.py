'''
Created on Apr 8, 2016

@author: jiangdu
'''

from nose.tools import eq_
from dj.test.unit import TestSample as cap


MULTIPLE_WORDS_SOURCE = "this is a good day"
MULTIPLE_WORDS_RESULT = "This Is A Good Day"

MULTIPLE_WORDS_WITH_APOSTROPHES_SOURCE = "it's ten o'clock"
MULTIPLE_WORDS_WITH_APOSTROPHES_RESULT = "It's Ten O'clock"


def testOneWord():
    print("testOneWord")
    text = "duck"
    result = cap.cap_text(text)
    eq_(result, "Duck")


def testMultipleWords_1():
    print("testMultipleWords_1")
    result = cap.cap_text(MULTIPLE_WORDS_SOURCE)
    eq_(result, MULTIPLE_WORDS_RESULT)


def testMultipleWords_2():
    print("testMultipleWords_2")
    result = cap.title_text(MULTIPLE_WORDS_SOURCE)
    eq_(result, MULTIPLE_WORDS_RESULT)


def testMultipleWords_3():
    print("testMultipleWords_3")
    result = cap.capwords_text(MULTIPLE_WORDS_SOURCE)
    eq_(result, MULTIPLE_WORDS_RESULT)


def testMultipleWordsWithApostraphes_1():
    print("testMultipleWordsWithApostraphes_1")
    result = cap.title_text(MULTIPLE_WORDS_WITH_APOSTROPHES_SOURCE)
    eq_(result, MULTIPLE_WORDS_WITH_APOSTROPHES_RESULT)


def testMultipleWordsWithApostraphes_2():
    print("testMultipleWordsWithApostraphes_2")
    result = cap.capwords_text(MULTIPLE_WORDS_WITH_APOSTROPHES_SOURCE)
    eq_(result, MULTIPLE_WORDS_WITH_APOSTROPHES_RESULT)


if __name__ == "__main__":
    pass
