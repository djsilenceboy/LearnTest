
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/two-sum/
public class TwoSum
{
	public int[] twoSum(int[] nums, int target){
		boolean found = false;
		int i, j = 1;
		for (i = 0; i < nums.length - 1; i++) {
			int num2 = target - nums[i];
			for (j = i + 1; j < nums.length; j++) {
				if (num2 == nums[j]) {
					found = true;
					break;
				}
			}
			if (found) break;
		}
		return new int[]{i, j};
	}

	public void test_twoSum_1(int[] nums, int target){
		System.out.println("Array / Target = " + Arrays.toString(nums) + " / " + target);
		long startTime = System.currentTimeMillis();
		int[] result = twoSum(nums, target);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + Arrays.toString(result));
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		TwoSum solution = new TwoSum();

		{
			int[] nums = {2, 7, 11, 15};
			solution.test_twoSum_1(nums, 9);
		}
	}
}
