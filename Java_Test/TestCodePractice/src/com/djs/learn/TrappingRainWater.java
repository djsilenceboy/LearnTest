
package com.djs.learn;

import java.util.Arrays;

// https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater
{
	// 314 / 315 test cases passed.
	public int trap_1(int[] height){
		int result = 0;
		int rangeLow = -1, rangeHigh = -1;

		// Find first non 0 element.
		for (int i = 0; i < height.length; i++)
			if (height[i] > 0) {
				rangeLow = i;
				break;
			}

		// Find last non 0 element.
		for (int i = height.length - 1; i >= 0; i--)
			if (height[i] > 0) {
				rangeHigh = i;
				break;
			}

		// Remove line by line from bottom to up.
		while (rangeLow + 1 < rangeHigh) {
			int tempRangeLow = -1, tempRangeHigh = -1;
			int sectionLowIndex = -1;
			for (int i = rangeLow; i <= rangeHigh; i++) {
				if (height[i] > 0) {
					// If there is left wall.
					if (sectionLowIndex >= 0) {
						// Add one line area.
						result += (i - sectionLowIndex - 1);
					}
					// Mark this right wall as new left wall.
					sectionLowIndex = i;
					// Remove one line.
					height[i]--;
					if (height[i] > 0) {
						if (tempRangeLow < 0) tempRangeLow = i;
						if (tempRangeHigh < i) tempRangeHigh = i;
					}
				}
			}

			// Shrink range.
			rangeLow = tempRangeLow;
			rangeHigh = tempRangeHigh;
			System.out.println("Range low / hght = " + rangeLow + " / " + rangeHigh);
			System.out.println("Result = " + result);
			System.out.println("Height = " + Arrays.toString(height));
		}

		return result;
	}

	// 314 / 315 test cases passed.
	public int trap_2(int[] height){
		if (height.length <= 2) return 0;

		int result = 0;
		int rangeLow = -1, rangeHigh = -1;

		// Find first non 0 element.
		for (int i = 0; i < height.length; i++)
			if (height[i] > 0) {
				rangeLow = i;
				break;
			}

		// Find last non 0 element.
		for (int i = height.length - 1; i >= 0; i--)
			if (height[i] > 0) {
				rangeHigh = i;
				break;
			}

		// Remove lines by lines from bottom to up.
		while (rangeLow + 1 < rangeHigh) {
			int commonMin = Integer.MAX_VALUE;
			for (int i = rangeLow; i <= rangeHigh; i++) {
				if (height[i] < commonMin) commonMin = height[i];
			}

			System.out.println("Range low / hght / Common Min = " + rangeLow + " / " + rangeHigh + " / " + commonMin);

			int tempRangeLow = -1, tempRangeHigh = -1;
			int sectionLowIndex = -1;
			for (int i = rangeLow; i <= rangeHigh; i++) {
				height[i] -= commonMin;
				if (height[i] > 0) {
					// If there is left wall.
					if (sectionLowIndex >= 0) {
						// Add one line area.
						result += (i - sectionLowIndex - 1);
					}
					// Mark this right wall as new left wall.
					sectionLowIndex = i;
					// Remove one line.
					height[i]--;
					if (height[i] > 0) {
						if (tempRangeLow < 0) tempRangeLow = i;
						if (tempRangeHigh < i) tempRangeHigh = i;
					}
				}
			}

			// Shrink range.
			rangeLow = tempRangeLow;
			rangeHigh = tempRangeHigh;
			System.out.println("Range low / hght = " + rangeLow + " / " + rangeHigh);
			System.out.println("Result = " + result);
			System.out.println("Height = " + Arrays.toString(height));
		}

		return result;
	}

	public void test_trap_1(int[] height){
		System.out.println("Height = " + Arrays.toString(height));
		long startTime = System.currentTimeMillis();
		int result = trap_1(height);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_trap_2(int[] height){
		System.out.println("Height = " + Arrays.toString(height));
		long startTime = System.currentTimeMillis();
		int result = trap_2(height);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		TrappingRainWater solution = new TrappingRainWater();

		{
			// 0
			int[] height = {};
			solution.test_trap_1(height);
		}

		{
			// 6
			int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
			solution.test_trap_1(height);
		}

		{
			// 6
			int[] height = {0, 2, 3, 2, 4, 3, 2, 3, 5, 4, 3, 4, 3};
			solution.test_trap_1(height);
		}

		{
			// 6
			int[] height = {0, 2, 3, 2, 4, 3, 2, 3, 5, 4, 3, 4, 3};
			solution.test_trap_2(height);
		}
	}
}
