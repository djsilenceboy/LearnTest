
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/spiral-matrix-ii/description/
public class SpiralMatrixII
{
	public int[][] generateMatrix(int n){
		if (n == 0) return null;

		int[][] result = new int[n][n];
		int[] direction_x = {1, 0, -1, 0};
		int[] direction_y = {0, 1, 0, -1};
		int size = n * n;
		int min_x = 0, max_x = n - 1, min_y = 1, max_y = n - 1;
		int current_direction = 0, x = 0, y = 0;

		for (int counter = 0; counter < size; counter++) {
			// System.out.println("X min / max = " + min_x + " / " + max_x);
			// System.out.println("Y min / max = " + min_y + " / " + max_y);
			// System.out.println("Y / X = " + y + " / " + x);
			// System.out.println("Counter = " + counter);
			result[y][x] = counter + 1;

			if ((current_direction == 0) && (x >= max_x)) {
				current_direction++;
				max_x--;
			}
			if ((current_direction == 1) && (y >= max_y)) {
				current_direction++;
				max_y--;
			}
			if ((current_direction == 2) && (x <= min_x)) {
				current_direction++;
				min_x++;
			}
			if ((current_direction == 3) && (y <= min_y)) {
				current_direction = 0;
				min_y++;
			}

			x += direction_x[current_direction];
			y += direction_y[current_direction];
			// System.out.println("Y / X = " + y + " / " + x);
		}

		return result;
	}

	public void test_generateMatrix(int n){
		System.out.println("Matrix size = " + n);
		long startTime = System.currentTimeMillis();
		int[][] result = generateMatrix(n);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + Arrays.deepToString(result));
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		SpiralMatrixII solution = new SpiralMatrixII();

		{
			// []
			solution.test_generateMatrix(0);
		}

		{
			// [1]
			solution.test_generateMatrix(1);
		}

		{
			// [[1, 2], [4, 3]]
			solution.test_generateMatrix(2);
		}

		{
			// [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
			solution.test_generateMatrix(3);
		}
	}
}
