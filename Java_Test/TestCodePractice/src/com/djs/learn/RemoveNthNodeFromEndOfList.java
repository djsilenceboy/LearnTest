
package com.djs.learn;

public class RemoveNthNodeFromEndOfList
{
	static class ListNode
	{
		int val;
		ListNode next;

		ListNode(int x){
			val = x;
		}
	}

	// a                 b
	// n5 -> n4 -> n3 -> n2 -> n1 -> null
	//             a                 b
	// n5 -> n4 -> n3 -> n2 -> n1 -> null

	// a                 b
	// n3 -> n2 -> n1 -> null

	// a           b
	// n2 -> n1 -> null

	public ListNode removeNthFromEnd_1(ListNode head, int n){
		ListNode a = head;
		ListNode b = head;
		int counter = 0;

		while (b != null) {
			if (counter < n + 1) {
				b = b.next;
				counter++;
			} else {
				a = a.next;
				b = b.next;
			}
		}

		if (counter < n + 1) head = head.next;
		else a.next = a.next.next;

		return head;
	}

	public void test_removeNthFromEnd_1(ListNode head, int n){
		long startTime = System.currentTimeMillis();
		ListNode result = removeNthFromEnd_1(head, n);
		long stopTime = System.currentTimeMillis();
		// System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();

		{
			ListNode head = new ListNode(1);
			solution.test_removeNthFromEnd_1(head, 2);
		}
	}
}
