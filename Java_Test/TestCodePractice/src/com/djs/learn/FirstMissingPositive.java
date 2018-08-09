
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/first-missing-positive/description/
public class FirstMissingPositive
{
	public int firstMissingPositive(int[] nums){
		int size = nums.length;
		int result = 1;

		// The valid value in the array should be [1, size -1].

		// Check each element:
		// If its value is not correct element, and not duplicated one, exchange it with correct element.
		// Then continue check exchanged value until no more move.
		for (int i = 0; i < size; i++) {
			int index = nums[i] - 1;
			while ((index >= 0) && (index < size) && (index != i) && (nums[i] != nums[index])) {
				int temp = nums[index];
				nums[index] = nums[i];
				nums[i] = temp;
				index = nums[i] - 1;
			}
		}

		// Find the max continuous element.
		for (int i = 0; i < size; i++) {
			if (nums[i] - 1 != i) break;
			result++;
		}

		return result;
	}

	public int firstMissingPositive_1(int[] nums){
		int size = nums.length;
		int result = 1;

		// The valid value in the array should be [1, size -1].

		// Reset value to 0 for (, 0] and (size, ).
		for (int i = 0; i < size; i++) {
			if ((nums[i] <= 0) || (nums[i] > size)) {
				nums[i] = 0;
			}
		}

		// Check each element:
		// If its value is not correct element, and not duplicated one, exchange it with correct element.
		// Then continue check exchanged value until no more move.
		for (int i = 0; i < size; i++) {
			int index = nums[i] - 1;
			while ((index >= 0) && (index != i) && (nums[i] != nums[index])) {
				int temp = nums[index];
				nums[index] = nums[i];
				nums[i] = temp;
				index = nums[i] - 1;
			}
		}

		// Reset value to 0 for all duplicated elements, which cannot move to correct place.
		for (int i = 0; i < size; i++) {
			if (nums[i] - 1 != i) nums[i] = 0;
		}

		// Find the max continuous element, which is not 0.
		for (int i = 0; i < size; i++) {
			if (nums[i] == 0) break;
			result++;
		}

		return result;
	}

	public void test_firstMissingPositive_1(int[] nums){
		System.out.println("Nums = " + Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		int result = firstMissingPositive(nums);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		FirstMissingPositive solution = new FirstMissingPositive();

		{
			// 3
			int[] nums = {1, 2, 0};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			// 2
			int[] nums = {3, 4, -1, 1};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			// 1
			int[] nums = {7, 8, 9, 11, 12};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			// 4
			int[] nums = {3, 2, 1};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			// 4
			int[] nums = {2, 1, 3, 5};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			// 4
			int[] nums = {15, 2, 10, 1, 11, 3, 12, 5, 13};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			// 4
			int[] nums = {2, 2, 5, 1, 1, 3};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			// 4
			int[] nums = {2, 2, 5, 1, 6, 3};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			// 4
			int[] nums = {43, 20, 39, -7, -8, -2, 8, 17, 10, 17, 12, 6, 37, 17, 50, 44, 3, 11, 18, -4, 44, 37, 28, 50, 15, 50, 19, 0, 45, 5, 37, 35, 35, 21, 39,
			              35, 27, -8, -1, 47, 19, 22, 1, 1, 47, -4, -7, -3, 16, 21, 2, 7, 6};
			solution.test_firstMissingPositive_1(nums);
		}
	}
}
