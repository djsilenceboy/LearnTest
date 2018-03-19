
package com.djs.learn;

import java.util.Arrays;

public class ContainerWithMostWater
{
	public int maxArea_1(int[] height){
		int max_area = 0;
		for (int i = 0; i < height.length - 1; i++)
			for (int j = i + 1; j < height.length; j++) {
				int h = (height[i] < height[j]) ? height[i] : height[j];
				int area = h * (j - i);
				if (area > max_area) max_area = area;
			}

		return max_area;
	}

	public int maxArea_2(int[] height){
		int max_area = 0;
		int max_height_i = 0;

		for (int i = 0; i < height.length - 1; i++)
			if (height[i] > max_height_i) {
				for (int j = height.length - 1; j > i; j--) {
					int h = (height[i] < height[j]) ? height[i] : height[j];
					int area = h * (j - i);
					if (area > max_area) {
						max_area = area;
						max_height_i = height[i];
					}
				}
			}

		return max_area;
	}

	public int maxArea_3(int[] height){
		int max_area = 0;
		int max_i = -1;
		int max_j = height.length - 1;

		for (int i = 0; i < height.length - 1; i++)
			if ((max_i < 0) || (height[i] > height[max_i])) {
				for (int j = max_j; j > i; j--) {
					int h = (height[i] < height[j]) ? height[i] : height[j];
					int area = h * (j - i);
					if (area > max_area) {
						max_area = area;
						max_i = i;
						max_j = j;
					}
				}
			}

		return max_area;
	}

	public int maxArea_4(int[] height){
		int max_area = 0;
		int i = 0;
		int j = height.length - 1;

		while (i < j) {
			int h = (height[i] < height[j]) ? height[i] : height[j];
			int area = h * (j - i);
			if (area > max_area) {
				max_area = area;
			}

			if (height[i] < height[j]) i++;
			else j--;
		}

		return max_area;
	}

	public static void main(String[] args){
		ContainerWithMostWater solution = new ContainerWithMostWater();

		int[] height = new int[15000];
		for (int i = 0; i < height.length; i++) {
			height[i] = height.length - i;
		}

		System.out.println(Arrays.toString(height));

		{
			long startTime = System.currentTimeMillis();
			int result = solution.maxArea_1(height);
			long stopTime = System.currentTimeMillis();
			System.out.println("Result = " + result);
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}

		{
			long startTime = System.currentTimeMillis();
			int result = solution.maxArea_2(height);
			long stopTime = System.currentTimeMillis();
			System.out.println("Result = " + result);
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}

		{
			long startTime = System.currentTimeMillis();
			int result = solution.maxArea_3(height);
			long stopTime = System.currentTimeMillis();
			System.out.println("Result = " + result);
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}

		{
			long startTime = System.currentTimeMillis();
			int result = solution.maxArea_4(height);
			long stopTime = System.currentTimeMillis();
			System.out.println("Result = " + result);
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}
	}
}
