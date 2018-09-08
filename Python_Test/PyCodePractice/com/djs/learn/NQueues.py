'''
@author: Du Jiang
https://leetcode.com/problems/n-queens/description/
'''


class Solution:
    def innerSolveNQueens(self, n, positions, rowNum, validColumns):
        if rowNum == n:
            self.result.append(["." * positions[i] + "Q" + "." *
                                (n - 1 - positions[i]) for i in range(n)])
            return

        for column in range(n):
            valid = True
            if (column in validColumns) and ((rowNum == 0) or (column < positions[rowNum - 1] - 1) or (column > positions[rowNum - 1] + 1)):
                if rowNum > 0:
                    for k in range(rowNum - 1):
                        if (rowNum - k) == abs(column - positions[k]):
                            valid = False
                            break
            else:
                valid = False

            if valid:
                positions[rowNum] = column
                validColumns.remove(column)
                self.innerSolveNQueens(
                    n, positions, rowNum + 1, validColumns)
                validColumns.add(column)

    def solveNQueens(self, n):
        """
        :type n: int
        :rtype: List[List[str]]
        """
        self.result = []
        validColumns = {i for i in range(n)}
        positions = [-1 for i in range(n)]

        self.innerSolveNQueens(n, positions, 0, validColumns)

        return self.result


def test(n):
    solution = Solution()
    print("n =", n)
    result = solution.solveNQueens(n)
    # print("result =", result)
    print("result size =", len(result))
    print("-" * 80)


def main():
    # 2
    test(4)
    # 10
    test(5)
    # 4
    test(6)
    # 40
    test(7)
    # 92
    test(8)
    # 352
    test(9)
    # 724
    test(10)
    # 2,680
    test(11)
    # 14,200
    test(12)
    # 73,712
    test(13)
    # 365,596
    test(14)


if __name__ == '__main__':
    main()
