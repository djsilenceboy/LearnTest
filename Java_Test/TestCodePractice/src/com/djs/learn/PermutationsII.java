
package com.djs.learn;

import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/permutations-ii/description/
public class PermutationsII
{
	public List<List<Integer>> permuteUnique(int[] nums){
		return null;
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
