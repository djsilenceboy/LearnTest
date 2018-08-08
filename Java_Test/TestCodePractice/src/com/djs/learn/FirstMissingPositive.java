
package com.djs.learn;

import java.util.Arrays;

public class FirstMissingPositive
{
	public int firstMissingPositive(int[] nums){
		int size = nums.length;
		boolean some_out_of_range = false;
		int result = -1;

		do {
			some_out_of_range = false;
			result = -1;
			for (int i = 0; i < size; i++) {
				if ((nums[i] <= 0) || (nums[i] > size)) {
					nums[i] = Integer.MAX_VALUE;
					some_out_of_range = true;
				}
				if (nums[i] > result) result = nums[i];
			}

			System.out.println("oor = " + some_out_of_range);
			System.out.println("Result = " + result);

			if (some_out_of_range) {
				int lower = 0, upper = size - 1;
				while (lower < upper) {
					while ((lower < upper) && (nums[lower] != Integer.MAX_VALUE))
						lower++;
					while ((upper >= 0) && (nums[upper] == Integer.MAX_VALUE)) {
						upper--;
						size--;
					}
					if (lower < upper) {
						nums[lower] = nums[upper];
						nums[upper] = Integer.MAX_VALUE;
					}
				}

				System.out.println("Size = " + size);
				System.out.println("Nums = " + Arrays.toString(nums));
			}
		} while (some_out_of_range);

		for (int i = 0; i < size; i++) {
			int j = i;
			int index = nums[j] - 1;
			while (index != j) {
				int temp = nums[index];
				nums[index] = nums[j];
				nums[j] = temp;
				index = nums[j] - 1;
			}
		}

		System.out.println("Nums = " + Arrays.toString(nums));

		if (result == -1) result = 1;
		else result++;
		System.out.println("Final result = " + result);

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
