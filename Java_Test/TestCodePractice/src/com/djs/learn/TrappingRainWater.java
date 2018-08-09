
package com.djs.learn;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater
{
	public int trap(int[] height){
		int result = 0;

		Stack<Integer> stack = new Stack<Integer>();
		int inStackMax = -1;
		int inStackMinIndex = -1;

		for (int i = 0; i < height.length; i++) {
			if (height[i] > 0) {
				if (stack.isEmpty()) {
					stack.push(i);
					inStackMax = height[i];
					inStackMinIndex = i;
				} else if (inStackMax > height[i]) {
					stack.push(i);
				} else // (inStackMax <= height[i])
				{
					while (!stack.isEmpty()) {

					}

					stack.push(i);
					inStackMax = height[i];
					inStackMinIndex = i;
				}
			}
		}

		return result;
	}

	public void test_trap_1(int[] height){
		System.out.println("Height = " + Arrays.toString(height));
		long startTime = System.currentTimeMillis();
		int result = trap(height);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		TrappingRainWater solution = new TrappingRainWater();

		{
			// 6
			int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
			solution.test_trap_1(height);
		}
	}
}
