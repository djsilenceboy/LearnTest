
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/jump-game-ii/description/
public class JumpGameII
{
	// 71 / 92 test cases passed. Status: Time Limit Exceeded
	public int internalJump(int[] nums, int index){
		if (nums[index] == 0) return 0;
		if (index == (nums.length - 1)) return 0;
		if (index + nums[index] >= (nums.length - 1)) return 1;

		int result = Integer.MAX_VALUE;
		for (int i = nums[index]; i >= 1; i--) {
			int temp_result = internalJump(nums, index + i);
			if ((temp_result > 0) && (temp_result < result)) result = temp_result;
		}

		if (result < Integer.MAX_VALUE)

		{
			return result + 1;
		} else {
			return 0;
		}
	}

	public int jump_1(int[] nums){
		return internalJump(nums, 0);
	}

	public void printArray(int[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == Integer.MAX_VALUE) System.out.print("M, ");
				else System.out.print(matrix[i][j] + ", ");
			}
			System.out.println();
		}
	}

	// 84 / 92 test cases passed. Status: Wrong Answer
	public int jump_2(int[] nums){
		// Use Dijkstra's algorithm.
		int size = nums.length;
		int[][] matrix = new int[size][size];

		// Convert nums array into 2-D distance array.
		// The far element to jump with lower weight.
		// The near element to jump with higher weight.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
			}

			if (nums[i] > 0) {
				for (int j = 1; j <= nums[i]; j++) {
					if (i + j < size) matrix[i][i + j] = nums[i] - j + 1;
				}
			}
		}

		// printArray(matrix);

		boolean[] checkedVertex = new boolean[size];
		int[] vertexWeight = new int[size];
		int[] vertexParent = new int[size];
		int checkedVertexCount = 0;
		int startVertex = 0;
		int tempWeight;

		// Init Dijkstra's arrays.
		for (int i = 0; i < size; i++) {
			checkedVertex[i] = false;
			vertexWeight[i] = Integer.MAX_VALUE;
			vertexParent[i] = -1;
		}

		vertexWeight[startVertex] = 0;

		while (checkedVertexCount < size) {
			// Set mark.
			checkedVertex[startVertex] = true;
			checkedVertexCount++;

			System.out.println("startVertex = " + startVertex);
			System.out.println("checkedVertexCount = " + checkedVertexCount);

			// Update new weight.
			for (int j = 0; j < size; j++) {
				// For each vertex not checked.
				if (!checkedVertex[j]) {
					tempWeight = matrix[startVertex][j];

					// If reachable and with shorter distance.
					if ((tempWeight < Integer.MAX_VALUE) && (vertexWeight[startVertex] + tempWeight < vertexWeight[j])) {
						vertexWeight[j] = vertexWeight[startVertex] + tempWeight;
						vertexParent[j] = startVertex;
					}
				}
			}

			System.out.println("checkedVertex = " + Arrays.toString(checkedVertex));
			System.out.println("vertexWeight = " + Arrays.toString(vertexWeight));
			System.out.println("vertexParent = " + Arrays.toString(vertexParent));

			// Find next selected vertex.
			// From all checked vertex to un-checked with minimum distance.
			startVertex = -1;
			tempWeight = Integer.MAX_VALUE;
			for (int i = 0; i < size; i++) {
				// For each vertex not checked.
				if (!checkedVertex[i]) {
					if (vertexWeight[i] < tempWeight) {
						tempWeight = vertexWeight[i];
						startVertex = i;
					}
				}
			}
		}

		// Find path from vertexParent array in reverse order.
		int result = 0;
		int index = size - 1;
		System.out.print("index : " + index);
		while (vertexParent[index] != -1) {
			result++;
			index = vertexParent[index];
			System.out.print(" -> " + index);
		}

		System.out.println();

		return result;
	}

	public int jump_3(int[] nums){
		int jumpCount = 0;
		int startPos = 0;
		int farEndPos = startPos + nums[startPos];
		int lastFarEndPos = startPos + 1;

		System.out.print(startPos);

		// From current position, for each new position it can jump (but not reach end yet),
		// find the one, which can further (next) jump most far.
		while ((startPos < farEndPos) && (farEndPos < nums.length - 1)) {
			int newStartPos = -1;
			int tempMaxJump = -1;

			// Find next position, which can further jump most far.
			// Note that, the part between startPos and lastFarEndPos had been checked in previous round.
			for (int i = lastFarEndPos; i <= farEndPos; i++) {
				int tempJump = i + nums[i];
				if (tempJump > tempMaxJump) {
					tempMaxJump = tempJump;
					newStartPos = i;
				}
			}

			jumpCount++;
			lastFarEndPos = farEndPos;
			startPos = newStartPos;
			farEndPos = startPos + nums[startPos];
			System.out.print(" -> " + startPos);
		}

		if ((startPos < nums.length - 1) && (farEndPos >= nums.length - 1)) {
			jumpCount++;
			System.out.print(" -> " + (nums.length - 1));
		}

		System.out.println();

		return jumpCount;
	}

	public void test_jump_1(int[] nums){
		System.out.println("Nums = " + Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		int result = jump_1(nums);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_jump_2(int[] nums){
		System.out.println("Nums = " + Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		int result = jump_2(nums);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_jump_3(int[] nums){
		System.out.println("Nums = " + Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		int result = jump_3(nums);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		JumpGameII solution = new JumpGameII();

		/*
		{
			// 2
			int[] nums = {2, 3, 1, 1, 4};
			solution.test_jump_1(nums);
		}
		
		{
			// 2
			int[] nums = {2, 3, 0, 1, 4};
			solution.test_jump_1(nums);
		}
		
		{
			// 0
			int[] nums = {0};
			solution.test_jump_1(nums);
		}
		
		{
			// 0
			int[] nums = {1};
			solution.test_jump_1(nums);
		}
		
		{
			// 5
			int[] nums = {8, 4, 3, 4, 0, 0, 9, 7, 2, 3, 5, 7, 3, 1, 1, 5, 1, 8, 6, 1, 1, 6, 1, 1, 8, 0, 4};
			solution.test_jump_1(nums);
		}

		{
			// 5
			int[] nums = {5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
			solution.test_jump_1(nums);
		}
		*/

		/*
		{
			// 2
			int[] nums = {2, 3, 1, 1, 4};
			solution.test_jump_2(nums);
		}
		
		{
			// 2
			int[] nums = {2, 3, 0, 1, 4};
			solution.test_jump_2(nums);
		}
		
		{
			// 0
			int[] nums = {0};
			solution.test_jump_2(nums);
		}

		{
			// 0
			int[] nums = {1};
			solution.test_jump_2(nums);
		}

		{
			// 5
			int[] nums = {8, 4, 3, 4, 0, 0, 9, 7, 2, 3, 5, 7, 3, 1, 1, 5, 1, 8, 6, 1, 1, 6, 1, 1, 8, 0, 4};
			solution.test_jump_2(nums);
		}

		{
			// 5
			int[] nums = {5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
			solution.test_jump_2(nums);
		}
		*/

		{
			// 2
			int[] nums = {2, 3, 1, 1, 4};
			solution.test_jump_3(nums);
		}

		{
			// 2
			int[] nums = {2, 3, 0, 1, 4};
			solution.test_jump_3(nums);
		}

		{
			// 0
			int[] nums = {0};
			solution.test_jump_3(nums);
		}

		{
			// 1
			int[] nums = {1};
			solution.test_jump_3(nums);
		}

		{
			// 5
			int[] nums = {8, 4, 3, 4, 0, 0, 9, 7, 2, 3, 5, 7, 3, 1, 1, 5, 1, 8, 6, 1, 1, 6, 1, 1, 8, 0, 4};
			solution.test_jump_3(nums);
		}
	}
}
