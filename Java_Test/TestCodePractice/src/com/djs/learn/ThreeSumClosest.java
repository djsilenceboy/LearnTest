
package com.djs.learn;

import java.util.Arrays;

public class ThreeSumClosest
{
	public int threeSumClosest_1(int[] nums, int target){
		int result = Integer.MAX_VALUE;
		int dist = Integer.MAX_VALUE;
		Arrays.sort(nums);
		int i = 0;
		while (i < nums.length - 2) {
			int l = i + 1;
			int r = nums.length - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				int temp_dist = Math.abs(sum - target);
				if (temp_dist < dist) {
					dist = temp_dist;
					result = sum;
				}
				if (sum == target) {
					break;
				}
				if (sum < target) while ((nums[l] == nums[++l]) && (l < r));
				if (sum > target) while ((nums[r--] == nums[r]) && (l < r));
			}
			while (nums[i] == nums[++i] && (i < nums.length - 2));
		}

		return result;
	}

	public void test_threeSumClosest_1(int[] nums, int target){
		System.out.println(target + ", " + Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		int result = threeSumClosest_1(nums, target);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		ThreeSumClosest solution = new ThreeSumClosest();

		{
			int[] nums = new int[]{-1, 2, 1, -4};
			int target = 1;
			// 2
			solution.test_threeSumClosest_1(nums, target);
		}

		{
			int[] nums = new int[]{1, 1, 1, 1};
			int target = 0;
			// 3
			solution.test_threeSumClosest_1(nums, target);
		}
	}
}
