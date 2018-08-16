
package com.djs.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/permutations-ii/description/
public class PermutationsII
{
	public List<List<Integer>> permute(List<Integer> nums){
		// System.out.println("Nums = " + nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Set<List<Integer>> result2 = new HashSet<List<Integer>>();
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
					result2.add(combination);
				}
			}
		}

		for (List<Integer> item : result2) {
			result.add(item);
		}

		// System.out.println("Result = " + result);

		return result;
	}

	public List<List<Integer>> permuteUnique(int[] nums){
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; i++) {
			temp.add(nums[i]);
		}

		return permute(temp);
	}

	public void test_permuteUnique_1(int[] nums){
		System.out.println("Nums = " + Arrays.toString(nums));
		long startTime = System.currentTimeMillis();
		List<List<Integer>> result = permuteUnique(nums);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		PermutationsII solution = new PermutationsII();

		{
			int[] nums = {1, 2, 3};
			solution.test_permuteUnique_1(nums);
		}

		{
			int[] nums = {1, 1, 2};
			solution.test_permuteUnique_1(nums);
		}
	}
}
