
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/jump-game-ii/description/
public class JumpGameII
{
	public int internalJump(int[] nums, int index){
		if (nums[index] == 0) return 0;
		if (index == (nums.length - 1)) return 0;
		if (index + nums[index] >= (nums.length - 1)) return 1;

		int result = Integer.MAX_VALUE;
		for (int i = nums[index]; i >= 1; i--) {
			int temp_result = internalJump(nums, index + i);
			if (temp_result > 0) {
				if (temp_result < result) result = temp_result;
			}
		}

		if (result < Integer.MAX_VALUE) {
			return result + 1;
		} else {
			return 0;
		}
	}

	public int jump_1(int[] nums){
		return internalJump(nums, 0);
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

	public static void main(String[] args){
		JumpGameII solution = new JumpGameII();

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
			// 1
			int[] nums = {1};
			solution.test_jump_1(nums);
		}

		{
			// 5?
			int[] nums = {5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
			solution.test_jump_1(nums);
		}
	}
}
