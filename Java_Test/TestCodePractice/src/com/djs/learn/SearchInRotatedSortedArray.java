
package com.djs.learn;

import java.util.Arrays;

public class SearchInRotatedSortedArray
{
	public int internal_search(int[] nums, int startPos, int endPos, int target){
		int index = -1;

		if (startPos == endPos) {
			if (nums[startPos] == target) index = startPos;
		} else if (startPos < endPos) {
			int midPos = (endPos + startPos) / 2;
			if (nums[midPos] == target) {
				index = midPos;
			} else if (target < nums[midPos]) {
				if (nums[startPos] >= nums[midPos]) {
					// 1st half: target inside, Pivot inside, target before Pivot.
					// startPos, midPos - 1
				} else if (target >= nums[startPos]) {
					// 1st half: target inside, Pivot not inside, target after Pivot.
					// startPos, midPos - 1
				} else {
					// 2nd half: target inside, Pivot may inside.
					// midPos + 1, endPos
				}
			} else // target > nums[midPos]
			{
				if (nums[endPos] <= nums[midPos]) {
					// 2nd half: target inside, Pivot inside, target after Pivot.
					// midPos + 1, endPos
				} else if (target <= nums[endPos]) {
					// 2nd half: target inside, Pivot not inside, target before Pivot.
					// midPos + 1, endPos
				} else {
					// 1st half: target inside, Pivot may inside.
					// startPos, midPos - 1
				}
			}
		}

		return index;

	}

	public int search_1(int[] nums, int target){
		int index = -1;

		if (nums.length > 0) {
			index = internal_search(nums, 0, nums.length - 1, target);
		}

		return index;
	}

	public void test_search_1(int[] nums, int target){
		System.out.println("Array / Target = " + Arrays.toString(nums) + " / " + target);
		long startTime = System.currentTimeMillis();
		int result = search_1(nums, target);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

		{
			int[] nums = {4, 5, 6, 7, 0, 1, 2};
			solution.test_search_1(nums, 0);
		}

		{
			int[] nums = {4, 5, 6, 7, 0, 1, 2};
			solution.test_search_1(nums, 3);
		}
	}
}
