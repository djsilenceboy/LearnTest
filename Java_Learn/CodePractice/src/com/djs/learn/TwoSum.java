
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/two-sum/
public class TwoSum
{
	public int[] twoSum_1(int[] nums, int target){
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

	private int[] sort(int[] data){
		int[] indexes = new int[data.length];
		int i, j;
		int temp;

		for (i = 0; i < indexes.length; i++) {
			indexes[i] = i;
		}

		for (i = 1; i < indexes.length; i++) {
			// Temporarily keep the top/new element in sub-list, which will be inserted into proper position.
			temp = indexes[i];

			for (j = i; (j > 0) && (data[indexes[j - 1]] > data[temp]); j--) {
				indexes[j] = indexes[j - 1];
			}

			if (j != i) {
				indexes[j] = temp;
			}
		}

		return indexes;
	}

	private int search(int[] data, int[] indexes, int from, int to, int key){
		if (from > to) return -1;
		int index = (from + to) / 2;
		if (data[indexes[index]] == key) return index;
		else if (data[indexes[index]] < key) return search(data, indexes, index + 1, to, key);
		else return search(data, indexes, from, index - 1, key);
	}

	public int[] twoSum_2(int[] nums, int target){
		int[] indexes = sort(nums);
		int i, j = 1;
		for (i = 0; i < nums.length - 1; i++) {
			int num2 = target - nums[indexes[i]];
			j = search(nums, indexes, i + 1, nums.length - 1, num2);
			if (j > i) break;
		}
		return new int[]{indexes[i], indexes[j]};
	}

	public void test_twoSum_1(int[] nums, int target){
		System.out.println("Array / Target = " + Arrays.toString(nums) + " / " + target);
		long startTime = System.currentTimeMillis();
		int[] result = twoSum_1(nums, target);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + Arrays.toString(result));
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_twoSum_2(int[] nums, int target){
		System.out.println("Array / Target = " + Arrays.toString(nums) + " / " + target);
		long startTime = System.currentTimeMillis();
		int[] result = twoSum_2(nums, target);
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
		{
			int[] nums = {3, 2, 4};
			solution.test_twoSum_1(nums, 6);
		}
		{
			int[] nums = {2, 7, 11, 15};
			solution.test_twoSum_2(nums, 9);
		}
		{
			int[] nums = {3, 2, 4};
			solution.test_twoSum_2(nums, 6);
		}
	}
}
