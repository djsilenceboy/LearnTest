'''
Created on Mar 29, 2018

@author: Du Jiang
'''


class Solution:
    def isValidSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: bool
        """
        valid = True
        col_sets = [[0, set()] for i in range(9)]
        block_sets = [[0, set()] for i in range(9)]

        for i, row in enumerate(board):
            count = 0
            row_set = set()
            for j, x in enumerate(row):
                if x != ".":
                    count += 1
                    row_set.add(x)
                    col_sets[j][0] += 1
                    col_sets[j][1].add(x)
                    block_num = int(i / 3) * 3 + int(j / 3)
                    block_sets[block_num][0] += 1
                    block_sets[block_num][1].add(x)
            if len(row_set) != count:
                valid = False
                break

        if valid:
            for col_info in col_sets:
                if len(col_info[1]) != col_info[0]:
                    valid = False
                    break

        if valid:
            for block_info in block_sets:
                if len(block_info[1]) != block_info[0]:
                    valid = False
                    break

        return valid


def test(board):
    solution = Solution()
    result = solution.isValidSudoku(board)
    print("result =", result)
    print("-" * 80)


def main():
    board = [
        ["5", "3", ".", ".", "7", ".", ".", ".", "."],
        ["6", ".", ".", "1", "9", "5", ".", ".", "."],
        [".", "9", "8", ".", ".", ".", ".", "6", "."],
        ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
        ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
        ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
        [".", "6", ".", ".", ".", ".", "2", "8", "."],
        [".", ".", ".", "4", "1", "9", ".", ".", "5"],
        [".", ".", ".", ".", "8", ".", ".", "7", "9"]
    ]
    test(board)

    board = [
        ["8", "3", ".", ".", "7", ".", ".", ".", "."],
        ["6", ".", ".", "1", "9", "5", ".", ".", "."],
        [".", "9", "8", ".", ".", ".", ".", "6", "."],
        ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
        ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
        ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
        [".", "6", ".", ".", ".", ".", "2", "8", "."],
        [".", ".", ".", "4", "1", "9", ".", ".", "5"],
        [".", ".", ".", ".", "8", ".", ".", "7", "9"]
    ]
    test(board)


if __name__ == '__main__':
    main()
