
package com.djs.learn;

import java.util.Arrays;

public class FirstMissingPositive
{
	public int firstMissingPositive(int[] nums){
		int result = 1;

		for (int num : nums) {
			if (num <= 0) continue;
			if (num == result) result++;
			else if (num > result) result = num - 1;
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
			int[] nums = {1, 2, 0};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			int[] nums = {3, 4, -1, 1};
			solution.test_firstMissingPositive_1(nums);
		}

		{
			int[] nums = {7, 8, 9, 11, 12};
			solution.test_firstMissingPositive_1(nums);
		}
	}
}
