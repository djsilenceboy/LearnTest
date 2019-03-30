
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/jump-game/description/
public class JumpGame
{
	public boolean canJump(int[] nums){
		boolean result = false;
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

			lastFarEndPos = farEndPos;
			startPos = newStartPos;
			farEndPos = startPos + nums[startPos];
			System.out.print(" -> " + startPos);
		}

		if ((startPos <= nums.length - 1) && (farEndPos >= nums.length - 1)) {
			result = true;
			System.out.print(" -> " + (nums.length - 1));
		}

		System.out.println();

		return result;
	}

	public void test_jump(int[] nums){
		System.out.println("Nums = " + Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		boolean result = canJump(nums);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		JumpGame solution = new JumpGame();

		{
			// true
			int[] nums = {2, 3, 1, 1, 4};
			solution.test_jump(nums);
		}

		{
			// true
			int[] nums = {2, 3, 0, 1, 4};
			solution.test_jump(nums);
		}

		{
			// true
			int[] nums = {0};
			solution.test_jump(nums);
		}

		{
			// true
			int[] nums = {1};
			solution.test_jump(nums);
		}

		{
			// true
			int[] nums = {8, 4, 3, 4, 0, 0, 9, 7, 2, 3, 5, 7, 3, 1, 1, 5, 1, 8, 6, 1, 1, 6, 1, 1, 8, 0, 4};
			solution.test_jump(nums);
		}
	}
}
