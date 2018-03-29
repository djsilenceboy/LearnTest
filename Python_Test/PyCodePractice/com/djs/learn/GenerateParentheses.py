'''
Created on Mar 29, 2018

@author: Du Jiang
'''


class Solution:
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        if n == 1:
            return ["()"]
        else:
            sub_result = self.generateParenthesis(n - 1)
            return ["(" + x + ")" for x in sub_result] + ["()" + x for x in sub_result] + [x + "()" for x in sub_result]


def test(n):
    solution = Solution()
    result = solution.generateParenthesis(n)
    print("result =", result)
    print("-" * 80)


def main():
    n = 3
    test(n)


print("-" * 80)

if __name__ == '__main__':
    main()
