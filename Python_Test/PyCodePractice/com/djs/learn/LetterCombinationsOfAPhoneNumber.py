'''
Created on Mar 27, 2018

@author: Du Jiang
'''


class Solution:
    __valid_numbers = [num for num in range(2, 10)]
    __number_letter_dict = {2: ["a", "b", "c"], 3: ["d", "e", "f"],
                            4: ["g", "h", "i"], 5: ["j", "k", "l"], 6: ["m", "n", "o"], 7: ["p", "q", "r", "s"], 8: ["t", "u", "v"], 9: ["w", "x", "y", "z"]}

    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        print("digits =", digits)
        first_digit = int(digits[0])
        print("first_digit =", first_digit)
        if len(digits) == 1:
            if first_digit in Solution.__valid_numbers:
                return Solution.__number_letter_dict[first_digit]
            else:
                return [""]
        else:
            result = []
            if first_digit in Solution.__valid_numbers:
                a_list = Solution.__number_letter_dict.get(
                    first_digit)
            else:
                a_list = [""]
            print("a_list =", a_list)
            for a, b in zip(a_list, self.letterCombinations(digits[1:])):
                result.append(a + b)
            return result


def test(digits):
    solution = Solution()
    solution.letterCombinations(digits)
    print("-" * 80)


def main():
    digits = "23"
    # ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    test(digits)


print("-" * 80)

if __name__ == '__main__':
    main()
