
package com.djs.learn.javalang.reference;

import java.util.Arrays;

public class TestMain
{
	public static void testArray(int[] ar1, Integer[] ar2, int[] ar3){
		ar1[0] = 11;
		ar2[0] = 22;
		ar3 = ar1;
	}

	public static void main(String[] args){
		SimpleFunc func = new SimpleFunc();
		SimpleObject obj = new SimpleObject();

		System.out.println("Before = " + obj.getInfo());
		func.setInfo("Hello", obj);
		System.out.println("After = " + obj.getInfo());

		System.out.println("----------------------------------------");

		SimpleObject obj2 = null;

		func.getInfo("Hello", obj2);
		System.out.println("After = " + obj2);
		System.out.println("After = " + ((obj2 != null) ? obj2.getInfo() : null));

		System.out.println("----------------------------------------");

		int[] ar1 = new int[2];
		Integer[] ar2 = new Integer[]{new Integer(0), new Integer(0)};
		int[] ar3 = null;

		System.out.println("Before ar1 = " + Arrays.toString(ar1));
		System.out.println("Before ar2 = " + Arrays.toString(ar2));
		System.out.println("Before ar3 = " + Arrays.toString(ar3));

		testArray(ar1, ar2, ar3);

		System.out.println("After ar1 = " + Arrays.toString(ar1));
		System.out.println("After ar2 = " + Arrays.toString(ar2));
		System.out.println("After ar3 = " + Arrays.toString(ar3));

		/*
		Before = null
		After = Hello
		----------------------------------------
		After = null
		After = null
		----------------------------------------
		Before ar1 = [0, 0]
		Before ar2 = [0, 0]
		After ar1 = [11, 0]
		After ar2 = [11, 0]
		 */
	}
}
