
package com.djs.learn.javalang.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataTypeV7
{
	public void testArray(){
		System.out.println("Test: Array");

		List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("Ok");
		list.add("Good");

		System.out.println("List = " + list);

		System.out.println("----------------------------------------");

		// This will fail.
		// list.addAll(new ArrayList<>());
		// list.addAll(Arrays.asList());

		list.addAll(new ArrayList<String>());
		list.addAll(Arrays.<String> asList());

		List<String> list2 = new ArrayList<>();
		list.addAll(list2);
	}

	public void testBinary(){
		System.out.println("Test: Binary");

		// An 8-bit 'byte' value:
		byte aByte = (byte)0b00100001;
		// A 16-bit 'short' value:
		short aShort = (short)0b1010000101000101;
		// Some 32-bit 'int' values:
		int anInt1 = 0b10100001010001011010000101000101;
		int anInt2 = 0B10100001010001011010000101000101; // The B can be upper or lower case.
		// A 64-bit 'long' value. Note the "L" suffix:
		long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;

		System.out.println("aByte = " + aByte);
		System.out.println("aShort = " + aShort);
		System.out.println("anInt1 = " + anInt1);
		System.out.println("anInt2 = " + anInt2);
		System.out.println("aLong = " + aLong);

		System.out.println("----------------------------------------");

		int[] phases = {0b00110001, 0b01100010, 0b11000100, 0b10001001, 0b00010011, 0b00100110, 0b01001100, 0b10011000};

		for (int item : phases) {
			System.out.printf("0x%X\n", item);
		}

		System.out.println("----------------------------------------");

		switch (0b0010){
			case 0b0001:
				System.out.println("0b0001");
			break;
			case 0b0010:
				System.out.println("0b0010");
			break;
			default:
				System.out.println("0b1111");
		}
	}

	public void testUnderscore(){
		System.out.println("Test: Underscore");

		long creditCardNumber = 1234_5678_9012_3456L;
		long socialSecurityNumber = 999_99_9999L;
		float pi = 3.14_15F;
		long hexBytes = 0xFF_EC_DE_5E;
		long hexWords = 0xCAFE_BABE;
		long maxLong = 0x7fff_ffff_ffff_ffffL;
		byte nybbles = 0b0010_0101;
		long bytes = 0b11010010_01101001_10010100_10010010;

		System.out.println("creditCardNumber = " + creditCardNumber);
		System.out.println("socialSecurityNumber = " + socialSecurityNumber);
		System.out.println("pi = " + pi);

		System.out.println("hexBytes = " + hexBytes);
		System.out.println("hexWords = " + hexWords);
		System.out.println("maxLong = " + maxLong);
		System.out.println("nybbles = " + nybbles);
		System.out.println("bytes = " + bytes);

		// float pi1=3_.1415F; // Invalid; cannot put underscores adjacent to a decimal point
		// float pi2=3._ 1415F; // Invalid; cannot put underscores adjacent to a decimal point
		// long socialSecurityNumber1=999_99_9999_ L; // Invalid; cannot put underscores prior to an L suffix

		// int x1=_52; // This is an identifier, not a numeric literal
		int x2 = 5_2; // OK (decimal literal)
		// int x3=52_; // Invalid; cannot put underscores at the end of a literal
		int x4 = 5_______2; // OK (decimal literal)

		// int x5=0_ x52; // Invalid; cannot put underscores in the 0x radix prefix
		// int x6=0x_ 52; // Invalid; cannot put underscores at the beginning of a number
		int x7 = 0x5_2; // OK (hexadecimal literal)
		// int x8=0x52_; // Invalid; cannot put underscores at the end of a number

		int x9 = 0_52; // OK (octal literal)
		int x10 = 05_2; // OK (octal literal)
		// int x11=052_; // Invalid; cannot put underscores at the end of a number

		System.out.println("x2 = " + x2);
		System.out.println("x4 = " + x4);
		System.out.println("x7 = " + x7);
		System.out.println("x9 = " + x9);
		System.out.println("x10 = " + x10);
	}

	public static void main(String[] args){
		TestDataTypeV7 test = new TestDataTypeV7();

		System.out.println("============================================================");

		test.testArray();

		System.out.println("============================================================");

		test.testBinary();

		System.out.println("============================================================");

		test.testUnderscore();

		System.out.println("============================================================");
	}
}
