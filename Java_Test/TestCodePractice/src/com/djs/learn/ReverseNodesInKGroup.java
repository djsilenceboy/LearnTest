
package com.djs.learn;

import java.util.ArrayList;
import java.util.List;

public class ReverseNodesInKGroup
{
	static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x){
			val = x;
		}
	}

	// a                b
	// H -> n1 -> n2 -> n3 -> n4 -> null

	// a    t           b
	// H -> n1 -> n2 -> n3 -> n4 -> null

	// a    t     b
	// H -> n2 -> n3 -> n4 -> null
	// th
	// n1

	// a    b
	// H -> n3 -> n4 -> null
	// th    tt
	// n2 -> n1

	public ListNode reverseKGroup_1(ListNode head, int k){
		ListNode result = new ListNode(-1);
		result.next = head;
		ListNode a = result;
		ListNode b = a.next;

		do {
			int counter = 0;
			while ((b != null) && (counter < k)) {
				b = b.next;
				counter++;
			}

			if (counter == k) {
				ListNode tempHead = null;
				ListNode tempTail = null;
				ListNode temp = null;
				while (counter > 0) {
					temp = a.next;
					a.next = temp.next;
					if (tempTail == null) tempTail = temp;
					temp.next = (tempHead == null) ? null : tempHead;
					tempHead = temp;
					counter--;
				}
				a.next = tempHead;
				tempTail.next = b;
				a = tempTail;
			}
		} while (b != null);

		return result.next;
	}

	public void test_reverseKGroup_1(ListNode list, int k){
		System.out.println("k = " + k);
		show_list(list);
		long startTime = System.currentTimeMillis();
		ListNode result = reverseKGroup_1(list, k);
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
		ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

		for (int k = 2; k < 4; k++) {
			{
				solution.test_reverseKGroup_1(null, k);
			}

			{
				ListNode list = new ListNode(1);
				solution.test_reverseKGroup_1(list, k);
			}

			{
				ListNode list = new ListNode(1);
				list.next = new ListNode(2);
				solution.test_reverseKGroup_1(list, k);
			}

			{
				ListNode list = new ListNode(1);
				list.next = new ListNode(2);
				list.next.next = new ListNode(3);
				solution.test_reverseKGroup_1(list, k);
			}

			{
				ListNode list = new ListNode(1);
				list.next = new ListNode(2);
				list.next.next = new ListNode(3);
				list.next.next.next = new ListNode(4);
				solution.test_reverseKGroup_1(list, k);
			}

			{
				ListNode list = new ListNode(1);
				list.next = new ListNode(2);
				list.next.next = new ListNode(3);
				list.next.next.next = new ListNode(4);
				list.next.next.next.next = new ListNode(5);
				solution.test_reverseKGroup_1(list, k);
			}
		}
	}
}
