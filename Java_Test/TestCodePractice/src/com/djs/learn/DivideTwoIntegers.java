
package com.djs.learn;

public class DivideTwoIntegers
{
	/*
	============================================================
	-2147483648 / -2147483648 = 1
	-2147483648 / 2147483647 = -1
	-2147483648 / -1 = -2147483648
	-2147483648 / 1 = -2147483648
	-2147483648 / 0 = java.lang.ArithmeticException: / by zero
	2147483647 / -2147483648 = 0
	2147483647 / 2147483647 = 1
	2147483647 / -1 = -2147483647
	2147483647 / 1 = 2147483647
	2147483647 / 0 = java.lang.ArithmeticException: / by zero
	-1 / -2147483648 = 0
	-1 / 2147483647 = 0
	-1 / -1 = 1
	-1 / 1 = -1
	-1 / 0 = java.lang.ArithmeticException: / by zero
	1 / -2147483648 = 0
	1 / 2147483647 = 0
	1 / -1 = -1
	1 / 1 = 1
	1 / 0 = java.lang.ArithmeticException: / by zero
	0 / -2147483648 = 0
	0 / 2147483647 = 0
	0 / -1 = 0
	0 / 1 = 0
	0 / 0 = java.lang.ArithmeticException: / by zero
	============================================================
	 */

	public int divide_1(int dividend, int divisor){
		boolean dividend_is_min = false;
		if (divisor == Integer.MIN_VALUE) {
			return (dividend == Integer.MIN_VALUE) ? 1 : 0;
		} else if (dividend == Integer.MIN_VALUE) {
			dividend = -Integer.MAX_VALUE;
			dividend_is_min = true;
		}

		int result = 0;
		boolean negative = ((dividend < 0) && (divisor >= 0)) || ((dividend >= 0) && (divisor < 0));
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		while (dividend >= divisor) {
			dividend -= divisor;
			result++;
		}

		if (negative) result = -result;
		if ((result == -Integer.MAX_VALUE) && dividend_is_min) result = Integer.MIN_VALUE;

		return result;
	}

	public int divide_2(int dividend, int divisor){
		int result = 0;

		if ((divisor == 0) || (dividend == Integer.MIN_VALUE) && (divisor == -1)) {
			result = Integer.MAX_VALUE;
		} else {
			boolean negative = ((dividend < 0) && (divisor >= 0)) || ((dividend >= 0) && (divisor < 0));
			long ldividend = Math.abs((long)dividend);
			long ldivisor = Math.abs((long)divisor);

			while (ldividend >= ldivisor) {
				ldividend -= ldivisor;
				result++;
			}

			if (negative) result = -result;
		}

		return result;
	}

	public int divide_3(int dividend, int divisor){
		int result = 0;

		if ((divisor == 0) || (dividend == Integer.MIN_VALUE) && (divisor == -1)) {
			result = Integer.MAX_VALUE;
		} else {
			boolean negative = ((dividend < 0) && (divisor >= 0)) || ((dividend >= 0) && (divisor < 0));
			long ldividend = Math.abs((long)dividend);
			long ldivisor = Math.abs((long)divisor);

			if (ldivisor <= ldividend) {
				long temp_remain = ldividend;
				while (ldivisor <= temp_remain) {
					long temp_sum = ldivisor;
					int temp_result = 1;

					while (temp_sum + temp_sum <= temp_remain) {
						temp_sum += temp_sum;
						temp_result += temp_result;
					}

					temp_remain -= temp_sum;
					result += temp_result;
				}
			}

			if (negative) result = -result;
		}

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

	public void test_divide_2(int dividend, int divisor){
		System.out.println("Dividend / Divisor = " + dividend + " / " + divisor);
		long startTime = System.currentTimeMillis();
		int result = divide_2(dividend, divisor);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_divide_3(int dividend, int divisor){
		System.out.println("Dividend / Divisor = " + dividend + " / " + divisor);
		long startTime = System.currentTimeMillis();
		int result = divide_3(dividend, divisor);
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

		System.out.println("==================================================");

		solution.test_divide_2(10, 3);
		solution.test_divide_2(7, -3);

		for (int a : special)
			for (int b : special) {
				solution.test_divide_2(a, b);
			}

		System.out.println("==================================================");

		solution.test_divide_3(10, 3);
		solution.test_divide_3(7, -3);

		for (int a : special)
			for (int b : special) {
				solution.test_divide_3(a, b);
			}
	}
}
