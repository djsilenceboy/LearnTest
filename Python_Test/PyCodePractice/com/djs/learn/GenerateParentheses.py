'''
@author: Du Jiang
https://leetcode.com/problems/generate-parentheses/description/
'''


class Solution:

    # ""

    # ()""
    # ("")""

    # ()()
    # ("")("")
    # (())""
    # (()"")""
    # (("")"")""

    # ""((()))
    # ""(()())
    # ()(())
    # ()()()
    # (())()
    # ()()()
    # ((()))""
    # (()())""

    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        result = set()
        if n == 0:
            result = [""]
        else:
            for i in range(1, n + 1):
                temp1 = {
                    "(" + x + ")" for x in self.generateParenthesis(i - 1)}
                temp2 = {x for x in self.generateParenthesis(n - i)}
                temp = {x + y for x in temp1 for y in temp2} | {x +
                                                                y for x in temp2 for y in temp1}
                result = result | temp

            result = sorted(list(result))
        return result

    __temp_result = None

    def internalProcess(self, stack, open, close, max):
        if len(stack) == (max * 2):
            self.__temp_result.append(stack)
            return

        if open < max:
            self.internalProcess(stack + "(", open + 1, close, max)
        if close < open:
            self.internalProcess(stack + ")", open, close + 1, max)

    def generateParenthesis_2(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        self.__temp_result = []
        self.internalProcess("", 0, 0, n)
        return sorted(self.__temp_result)


def test(n):
    solution = Solution()
    result = solution.generateParenthesis(n)
    print("result =", result)
    print("-" * 80)


def test_2(n):
    solution = Solution()
    result = solution.generateParenthesis_2(n)
    print("result =", result)
    print("-" * 80)


def main():
    n = 1
    # ['()']
    test(n)

    n = 2
    # ['(())', '()()']
    test(n)

    n = 3
    # ['((()))', '(()())', '(())()', '()(())', '()()()']
    test(n)

    n = 4
    # ['(((())))', '((()()))', '((())())', '((()))()', '(()(()))', '(()()())', '(()())()', '(())(())', '(())()()', '()((()))', '()(()())', '()(())()', '()()(())', '()()()()']
    # ["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]
    test(n)

    n = 5
    # ['((((()))))', '(((()())))', '(((())()))', '(((()))())', '(((())))()', '((()(())))', '((()()()))', '((()())())', '((()()))()', '((())(()))', '((())()())', '((())())()', '((()))(())', '((()))()()', '(()((())))', '(()(()()))', '(()(())())', '(()(()))()', '(()()(()))', '(()()()())', '(()()())()', '(()())(())', '(()())()()', '(())((()))', '(())(()())', '(())(())()', '(())()(())', '(())()()()', '()(((())))', '()((()()))', '()((())())', '()((()))()', '()(()(()))', '()(()()())', '()(()())()', '()(())(())', '()(())()()', '()()((()))', '()()(()())', '()()(())()', '()()()(())', '()()()()()']
    # ["((((()))))","(((()())))","(((())()))","(((()))())","(((())))()","((()(())))","((()()()))","((()())())","((()()))()","((())(()))","((())()())","((())())()","((()))(())","((()))()()","(()((())))","(()(()()))","(()(())())","(()(()))()","(()()(()))","(()()()())","(()()())()","(()())(())","(()())()()","(())((()))","(())(()())","(())(())()","(())()(())","(())()()()","()(((())))","()((()()))","()((())())","()((()))()","()(()(()))","()(()()())","()(()())()","()(())(())","()(())()()","()()((()))","()()(()())","()()(())()","()()()(())","()()()()()"]
    test(n)

    n = 3
    test_2(n)

    n = 4
    test_2(n)

    n = 5
    test_2(n)


if __name__ == '__main__':
    main()
