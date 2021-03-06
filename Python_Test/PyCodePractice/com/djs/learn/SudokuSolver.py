'''
@author: Du Jiang
https://leetcode.com/problems/sudoku-solver/description/
'''


class Solution:
    full_valid_set = {str(num + 1) for num in range(9)}
    total_row = 9
    total_col = 9
    total_block = 9

    def checkNextCell(self, board, i, j):
        j = j + 1
        if j == self.total_col:
            i = i + 1
            j = 0
        return self.checkCell(board, i, j)

    def checkCell(self, board, i, j):
        if i == self.total_row:
            return True

        value = self.board_candidate_set[i][j]
        if value == ".":
            return self.checkNextCell(board, i, j)
        else:
            # Value is a set.
            block_num = int(i / 3) * 3 + int(j / 3)
            found_solution = False
            for candidate in sorted(value):
                # If candidate has been used, test next candidate.
                if (candidate in self.row_fill_num_sets[i]) or (candidate in self.col_fill_num_sets[j]) or (candidate in self.block_fill_num_sets[block_num]):
                    continue
                # Add candidate into all sets temporarily.
                self.row_fill_num_sets[i].add(candidate)
                self.col_fill_num_sets[j].add(candidate)
                self.block_fill_num_sets[block_num].add(candidate)
                # Test remain cells.
                found_solution = self.checkNextCell(board, i, j)
                # If found solution for remain cells, the candidate is correct.
                if found_solution:
                    board[i][j] = candidate
                else:
                    # Else remove candidate from all sets, then try next
                    # candidate.
                    self.row_fill_num_sets[i].remove(candidate)
                    self.col_fill_num_sets[j].remove(candidate)
                    self.block_fill_num_sets[block_num].remove(candidate)

            return found_solution

    def solveSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: void Do not return anything, modify board in-place instead.
        """

        self.row_existing_num_sets = [set() for i in range(self.total_row)]
        self.col_existing_num_sets = [set() for i in range(self.total_col)]
        self.block_existing_num_sets = [set() for i in range(self.total_block)]

        # Pre-process to prepare existing row / column / block sets.
        for i, row in enumerate(board):
            for j, value in enumerate(row):
                if value != ".":
                    self.row_existing_num_sets[i].add(value)
                    self.col_existing_num_sets[j].add(value)
                    block_num = int(i / 3) * 3 + int(j / 3)
                    self.block_existing_num_sets[block_num].add(value)

        import copy
        # Make a temp copy of board.
        self.board_candidate_set = copy.deepcopy(board)
        for i, row in enumerate(self.board_candidate_set):
            for j, value in enumerate(row):
                # Pre-process to prepare candidate sets for each missing cell.
                if value == ".":
                    block_num = int(i / 3) * 3 + int(j / 3)
                    already_used_set = self.row_existing_num_sets[i] | self.col_existing_num_sets[
                        j] | self.block_existing_num_sets[block_num]
                    self.board_candidate_set[i][j] = self.full_valid_set - \
                        already_used_set
                    # print("invalid_set[i,j], candidate_set =", i, j, self.invalid_set, self.board_candidate_set[i][j])

        # print("full_valid_set =", self.full_valid_set)
        # print("row_existing_num_sets =")
        # for s in self.row_existing_num_sets:
        #     print(s)
        # print("col_existing_num_sets =")
        # for s in self.col_existing_num_sets:
        #     print(s)
        # print("block_existing_num_sets =")
        # for s in self.block_existing_num_sets:
        #     print(s)
        # print("board_candidate_set =")
        # for s in self.board_candidate_set:
        #     print(s)

        # Prepare used candidate row / column / block sets.
        self.row_fill_num_sets = [set() for i in range(self.total_row)]
        self.col_fill_num_sets = [set() for i in range(self.total_col)]
        self.block_fill_num_sets = [set() for i in range(self.total_block)]

        # Start recursive checking.
        self.checkCell(board, 0, 0)

        return


def test(board):
    print("input =")
    for row in board:
        print(row)
    solution = Solution()
    solution.solveSudoku(board)
    print("result =")
    for row in board:
        print(row)
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


if __name__ == '__main__':
    main()
