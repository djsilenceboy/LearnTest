
package com.djs.learn;

import java.util.Arrays;

public class SearchInRotatedSortedArray
{
	public int internal_search(int[] nums, int startPos, int endPos, int target){
		int index = -1;
		boolean furtherCheck = false;
		boolean firstHalf = true;

		if (startPos == endPos) { // Only 1 element.
			if (nums[startPos] == target) index = startPos;
		} else if (startPos < endPos) {
			int midPos = (endPos + startPos) / 2;
			if (nums[midPos] == target) { // Mid element is target.
				index = midPos;
			} else if (target < nums[midPos]) {
				furtherCheck = true;

				if (nums[startPos] > nums[midPos]) {
					// 1st half: target inside, Pivot inside, target before Pivot.
					firstHalf = true;
				} else if (target >= nums[startPos]) {
					// 1st half: target inside, Pivot not inside, target after Pivot.
					firstHalf = true;
				} else {
					// 2nd half: target inside, Pivot may inside.
					firstHalf = false;
				}
			} else // target > nums[midPos]
			{
				furtherCheck = true;

				if (nums[endPos] < nums[midPos]) {
					// 2nd half: target inside, Pivot inside, target after Pivot.
					firstHalf = false;
				} else if (target <= nums[endPos]) {
					// 2nd half: target inside, Pivot not inside, target before Pivot.
					firstHalf = false;
				} else {
					// 1st half: target inside, Pivot may inside.
					firstHalf = true;
				}
			}

			if (furtherCheck) {
				if (firstHalf) {
					// startPos, midPos - 1
					index = internal_search(nums, startPos, midPos - 1, target);
				} else {
					// midPos + 1, endPos
					index = internal_search(nums, midPos + 1, endPos, target);
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

		{
			int[] nums = {3, 1};
			solution.test_search_1(nums, 1);
		}
	}
}
