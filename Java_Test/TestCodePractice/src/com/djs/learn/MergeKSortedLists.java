
package com.djs.learn;

import java.util.ArrayList;
import java.util.List;

public class MergeKSortedLists
{
	static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x){
			val = x;
		}
	}

	public int swap(int x, int y){
		return x;
	}

	public ListNode mergeKLists_1(ListNode[] lists){
		ListNode result = null;
		ListNode result_tail = null;
		int available_lists = 0;
		int[] list_indexes = null;

		for (int i = 0; i < lists.length; i++)
			if (lists[i] != null) available_lists++;

		if (available_lists > 0) {
			list_indexes = new int[available_lists];
			int list_indexes_counter = 0;
			for (int i = 0; i < lists.length; i++)
				if (lists[i] != null) list_indexes[list_indexes_counter++] = i;
		}

		while (available_lists > 0) {
			int position, child, parent;
			// Push the smallest element to top position.
			for (position = 0; position < list_indexes.length; position++) {
				// From the bottom position.
				child = list_indexes.length - 1;

				// Compare each node with its parent,
				// Swap value if child is smaller than parent.
				while (child > position) {
					// Get parent position.
					parent = position + (int)(((float)child - position - 0.5) / 2.0);

					// Compare parent and child.
					if (lists[list_indexes[parent]].val > lists[list_indexes[child]].val) {
						// Swap.
						list_indexes[child] = swap(list_indexes[parent], list_indexes[parent] = list_indexes[child]);
					}

					// move up one node.
					child--;
				}
			}

			if (result == null) {
				result = lists[list_indexes[0]];
				result_tail = result;
			} else {
				result_tail.next = lists[list_indexes[0]];
				result_tail = result_tail.next;
			}
			lists[list_indexes[0]] = lists[list_indexes[0]].next;
			result_tail.next = null;

			if (lists[list_indexes[0]] == null) {
				if (available_lists > 1) list_indexes[0] = list_indexes[available_lists - 1];
				available_lists--;
			}
		}

		return result;
	}

	/*
	Runtime Error Message: Exception in thread "main" java.lang.NullPointerException
	at Solution.mergeKLists(Solution.java:44)
	at __DriverSolution__.__helper__(__Driver__.java:4)
	at __Driver__.main(__Driver__.java:48)

	Last executed input: [[],[-1,5],[1,4,6],[4,5,6]]
	 */
	public void test_mergeKLists_1(ListNode[] lists){
		show_lists(lists);
		long startTime = System.currentTimeMillis();
		ListNode result = mergeKLists_1(lists);
		long stopTime = System.currentTimeMillis();
		show_list(result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void show_list(ListNode list){
		List<Integer> data = new ArrayList();
		while (list != null) {
			data.add(list.val);
			list = list.next;
		}
		System.out.println("List = " + data);
	}

	public void show_lists(ListNode[] lists){
		for (ListNode list : lists)
			show_list(list);
	}

	public static void main(String[] args){
		MergeKSortedLists solution = new MergeKSortedLists();

		{
			// [[],[-1,5],[1,4,6],[4,5,6]]
			ListNode[] lists = new ListNode[]{null, new ListNode(-1), new ListNode(1), new ListNode(4)};
			lists[1].next = new ListNode(5);
			lists[2].next = new ListNode(4);
			lists[2].next.next = new ListNode(6);
			lists[3].next = new ListNode(5);
			lists[3].next.next = new ListNode(6);
			solution.test_mergeKLists_1(lists);
		}
	}
}
