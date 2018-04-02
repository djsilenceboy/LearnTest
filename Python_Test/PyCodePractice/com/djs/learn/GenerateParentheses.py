'''
Created on Mar 29, 2018

@author: Du Jiang
'''


class Solution:
    def generateParenthesis_1(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        result = None
        if n == 1:
            result = ["()"]
        else:
            sub_result = self.generateParenthesis(n - 1)
            temp = {"(" + x + ")" for x in sub_result} | {"()" +
                                                          x for x in sub_result} | {x + "()" for x in sub_result}
            if (n >= 4) and (n % 2 == 0):
                sub_result_2 = self.generateParenthesis((n - 2) / 2)
                temp = temp | {"(" + x + ")(" + x + ")" for x in sub_result_2}

            result = sorted(list(temp))
        return result

    def generateParenthesis_2(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        result = set()
        if n == 0:
            result = [""]
        elif n == 1:
            result = ["()"]
        else:
            for i in range(int(n / 2) + 1):
                print("i =", i, int(n / 2), n)
                temp1 = self.generateParenthesis(i) if i < 3 else [
                    "(" + x + ")" for x in self.generateParenthesis(i - 1)]
                print("temp1 =", temp1)
                temp2 = self.generateParenthesis(
                    n - i) if n - i < 3 else ["(" + x + ")" for x in self.generateParenthesis(n - i - 1)]
                print("temp2 =", temp2)
                temp = {x + y for x in temp1 for y in temp2} | {x +
                                                                y for x in temp2 for y in temp1}
                print("temp =", temp)
                result = result | temp
                print("result =", result)

            result = sorted(list(result))
        return result

    def generateParenthesis_3(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        result = set()
        if n == 0:
            result = [""]
        else:
            for i in range(1, n + 1):
                print("i =", i, n - i)
                temp1 = {
                    "(" + x + ")" for x in self.generateParenthesis(i - 1)}
                print("temp1 =", i, temp1)
                temp2 = {x for x in self.generateParenthesis(n - i)}
                print("temp2 =", n - i, temp2)
                temp = {x + y for x in temp1 for y in temp2} | {x +
                                                                y for x in temp2 for y in temp1}
                print("temp =", temp)
                result = result | temp
                print("result =", result)

            result = sorted(list(result))
        return result

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


def test(n):
    solution = Solution()
    result = solution.generateParenthesis(n)
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


if __name__ == '__main__':
    main()
