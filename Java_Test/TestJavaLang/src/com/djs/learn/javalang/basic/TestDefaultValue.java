
package com.djs.learn.javalang.basic;

import java.util.Arrays;

public class TestDefaultValue
{
	public int intDefaultValue;
	public float floatDefaultValue;
	public double doubleDefaultValue;
	public boolean booleanDefaultValue;
	public char charDefaultValue;
	public String stringDefaultValue; // Object

	public static int intStaticDefaultValue;
	public static float floatStaticDefaultValue;
	public static double doubleStaticDefaultValue;
	public static boolean booleanStaticDefaultValue;
	public static char charStaticDefaultValue;
	public static String stringStaticDefaultValue;

	public void testDefaultValue1(){
		System.out.println("intDefaultValue = " + intDefaultValue);
		System.out.println("floatDefaultValue = " + floatDefaultValue);
		System.out.println("doubleDefaultValue = " + doubleDefaultValue);
		System.out.println("booleanDefaultValue = " + booleanDefaultValue);
		System.out.println("charDefaultValue = " + charDefaultValue + ", " + String.format("0x%04X", (int)charDefaultValue));
		System.out.println("stringDefaultValue = " + stringDefaultValue);

		System.out.println("intStaticDefaultValue = " + intStaticDefaultValue);
		System.out.println("floatStaticDefaultValue = " + floatStaticDefaultValue);
		System.out.println("doubleStaticDefaultValue = " + doubleStaticDefaultValue);
		System.out.println("booleanStaticDefaultValue = " + booleanStaticDefaultValue);
		System.out.println("charStaticDefaultValue = " + charStaticDefaultValue + ", " + String.format("0x%04X", (int)charStaticDefaultValue));
		System.out.println("stringStaticDefaultValue = " + stringStaticDefaultValue);
	}

	public void testDefaultValue2(){
		int intDefaultValue;
		float floatDefaultValue;
		double doubleDefaultValue;
		boolean booleanDefaultValue;
		char charDefaultValue;
		String stringDefaultValue; // Object

		// The local variable xx may not have been initialized.
		/*
		System.out.println("intDefaultValue = " + intDefaultValue);
		System.out.println("floatDefaultValue = " + floatDefaultValue);
		System.out.println("doubleDefaultValue = " + doubleDefaultValue);
		System.out.println("booleanDefaultValue = " + booleanDefaultValue);
		System.out.println("charDefaultValue = " + charDefaultValue + ", " + String.format("0x%04X", (int)charDefaultValue));
		System.out.println("stringDefaultValue = " + stringDefaultValue);
		*/
	}

	public void testArray(){
		int[] array1 = new int[2];
		// int[5] array11 = new int[];
		// int[] array12 = new int[];

		System.out.println("int[2] = " + Arrays.toString(array1));

		int[][] array2 = new int[2][];
		// int[][] array21 = new int[][5];
		// int[][] array22 = new int[][];

		System.out.println("int[2][] = " + Arrays.toString(array2));

		array2[0] = new int[2];

		System.out.println("int[2][] = " + Arrays.toString(array2));

		int[] array31 = {0, 1, 2};
		int[][] array32 = {{0, 1}, {2, 3, 4}, {5}};

		System.out.println("int[] = " + Arrays.toString(array31));
		System.out.println("int[][] = " + Arrays.toString(array32));
	}

	public static void main(String[] args){
		TestDefaultValue testMain = new TestDefaultValue();

		System.out.println("============================================================");

		testMain.testDefaultValue1();

		System.out.println("============================================================");

		testMain.testArray();

		System.out.println("============================================================");
	}
}
