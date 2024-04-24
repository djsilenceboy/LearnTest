'''
Created on Apr 8, 2016

@author: dj
'''

import unittest
import TestSample as cap


MULTIPLE_WORDS_SOURCE = "this is a good day"
MULTIPLE_WORDS_RESULT = "This Is A Good Day"

MULTIPLE_WORDS_WITH_APOSTROPHES_SOURCE = "it's ten o'clock"
MULTIPLE_WORDS_WITH_APOSTROPHES_RESULT = "It's Ten O'clock"


class TestCap(unittest.TestCase):

    def setUp(self):
        print("Do setup...")

    def tearDown(self):
        print("Do tear down...")

    def test_one_word(self):
        print("test_one_word")
        text = "duck"
        result = cap.cap_text(text)
        self.assertEqual(result, "Duck")

    def test_multiple_words_1(self):
        print("test_multiple_words_1")
        result = cap.cap_text(MULTIPLE_WORDS_SOURCE)
        self.assertEqual(result, MULTIPLE_WORDS_RESULT)

    def test_multiple_words_2(self):
        print("test_multiple_words_2")
        result = cap.title_text(MULTIPLE_WORDS_SOURCE)
        self.assertEqual(result, MULTIPLE_WORDS_RESULT)

    def test_multiple_words_3(self):
        print("test_multiple_words_3")
        result = cap.capwords_text(MULTIPLE_WORDS_SOURCE)
        self.assertEqual(result, MULTIPLE_WORDS_RESULT)

    def test_multiple_words_with_apostraphes_1(self):
        print("test_multiple_words_with_apostraphes_1")
        result = cap.title_text(MULTIPLE_WORDS_WITH_APOSTROPHES_SOURCE)
        self.assertEqual(result, MULTIPLE_WORDS_WITH_APOSTROPHES_RESULT)

    def test_multiple_words_with_apostraphes_2(self):
        print("test_multiple_words_with_apostraphes_2")
        result = cap.capwords_text(MULTIPLE_WORDS_WITH_APOSTROPHES_SOURCE)
        self.assertEqual(result, MULTIPLE_WORDS_WITH_APOSTROPHES_RESULT)

    def test_error_1(self):
        print("test_error_1")
        self.assertRaises(ValueError, cap.some_error)

    def test_error_2(self):
        print("test_error_2")
        self.assertRaises(TypeError, cap.some_error)

    @unittest.skipIf(True, "Let's pass it.")
    def test_skipIf_1(self):
        print("test_skipIf_1")

    @unittest.skipIf(False, "Let's do it.")
    def test_skipIf_2(self):
        print("test_skipIf_2")

    @unittest.skipUnless(False, "Let's pass it too.")
    def test_skipUnless_1(self):
        print("test_skipUnless_1")

    @unittest.skipUnless(True, "Let's do it too.")
    def test_skipUnless_2(self):
        print("test_skipUnless_2")

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'TestCap.test_one_word']
    unittest.main()
