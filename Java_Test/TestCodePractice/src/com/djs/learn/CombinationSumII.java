
package com.djs.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum-ii/description/
public class CombinationSumII
{
	List<List<Integer>> results = null;
	int[] candidates = null;

	// Recursive method.
	public void internal_combinationSum(List<Integer> result, int startIndex, int remained){
		// Search candidates from higher index to lower index.
		// If no more candidate.
		if (startIndex < 0) return;
		// If remained sum is less than current (larger) candidate, not enough to be subtracted.
		if (remained < candidates[startIndex]) {
			// set candidate to lower value index.
			// Then try to subtract.
			internal_combinationSum(result, startIndex - 1, remained);
		} else {
			// If remained sum is enough to be subtracted by current candidate.
			// Make a copy of current existing result set.
			List<Integer> new_result = null;
			if (result == null) new_result = new ArrayList<Integer>();
			else new_result = new ArrayList<Integer>(result);

			// Add current candidate into result copy.
			new_result.add(candidates[startIndex]);
			int new_remained = remained - candidates[startIndex];
			// If remained value is 0, means finding a combination.
			if (new_remained == 0) {
				// Add the combination into results.
				if (!results.contains(new_result)) {
					results.add(new_result);
				}
			} else {
				// If remained value is not 0, continue to try current candidate.
				internal_combinationSum(new_result, startIndex - 1, new_remained);
			}

			// Consider bypass/exclude current (higher) candidate, but use lower candidates.
			internal_combinationSum(result, startIndex - 1, remained);
		}
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target){
		results = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		this.candidates = candidates;
		internal_combinationSum(null, candidates.length - 1, target);
		return results;
	}

	public void test_combinationSum_1(int[] candidates, int target){
		System.out.println("Array / Target = " + Arrays.toString(candidates) + " / " + target);
		long startTime = System.currentTimeMillis();
		List<List<Integer>> result = combinationSum2(candidates, target);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		CombinationSumII solution = new CombinationSumII();

		{
			int[] candidates = {10, 1, 2, 7, 6, 1, 5};
			solution.test_combinationSum_1(candidates, 8);
		}

		{
			int[] candidates = {2, 5, 2, 1, 2};
			solution.test_combinationSum_1(candidates, 5);
		}
	}
}
