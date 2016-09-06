
package com.djs.learn.javalang.random;

import java.util.Random;

public class TestMain
{
	public void testLong(){
		// Use time as base. It is 64 bits.
		long lSeed = System.currentTimeMillis();
		// Shift high 32 bits with low 32 bits.
		long lShift = ((lSeed & 0xFFFFFFFF00000000L) >> 32) | ((lSeed & 0xFFFFFFFFL) << 32);
		// Generate a random positive integer, 31 bits.
		int iRnd = (new Random()).nextInt(0x7FFFFFFF);
		// XOR low 31 bits. 
		long lId = lShift ^ iRnd;

		System.out.println("Seed = " + Long.toHexString(lSeed));
		System.out.println("Shift = " + Long.toHexString(lShift));
		System.out.println("Rnd = " + Integer.toHexString(iRnd));
		System.out.println("ID = " + Long.toHexString(lId));
	}

	public void testInt(){
		// Use time as base. Only keep low 32 bits.
		long lSeed = (System.currentTimeMillis() & 0xFFFFFFFFL);
		// Shift high 16 bits with low 16 bits. And positive.
		int iShift = (int)((((lSeed & 0xFFFF0000L) >> 16) | ((lSeed & 0xFFFFL) << 16)) & 0x7FFFFFFFL);
		// Generate a random positive integer, 16 bits.
		int iRnd = (new Random()).nextInt(0xFFFF);
		// XOR low 16 bits.
		int iId = iShift ^ iRnd;

		System.out.println("Seed = " + Long.toHexString(lSeed));
		System.out.println("Shift = " + Integer.toHexString(iShift));
		System.out.println("Rnd = " + Integer.toHexString(iRnd));
		System.out.println("ID = " + Integer.toHexString(iId));
	}

	public static void main(String[] args){
		TestMain rit = new TestMain();

		rit.testLong();
		rit.testInt();
	}
}
