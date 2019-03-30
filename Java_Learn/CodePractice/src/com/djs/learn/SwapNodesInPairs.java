
package com.djs.learn;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/swap-nodes-in-pairs/description/
public class SwapNodesInPairs
{
	static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x){
			val = x;
		}
	}

	// a          b
	// H -> n1 -> n2 -> n3 -> n4 -> null
	// a    b     t
	//            a           b
	// H -> n2 -> n1 -> n3 -> n4 -> null

	// H -> n1 -> n2 -> n3 -> null
	// H -> n1 -> n2 -> null
	// H -> n1 -> null

	public ListNode swapPairs_1(ListNode head){
		ListNode result = new ListNode(-1);
		result.next = head;
		ListNode a = result;
		ListNode b = (a.next != null) ? a.next.next : null;

		while (b != null) {
			ListNode temp = a.next;
			a.next = b;
			temp.next = b.next;
			b.next = temp;
			a = temp;
			b = (a.next != null) ? a.next.next : null;
		}

		return result.next;
	}

	public ListNode swapPairs_2(ListNode head){
		if ((head == null) || (head.next == null)) return head;

		ListNode result = head.next;
		head.next = swapPairs_2(head.next.next);
		result.next = head;

		return result;
	}

	public void test_swapPairs_1(ListNode list){
		show_list(list);
		long startTime = System.currentTimeMillis();
		ListNode result = swapPairs_1(list);
		long stopTime = System.currentTimeMillis();
		show_list(result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_swapPairs_2(ListNode list){
		show_list(list);
		long startTime = System.currentTimeMillis();
		ListNode result = swapPairs_2(list);
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

	public static void main(String[] args){
		SwapNodesInPairs solution = new SwapNodesInPairs();

		{
			solution.test_swapPairs_1(null);
		}

		{
			ListNode list = new ListNode(1);
			solution.test_swapPairs_1(list);
		}

		{
			ListNode list = new ListNode(1);
			list.next = new ListNode(2);
			solution.test_swapPairs_1(list);
		}

		{
			ListNode list = new ListNode(1);
			list.next = new ListNode(2);
			list.next.next = new ListNode(3);
			solution.test_swapPairs_1(list);
		}

		{
			ListNode list = new ListNode(1);
			list.next = new ListNode(2);
			list.next.next = new ListNode(3);
			list.next.next.next = new ListNode(4);
			solution.test_swapPairs_1(list);
		}

		{
			ListNode list = new ListNode(1);
			list.next = new ListNode(2);
			list.next.next = new ListNode(3);
			list.next.next.next = new ListNode(4);
			list.next.next.next.next = new ListNode(5);
			solution.test_swapPairs_1(list);
		}

		{
			ListNode list = new ListNode(1);
			list.next = new ListNode(2);
			list.next.next = new ListNode(3);
			list.next.next.next = new ListNode(4);
			solution.test_swapPairs_2(list);
		}

		{
			ListNode list = new ListNode(1);
			list.next = new ListNode(2);
			list.next.next = new ListNode(3);
			list.next.next.next = new ListNode(4);
			list.next.next.next.next = new ListNode(5);
			solution.test_swapPairs_2(list);
		}
	}
}
