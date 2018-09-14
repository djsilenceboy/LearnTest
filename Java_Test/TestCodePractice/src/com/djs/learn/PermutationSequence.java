
package com.djs.learn;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutation-sequence/description/
public class PermutationSequence
{
	private int counter;
	private List<Integer> kCombination;

	public List<List<Integer>> permute(List<Integer> nums, int n, int k){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<List<Integer>> result2 = new ArrayList<List<Integer>>();
		int size = nums.size();
		if (size <= 1) {
			if (size == n) {
				kCombination = nums;
				return null;
			} else {
				result.add(nums);
				return result;
			}
		} else {
			// For each element, keep that element and find combination of remained elements.
			// Then combine that element (as leading) with each of combinations of remained elements.
			for (int i = 0; i < size; i++) {
				List<Integer> newClone = new ArrayList<>(nums);
				int temp = newClone.remove(i);
				List<List<Integer>> subResult = permute(newClone, n, k);
				for (List<Integer> combination : subResult) {
					combination.add(0, temp);
					if (combination.size() == n) {
						counter++;
						// System.out.println("K, Combination = " + k + ", " + combination);
						if (counter == k) {
							kCombination = combination;
							return null;
						}
					}
					result2.add(combination);
				}
			}
		}

		for (List<Integer> item : result2) {
			result.add(item);
		}

		return result;
	}

	// 89 / 200 test cases passed.
	public String getPermutation(int n, int k){
		String result = "";
		List<Integer> temp = new ArrayList<Integer>();
		counter = 0;
		kCombination = null;

		for (int i = 0; i < n; i++) {
			temp.add(i + 1);
		}

		permute(temp, n, k);

		for (Integer i : kCombination) {
			result += i;
		}

		return result;
	}

	public void test_getPermutation_1(int n, int k){
		System.out.println("N / K = " + n + ", " + k);
		long startTime = System.currentTimeMillis();
		String result = getPermutation(n, k);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		PermutationSequence solution = new PermutationSequence();

		{
			// 213
			solution.test_getPermutation_1(3, 3);
		}

		{
			// 2314
			solution.test_getPermutation_1(4, 9);
		}

		{
			// 1
			solution.test_getPermutation_1(1, 1);
		}

		{
			// 647123589
			solution.test_getPermutation_1(9, 219601);
		}
	}
}
