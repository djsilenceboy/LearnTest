
package com.djs.learn;

// https://leetcode.com/problems/add-two-numbers/description/
public class AddTwoNumbers
{
	public ListNode addTwoNumbers_1(ListNode l1, ListNode l2){
		ListNode head = null;
		ListNode current = null;
		int tenbit = 0;

		while ((l1 != null) || (l2 != null)) {
			int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + tenbit;
			tenbit = sum / 10;
			sum %= 10;
			ListNode temp = new ListNode(sum);

			if (head == null) {
				head = current = temp;
			} else {
				current.next = temp;
				current = temp;
			}

			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;
		}

		if (tenbit > 0) {
			current.next = new ListNode(tenbit);
		}

		return head;
	}

	public ListNode addTwoNumbers_2(ListNode l1, ListNode l2){
		ListNode head = null;
		ListNode current = null;
		int tenbit = 0;

		while ((l1 != null) || (l2 != null)) {
			int sum = tenbit;

			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}

			tenbit = sum / 10;
			ListNode temp = new ListNode(sum % 10);

			if (head == null) {
				head = current = temp;
			} else {
				current.next = temp;
				current = temp;
			}
		}

		if (tenbit > 0) {
			current.next = new ListNode(tenbit);
		}

		return head;
	}

	public ListNode addTwoNumbers_3(ListNode l1, ListNode l2){
		ListNode head = null;
		ListNode current = null;
		int sum = 0;

		while ((l1 != null) || (l2 != null) || (sum > 0)) {
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}

			ListNode temp = new ListNode(sum % 10);
			sum /= 10;

			if (head == null) {
				head = current = temp;
			} else {
				current.next = temp;
				current = temp;
			}
		}

		return head;
	}

	public void test_addTwoNumbers_1(ListNode l1, ListNode l2){
		printListNode(l1);
		printListNode(l2);
		long startTime = System.currentTimeMillis();
		ListNode result = addTwoNumbers_1(l1, l2);
		long stopTime = System.currentTimeMillis();
		printListNode(result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_addTwoNumbers_2(ListNode l1, ListNode l2){
		printListNode(l1);
		printListNode(l2);
		long startTime = System.currentTimeMillis();
		ListNode result = addTwoNumbers_2(l1, l2);
		long stopTime = System.currentTimeMillis();
		printListNode(result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_addTwoNumbers_3(ListNode l1, ListNode l2){
		printListNode(l1);
		printListNode(l2);
		long startTime = System.currentTimeMillis();
		ListNode result = addTwoNumbers_3(l1, l2);
		long stopTime = System.currentTimeMillis();
		printListNode(result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	private static void printListNode(ListNode l){
		System.out.print("ListNode = ");
		while (l != null) {
			System.out.print(l.val);
			l = l.next;
		}
		System.out.println();
	}

	public static void main(String[] args){
		AddTwoNumbers solution = new AddTwoNumbers();

		// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
		// Output: 7 -> 0 -> 8
		ListNode l1 = null, l2 = null;
		{
			int[] data1 = {3, 4, 2};
			int[] data2 = {4, 6, 5};

			for (int data : data1) {
				if (l1 == null) {
					l1 = new ListNode(data);
					l1.next = null;
				} else {
					ListNode temp = new ListNode(data);
					temp.next = l1;
					l1 = temp;
				}
			}

			for (int data : data2) {
				if (l2 == null) {
					l2 = new ListNode(data);
					l2.next = null;
				} else {
					ListNode temp = new ListNode(data);
					temp.next = l2;
					l2 = temp;
				}
			}
		}

		{
			solution.test_addTwoNumbers_1(l1, l2);
		}

		{
			solution.test_addTwoNumbers_2(l1, l2);
		}

		{
			solution.test_addTwoNumbers_3(l1, l2);
		}
	}
}

class ListNode
{
	int val;
	ListNode next;

	ListNode(int x){
		val = x;
	}
}
