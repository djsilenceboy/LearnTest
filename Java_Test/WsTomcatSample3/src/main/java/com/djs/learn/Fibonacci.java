
package com.djs.learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fibonacci
{
	public List<Long> sort(List<Long> fib){
		ArrayList<Long> odd = new ArrayList<Long>();
		ArrayList<Long> even = new ArrayList<Long>();

		for (Long i : fib) {
			if (i % 2 == 0) {
				even.add(i);
			} else {
				odd.add(i);
			}
		}

		Collections.reverse(odd);
		Collections.reverse(even);

		// System.out.println("odd = " + odd);
		// System.out.println("even = " + even);

		odd.addAll(even);

		return odd;
	}
}
