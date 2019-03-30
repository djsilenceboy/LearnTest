
package com.djs.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix/description/
public class SpiralMatrix
{
	public List<Integer> spiralOrder(int[][] matrix){
		List<Integer> result = new ArrayList<>();
		int[] direction_x = {1, 0, -1, 0};
		int[] direction_y = {0, 1, 0, -1};
		int m = matrix.length;
		if (m == 0) return result;
		int n = matrix[0].length;
		int size = m * n;
		int min_x = 0, max_x = n - 1, min_y = 1, max_y = m - 1;
		int current_direction = 0, x = 0, y = 0;

		for (int counter = 0; counter < size; counter++) {
			// System.out.println("X min / max = " + min_x + " / " + max_x);
			// System.out.println("Y min / max = " + min_y + " / " + max_y);
			// System.out.println("Y / X = " + y + " / " + x);
			// System.out.println("Counter / Matric[][] = " + counter + " / " + matrix[y][x]);
			result.add(matrix[y][x]);

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

	public void test_spiralOrder(int[][] matrix){
		System.out.println("Matrix = " + Arrays.deepToString(matrix));
		long startTime = System.currentTimeMillis();
		List<Integer> result = spiralOrder(matrix);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		SpiralMatrix solution = new SpiralMatrix();

		{
			// [1,2,3,6,9,8,7,4,5]
			int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
			solution.test_spiralOrder(matrix);
		}

		{
			// [1,2,3,4,8,12,11,10,9,5,6,7]
			int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
			solution.test_spiralOrder(matrix);
		}

		{
			// []
			int[][] matrix = {};
			solution.test_spiralOrder(matrix);
		}

		{
			// [1]
			int[][] matrix = {{1}};
			solution.test_spiralOrder(matrix);
		}

		{
			// [3, 2]
			int[][] matrix = {{3}, {2}};
			solution.test_spiralOrder(matrix);
		}
	}
}
