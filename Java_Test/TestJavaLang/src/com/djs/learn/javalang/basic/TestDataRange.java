
package com.djs.learn.javalang.basic;

public class TestDataRange
{
	public void test_int(){
		int[] special = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 1, 0};

		for (int a : special)
			for (int b : special) {
				System.out.print(a + " / " + b + " = ");
				try {
					int result = a / b;
					System.out.println(result);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
	}

	public void test_int_2(){
		{
			int a = Integer.MIN_VALUE;
			int b = Math.abs(a);
			System.out.println(a + " -> " + b);
		}
	}

	public static void main(String[] args){
		TestDataRange test = new TestDataRange();

		System.out.println("============================================================");
		test.test_int();

		System.out.println("============================================================");
		test.test_int_2();

		System.out.println("============================================================");
	}
}
