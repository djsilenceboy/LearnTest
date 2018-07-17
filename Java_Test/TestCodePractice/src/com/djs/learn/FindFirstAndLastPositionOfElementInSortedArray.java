
package com.djs.learn;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray
{
	public int[] internal_searchRange_1(int[] nums, int startPos, int endPos, int target){
		int[] index = {-1, -1};

		if (startPos == endPos) { // Only 1 element.
			if (nums[startPos] == target) { // Only 1 match.
				index[0] = index[1] = startPos;
			}
		} else if (startPos < endPos) { // More than 1 element.
			int midPos = (endPos + startPos) / 2;
			if (target < nums[midPos]) {
				// Target may be in lower half.
				index = internal_searchRange_1(nums, 0, midPos - 1, target);
			} else if (target > nums[midPos]) {
				// Target may be in upper half.
				index = internal_searchRange_1(nums, midPos + 1, endPos, target);
			} else // target == nums[midPos] // Mid is target.
			{
				// Target just around mid.
				// Maybe some in lower half.
				int[] index_l = internal_searchRange_1(nums, 0, midPos - 1, target);
				// Maybe some in upper half.
				int[] index_h = internal_searchRange_1(nums, midPos + 1, endPos, target);

				if (index_l[1] == -1) index[0] = midPos;
				else index[0] = index_l[0];

				if (index_h[0] == -1) index[1] = midPos;
				else index[1] = index_h[1];
			}
		}

		return index;
	}

	public int[] searchRange_1(int[] nums, int target){
		int[] index = {-1, -1};

		if (nums.length > 0) {
			index = internal_searchRange_1(nums, 0, nums.length - 1, target);
		}

		return index;
	}

	public int[] internal_searchRange(int[] nums, int startPos, int endPos, int target, int midMatchedFlag){
		int[] index = {-1, -1};

		if (startPos == endPos) { // Only 1 element.
			if (nums[startPos] == target) { // Only 1 match.
				index[0] = index[1] = startPos;
			}
		} else if (startPos < endPos) { // More than 1 element.
			int midPos = (endPos + startPos) / 2;
			if (target < nums[midPos]) {
				// Target may be in lower half.
				index = internal_searchRange(nums, 0, midPos - 1, target, 0);
			} else if (target > nums[midPos]) {
				// Target may be in upper half.
				index = internal_searchRange(nums, midPos + 1, endPos, target, 0);
			} else // target == nums[midPos] // Mid is target.
			{
				index[0] = index[1] = midPos;
				boolean checkLowerHalf = false;
				boolean checkUpperHalf = false;

				// Target just around mid.
				if (midMatchedFlag == -1) {
					// Maybe some in lower half.
					checkLowerHalf = true;
				} else if (midMatchedFlag == 1) {
					// Maybe some in upper half.
					checkUpperHalf = true;
				} else { // midMatchedFlag == 0
					// Maybe some in lower half.
					checkLowerHalf = true;
					// Maybe some in upper half.
					checkUpperHalf = true;
				}

				if (checkLowerHalf) {
					int[] index_l = internal_searchRange(nums, 0, midPos - 1, target, -1);

					if (index_l[1] != -1) index[0] = index_l[0];
				}

				if (checkUpperHalf) {
					int[] index_h = internal_searchRange(nums, midPos + 1, endPos, target, 1);

					if (index_h[0] != -1) index[1] = index_h[1];
				}
			}
		}

		return index;
	}

	public int[] searchRange_2(int[] nums, int target){
		int[] index = {-1, -1};

		if (nums.length > 0) {
			index = internal_searchRange(nums, 0, nums.length - 1, target, 0);
		}

		return index;
	}

	public void test_searchRange_1(int[] nums, int target){
		System.out.println("Array / Target = " + Arrays.toString(nums) + " / " + target);
		long startTime = System.currentTimeMillis();
		int[] result = searchRange_1(nums, target);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + Arrays.toString(result));
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_searchRange_2(int[] nums, int target){
		System.out.println("Array / Target = " + Arrays.toString(nums) + " / " + target);
		long startTime = System.currentTimeMillis();
		int[] result = searchRange_2(nums, target);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + Arrays.toString(result));
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		FindFirstAndLastPositionOfElementInSortedArray solution = new FindFirstAndLastPositionOfElementInSortedArray();

		{
			int[] nums = {5, 7, 7, 8, 8, 10};
			solution.test_searchRange_1(nums, 8);
		}

		{
			int[] nums = {5, 7, 7, 8, 8, 10};
			solution.test_searchRange_1(nums, 6);
		}

		{
			int[] nums = {1, 2, 3, 3, 3, 3, 4, 5, 9};
			solution.test_searchRange_1(nums, 3);
		}

		{
			int[] nums = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
			solution.test_searchRange_1(nums, 5);
		}

		{
			int[] nums = {5, 7, 7, 8, 8, 10};
			solution.test_searchRange_2(nums, 8);
		}

		{
			int[] nums = {5, 7, 7, 8, 8, 10};
			solution.test_searchRange_2(nums, 6);
		}

		{
			int[] nums = {1, 2, 3, 3, 3, 3, 4, 5, 9};
			solution.test_searchRange_2(nums, 3);
		}

		{
			int[] nums = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
			solution.test_searchRange_2(nums, 5);
		}
	}
}
