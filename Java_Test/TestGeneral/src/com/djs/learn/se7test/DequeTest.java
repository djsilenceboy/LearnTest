
package com.djs.learn.se7test;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeTest
{
	public static void main(String[] args){
		Deque<Integer> d = new ArrayDeque<Integer>();
		DequeTest test = new DequeTest();
		test.fillDeque(d);
		test.printDeque(d);
	}

	void fillDeque(Deque<Integer> a){
		a.add(2);
		a.add(3);
		a.addFirst(4);
		a.addLast(5);
		a.addFirst(6);
		a.add(7);

		// 6 4 2 3 5 7
	}

	void printDeque(Deque<Integer> a){
		for (int b = 0; b < a.size(); b++) {
			System.out.print(a.pollFirst() + ", ");
		}

		// 6, 4, 2,
	}
}
