
package com.djs.learn.classtag.statictag;

public class StaticChildA extends StaticParentA
{
	public static int getCountA(){
		System.out.println("StaticChildA:getCountA");

		return getCountC();
	}

	@Override
	public int getCountB(){
		System.out.println("StaticChildA:getCountB");

		return super.getCountB();
	}

	// Illegal.

	/*
	public int getCountC(){
		System.out.println("StaticChildA:getCountC");
	
		return ++countC;
	}
	*/
}
