
package com.djs.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum
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
				results.add(new_result);
			} else {
				// If remained value is not 0, continue to try current candidate.
				internal_combinationSum(new_result, startIndex, new_remained);
			}

			// Consider bypass/exclude current (higher) candidate, but use lower candidates.
			internal_combinationSum(result, startIndex - 1, remained);
		}
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target){
		results = new ArrayList<List<Integer>>();
		this.candidates = candidates;
		internal_combinationSum(null, candidates.length - 1, target);
		return results;
	}

	public void test_combinationSum_1(int[] candidates, int target){
		System.out.println("Array / Target = " + Arrays.toString(candidates) + " / " + target);
		long startTime = System.currentTimeMillis();
		List<List<Integer>> result = combinationSum(candidates, target);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		CombinationSum solution = new CombinationSum();

		{
			int[] candidates = {2, 3, 6, 7};
			solution.test_combinationSum_1(candidates, 7);
		}

		{
			int[] candidates = {2, 3, 5};
			solution.test_combinationSum_1(candidates, 8);
		}
	}
}
