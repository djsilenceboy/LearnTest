
package com.djs.learn;

public class DivideTwoIntegers
{
	public int divide_1(int dividend, int divisor){
		// if (dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
		// else if (divisor == Integer.MIN_VALUE) return 0;

		int result = 0;
		boolean negtive = ((dividend < 0) && (divisor >= 0)) || ((dividend >= 0) && (divisor < 0));
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		System.out.println("Dividend / Divisor = " + dividend + " / " + divisor);

		while (dividend >= divisor) {
			dividend -= divisor;
			result++;
			// System.out.println("Dividend / Result = " + dividend + " / " + result);
		}

		if (negtive) result = -result;

		return result;
	}

	public void test_divide_1(int dividend, int divisor){
		System.out.println("Dividend / Divisor = " + dividend + " / " + divisor);
		long startTime = System.currentTimeMillis();
		int result = divide_1(dividend, divisor);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		DivideTwoIntegers solution = new DivideTwoIntegers();

		solution.test_divide_1(10, 3);
		solution.test_divide_1(7, -3);

		int[] special = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 1};
		for (int a : special)
			for (int b : special) {
				solution.test_divide_1(a, b);
			}
	}
}
