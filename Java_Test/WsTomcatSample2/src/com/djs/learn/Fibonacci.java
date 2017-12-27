
package com.djs.learn;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci
{
	public List<Long> generateNumber(int number){
		ArrayList<Long> result = new ArrayList<Long>();
		int count = 0;
		Long a = 1L;
		Long b = 1L;

		result.add(a);
		count++;

		if (number > 1) {
			result.add(b);
			count++;

			if (number > 2) {
				while (count < number) {
					Long c = a + b;
					result.add(c);
					a = b;
					b = c;
					count++;
				}
			}
		}

		return result;
	}

	public static void main(String[] args){
		Fibonacci f = new Fibonacci();

		{
			List<Long> result = f.generateNumber(1);
			System.out.println("Gen(1) = " + result);
		}

		{
			List<Long> result = f.generateNumber(2);
			System.out.println("Gen(2) = " + result);
		}

		{
			List<Long> result = f.generateNumber(10);
			System.out.println("Gen(10) = " + result);
		}

		{
			List<Long> result = f.generateNumber(50);
			System.out.println("Gen(50) = " + result);
		}
	}
}
