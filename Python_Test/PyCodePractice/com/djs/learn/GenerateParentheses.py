'''
Created on Mar 29, 2018

@author: Du Jiang
'''


class Solution:
    def process(self, n):
        if n == 1:
            return ["()", ")("]
        else:
            sub_result = self.process(n - 1)
            return sorted(list({"(" + x + ")" for x in sub_result} | {"()" + x for x in sub_result} | {x + "()" for x in sub_result}))

    def generateParenthesis_2(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        result = self.process(n)
        for item in result:
            if item.startswith(")") or item.endswith("("):
                result.remove(item)
        return result

    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        if n == 1:
            return ["()", ")("]
        else:
            sub_result = self.generateParenthesis(n - 1)
            return sorted(list({"(" + x + ")" for x in sub_result} | {"()" + x for x in sub_result} | {x + "()" for x in sub_result}))


def test(n):
    solution = Solution()
    result = solution.generateParenthesis(n)
    print("result =", result)
    print("-" * 80)


def main():
    n = 3
    # ['((()))', '(()())', '(())()', '()(())', '()()()']
    test(n)

    n = 4
    # ['(((())))','((()()))','((())())','((()))()','(()(()))','(()()())','(()())()',           '(())()()','()((()))','()(()())','()(())()','()()(())','()()()()']
    # ["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]
    test(n)


if __name__ == '__main__':
    main()
