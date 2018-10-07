
package com.djs.learn.javalang.methods;

import java.util.Arrays;

/**
 * <pre>
============================================================
Arg1 = []
Arg1 = null
Arg1 = [1, 2, 3]
Arg1 = [1, 2, 3]
============================================================
Arg1 = null
Arg2 = []
------------------------------
Arg1 = null
Arg2 = null
------------------------------
Arg1 = null
Arg2 = [4, 5]
------------------------------
Arg1 = [1, 2, 3]
Arg2 = []
------------------------------
Arg1 = [1, 2, 3]
Arg2 = null
------------------------------
Arg1 = [1, 2, 3]
Arg2 = [4, 5]
------------------------------
Arg1 = [1, 2, 3]
Arg2 = [4, 5]
============================================================
Arg1 = 1
============================================================
 * </pre>
 */
public class TestVarargs
{
	public void methodA(int... arg1){
		System.out.println("Arg1 = " + Arrays.toString(arg1));
	}

	public void methodB(int[] arg1, int... arg2){
		System.out.println("Arg1 = " + Arrays.toString(arg1));
		System.out.println("Arg2 = " + Arrays.toString(arg2));
	}

	public void methodC(int arg1){
		System.out.println("Arg1 = " + arg1);
	}

	public void methodC(int... arg1){
		System.out.println("Arg1 = " + Arrays.toString(arg1));
	}

	public static void main(String[] args){
		TestVarargs test = new TestVarargs();

		System.out.println("============================================================");

		test.methodA();
		test.methodA(null);
		test.methodA(1, 2, 3);
		test.methodA(new int[]{1, 2, 3});
		// test.methodA(new int[]{1, 2, 3}, 4 , 5);

		System.out.println("============================================================");

		// test.methodB();
		// test.methodB(1, 2, 3);

		test.methodB(null);
		System.out.println("------------------------------");
		test.methodB(null, null);
		System.out.println("------------------------------");
		test.methodB(null, 4, 5);
		System.out.println("------------------------------");
		test.methodB(new int[]{1, 2, 3});
		System.out.println("------------------------------");
		test.methodB(new int[]{1, 2, 3}, null);
		System.out.println("------------------------------");
		test.methodB(new int[]{1, 2, 3}, 4, 5);
		System.out.println("------------------------------");
		test.methodB(new int[]{1, 2, 3}, new int[]{4, 5});

		System.out.println("============================================================");

		// It will choose "int" not "int...".
		test.methodC(1);

		System.out.println("============================================================");
	}
}
