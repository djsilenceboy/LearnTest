'''
@author: Du Jiang
https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
'''


class Solution:
    __number_letter_dict = {"0": "", "1": "", "2": "abc", "3": "def",
                            "4": "ghi", "5": "jkl", "6": "mno", "7": "pqrs", "8": "tuv", "9": "wxyz"}

    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        if len(digits) == 0:
            return []

        a_list = list(Solution.__number_letter_dict[digits[0]])

        if len(digits) == 1:
            return a_list
        else:
            return [a + b for a in a_list for b in self.letterCombinations(digits[1:])]


def test(digits):
    solution = Solution()
    result = solution.letterCombinations(digits)
    print("result =", result)
    print("-" * 80)


def main():
    digits = ""
    # []
    test(digits)

    digits = "23"
    # ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    test(digits)


print("-" * 80)

if __name__ == '__main__':
    main()
