
package com.djs.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/description/
public class MergeIntervals
{
	static class Interval
	{
		int start;
		int end;

		Interval(){
			start = 0;
			end = 0;
		}

		Interval(int s, int e){
			start = s;
			end = e;
		}

		@Override
		public String toString(){
			return "[" + start + ", " + end + "]";
		}
	}

	public class MyComparator implements Comparator<Interval>
	{
		@Override
		public int compare(Interval o1, Interval o2){
			if (o1.start < o2.start) return -1;
			else if (o1.start > o2.start) return 1;
			else if (o1.end < o2.end) return -1;
			else if (o1.end > o2.end) return 1;
			else return 0;
		}
	}

	public List<Interval> merge_1(List<Interval> intervals){
		List<Interval> result = new ArrayList<>();
		Interval temp = null;

		Collections.sort(intervals, new MyComparator());
		for (Interval interval : intervals) {
			if (temp == null) {
				temp = interval;
			} else {
				if (temp.end < interval.start) {
					result.add(temp);
					temp = interval;
				} else if (temp.end <= interval.end) {
					temp.end = interval.end;
				}
			}
		}

		if (temp != null) {
			result.add(temp);
		}

		return result;
	}

	public List<Interval> merge_2(List<Interval> intervals){
		List<Interval> result = new ArrayList<>();
		int size = intervals.size();
		int[] starts = new int[size];
		int[] ends = new int[size];
		int i = 0, j;

		for (Interval interval : intervals) {
			starts[i] = interval.start;
			ends[i] = interval.end;
			i++;
		}

		Arrays.sort(starts);
		Arrays.sort(ends);
		for (i = 0, j = 0; i < size; i++) {
			if ((i == size - 1) || (ends[i] < starts[i + 1])) {
				result.add(new Interval(starts[j], ends[i]));
				j = i + 1;
			}
		}

		return result;
	}

	public void test_merge_1(List<Interval> intervals){
		System.out.println("Intervals = " + intervals);
		long startTime = System.currentTimeMillis();
		List<Interval> result = merge_1(intervals);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_merge_2(List<Interval> intervals){
		System.out.println("Intervals = " + intervals);
		long startTime = System.currentTimeMillis();
		List<Interval> result = merge_2(intervals);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		MergeIntervals solution = new MergeIntervals();

		{
			// [[1,6],[8,10],[15,18]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 3));
			intervals.add(new Interval(2, 6));
			intervals.add(new Interval(8, 10));
			intervals.add(new Interval(15, 18));
			solution.test_merge_1(intervals);
		}

		{
			// [[1,5]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 4));
			intervals.add(new Interval(4, 5));
			solution.test_merge_1(intervals);
		}

		{
			// [[0,4]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 4));
			intervals.add(new Interval(0, 4));
			solution.test_merge_1(intervals);
		}

		{
			// [[0,4]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 4));
			intervals.add(new Interval(0, 1));
			solution.test_merge_1(intervals);
		}

		{
			// [[1,6],[8,10],[15,18]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 3));
			intervals.add(new Interval(2, 6));
			intervals.add(new Interval(8, 10));
			intervals.add(new Interval(15, 18));
			solution.test_merge_2(intervals);
		}

		{
			// [[1,5]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 4));
			intervals.add(new Interval(4, 5));
			solution.test_merge_2(intervals);
		}

		{
			// [[0,4]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 4));
			intervals.add(new Interval(0, 4));
			solution.test_merge_2(intervals);
		}

		{
			// [[0,4]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 4));
			intervals.add(new Interval(0, 1));
			solution.test_merge_2(intervals);
		}
	}
}
