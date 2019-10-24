
package com.djs.learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeTwoSortedLists
{
	static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x){
			val = x;
		}
	}

	public ListNode mergeTwoLists1(ListNode l1, ListNode l2){
		ListNode head = new ListNode(0);
		ListNode currentPosition = head;

		while ((l1 != null) && (l2 != null)) {
			ListNode node;
			if (l1.val < l2.val) {
				node = l1;
				l1 = l1.next;
			} else {
				node = l2;
				l2 = l2.next;
			}

			node.next = null;
			currentPosition.next = node;
			currentPosition = node;
		}

		if (l1 != null) {
			currentPosition.next = l1;
		} else if (l2 != null) {
			currentPosition.next = l2;
		}

		return head.next;
	}

	public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
		ListNode head = new ListNode(0);
		ListNode temp = head;

		temp.next = l1;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = l2;

		List<Integer> allItems = new ArrayList<>();
		temp = head.next;
		while (temp != null) {
			allItems.add(temp.val);
			temp = temp.next;
		}

		Collections.sort(allItems);

		temp = head.next;
		for (Integer item : allItems) {
			temp.val = item;
			temp = temp.next;
		}

		return head.next;
	}

	public void test_mergeTwoLists_1(ListNode l1, ListNode l2){
		show_list(1, l1);
		show_list(2, l2);
		long startTime = System.currentTimeMillis();
		ListNode result = mergeTwoLists1(l1, l2);
		long stopTime = System.currentTimeMillis();
		show_list(0, result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_mergeTwoLists_2(ListNode l1, ListNode l2){
		show_list(1, l1);
		show_list(2, l2);
		long startTime = System.currentTimeMillis();
		ListNode result = mergeTwoLists2(l1, l2);
		long stopTime = System.currentTimeMillis();
		show_list(0, result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void show_list(int id, ListNode list){
		List<Integer> data = new ArrayList();
		while (list != null) {
			data.add(list.val);
			list = list.next;
		}
		System.out.println("List[" + id + "] = " + data);
	}

	public static ListNode create_list(int... dataList){
		ListNode head = null;
		for (int data : dataList) {
			ListNode node = new ListNode(data);
			node.next = head;
			head = node;
		}

		return head;
	}

	public static void main(String[] args){
		MergeTwoSortedLists solution = new MergeTwoSortedLists();

		{
			final ListNode l1 = create_list(4, 2, 1);
			final ListNode l2 = create_list(4, 3, 1);
			solution.test_mergeTwoLists_1(l1, l2);
		}

		{
			final ListNode l1 = create_list(2);
			final ListNode l2 = create_list(1);
			solution.test_mergeTwoLists_1(l1, l2);
		}

		{
			final ListNode l1 = create_list();
			final ListNode l2 = create_list(0);
			solution.test_mergeTwoLists_1(l1, l2);
		}

		{
			final ListNode l1 = create_list(5);
			final ListNode l2 = create_list(4, 2, 1);
			solution.test_mergeTwoLists_1(l1, l2);
		}

		{
			final ListNode l1 = create_list(4, 2, 1);
			final ListNode l2 = create_list(4, 3, 1);
			solution.test_mergeTwoLists_2(l1, l2);
		}

		{
			final ListNode l1 = create_list(2);
			final ListNode l2 = create_list(1);
			solution.test_mergeTwoLists_2(l1, l2);
		}

		{
			final ListNode l1 = create_list();
			final ListNode l2 = create_list(0);
			solution.test_mergeTwoLists_2(l1, l2);
		}

		{
			final ListNode l1 = create_list(5);
			final ListNode l2 = create_list(4, 2, 1);
			solution.test_mergeTwoLists_2(l1, l2);
		}
	}
}
