
package com.djs.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/permutations/description/
public class Permutations
{
	public List<List<Integer>> permute(List<Integer> nums){
		// System.out.println("Nums = " + nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int size = nums.size();
		if (size <= 1) {
			result.add(nums);
		} else {
			// For each element, keep that element and find combination of remained elements.
			// Then combine that element (as leading) with each of combinations of remained elements.
			for (int i = 0; i < size; i++) {
				List<Integer> newClone = new ArrayList<>(nums);
				int temp = newClone.remove(i);
				List<List<Integer>> subResult = permute(newClone);
				for (List<Integer> combination : subResult) {
					combination.add(0, temp);
					result.add(combination);
				}
			}
		}

		// System.out.println("Result = " + result);

		return result;
	}

	public List<List<Integer>> permute(int[] nums){
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; i++) {
			temp.add(nums[i]);
		}

		return permute(temp);
	}

	public void test_permute_1(int[] nums){
		System.out.println("Nums = " + Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		List<List<Integer>> result = permute(nums);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		Permutations solution = new Permutations();

		{
			// [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
			int[] nums = {1, 2, 3};
			solution.test_permute_1(nums);
		}

		{
			// [[1, 2, 3, 4], [1, 2, 4, 3], [1, 3, 2, 4], [1, 3, 4, 2], [1, 4, 2, 3], [1, 4, 3, 2], [2, 1, 3, 4], [2, 1, 4, 3], [2, 3, 1, 4], [2, 3, 4, 1], [2, 4, 1, 3], [2, 4, 3, 1], [3, 1, 2, 4], [3, 1, 4, 2], [3, 2, 1, 4], [3, 2, 4, 1], [3, 4, 1, 2], [3, 4, 2, 1], [4, 1, 2, 3], [4, 1, 3, 2], [4, 2, 1, 3], [4, 2, 3, 1], [4, 3, 1, 2], [4, 3, 2, 1]]
			int[] nums = {1, 2, 3, 4};
			solution.test_permute_1(nums);
		}

		{
			// [[1, 1, 2], [1, 2, 1], [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 1, 1]]
			int[] nums = {1, 1, 2};
			solution.test_permute_1(nums);
		}
	}
}
