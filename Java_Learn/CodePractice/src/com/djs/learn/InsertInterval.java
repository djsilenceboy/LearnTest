
package com.djs.learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problemset/algorithms/
public class InsertInterval
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

	public List<Interval> insert(List<Interval> intervals, Interval newInterval){
		List<Interval> result = new ArrayList<>();
		Interval temp = null;

		intervals.add(newInterval);
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

	public void test_insert(List<Interval> intervals, Interval newInterval){
		System.out.println("Intervals = " + intervals);
		System.out.println("New interval = " + newInterval);
		long startTime = System.currentTimeMillis();
		List<Interval> result = insert(intervals, newInterval);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		InsertInterval solution = new InsertInterval();

		{
			// [[1,5],[6,9]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 3));
			intervals.add(new Interval(6, 9));
			solution.test_insert(intervals, new Interval(2, 5));
		}

		{
			// [[1,2],[3,10],[12,16]]
			List<Interval> intervals = new ArrayList<>();
			intervals.add(new Interval(1, 2));
			intervals.add(new Interval(3, 5));
			intervals.add(new Interval(6, 7));
			intervals.add(new Interval(8, 10));
			intervals.add(new Interval(12, 16));
			solution.test_insert(intervals, new Interval(4, 8));
		}

		{
			// [[5,7]]
			List<Interval> intervals = new ArrayList<>();
			solution.test_insert(intervals, new Interval(5, 7));
		}
	}
}
