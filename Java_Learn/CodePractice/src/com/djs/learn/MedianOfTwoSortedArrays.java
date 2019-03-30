
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/median-of-two-sorted-arrays/description/
public class MedianOfTwoSortedArrays
{
	// O((m+n)/2)
	public double findMedianSortedArrays(int[] nums1, int[] nums2){
		int total_length = nums1.length + nums2.length;
		int index_m1 = total_length / 2 + total_length % 2 - 1;
		int index_m2 = index_m1 + 1 - total_length % 2;
		int i = 0;
		int j = 0;
		double sum = 0;

		do {
			int temp, index;

			if ((i < nums1.length) && (j < nums2.length)) {
				index = i + j;
				temp = (nums1[i] <= nums2[j]) ? nums1[i++] : nums2[j++];
			} else if (i < nums1.length) {
				index = i + nums2.length;
				temp = nums1[i++];
			} else {
				index = nums1.length + j;
				temp = nums2[j++];
			}

			if (index == index_m1) {
				sum = temp;
			}
			if (index == index_m2) {
				sum += temp;
				break;
			}
		} while ((i < nums1.length) || (j < nums2.length));

		return sum / 2;
	}

	public void test_longestPalindrome(int[] nums1, int[] nums2){
		System.out.println("Input = " + Arrays.toString(nums1) + ", " + Arrays.toString(nums2));
		long startTime = System.currentTimeMillis();
		double result = findMedianSortedArrays(nums1, nums2);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();

		{
			// 2.0
			solution.test_longestPalindrome(new int[]{1, 3}, new int[]{2});
		}

		{
			// 2.5
			solution.test_longestPalindrome(new int[]{1, 2}, new int[]{3, 4});
		}
	}
}
