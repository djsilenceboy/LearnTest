
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/rotate-image/description/
public class RotateImage
{
	public void rotate(int[][] matrix){
		int size = matrix.length;

		// 1st flip: Lower-left with upper-right.
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}

		// 2nd flip: left with right.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size / 2; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][size - 1 - j];
				matrix[i][size - 1 - j] = temp;
			}
		}
	}

	public void test_rotate(int[][] matrix){
		System.out.println("Input = " + Arrays.deepToString(matrix));
		long startTime = System.currentTimeMillis();
		rotate(matrix);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + Arrays.deepToString(matrix));
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		RotateImage solution = new RotateImage();

		{
			int[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
			// [7,4,1],
			// [8,5,2],
			// [9,6,3]
			solution.test_rotate(data);
		}

		{
			int[][] data = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
			// [15,13, 2, 5],
			// [14, 3, 4, 1],
			// [12, 6, 8, 9],
			// [16, 7,10,11]
			solution.test_rotate(data);
		}
	}
}
