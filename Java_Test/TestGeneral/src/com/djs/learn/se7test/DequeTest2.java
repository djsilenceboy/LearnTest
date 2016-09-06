
package com.djs.learn.se7test;

import java.util.ArrayDeque;

public class DequeTest2
{
	public static void main(String[] args){
		ArrayDeque<Integer> deck = new ArrayDeque();
		int max = 6;
		int cur = 1;

		while (cur < max) {
			deck.addFirst(cur++);
			deck.addLast(++cur);
		}
		for (Integer a : deck) {
			if (deck.peekFirst() > deck.pollLast()) System.out.println("Front: " + deck.removeFirst());
			else System.out.println("End: " + deck.removeLast());
		}
	}
}
