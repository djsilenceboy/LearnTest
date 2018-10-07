
package com.djs.learn.javalang.classes;

/**
 * <pre>
Static counter = 1
Instance counter = 1
Instance counter = 2
Instance counter = 3
Static counter = 2
============================================================
 * </pre>
 */
public class TestSpecialStaticInit2
{
	public static int counterA = 1;
	static {
		System.out.println("Static counter = " + counterA++);
	}

	public int counterB = 1;
	{
		System.out.println("Instance counter = " + counterB++);
	}

	public TestSpecialStaticInit2(){
		System.out.println("Instance counter = " + counterB++);
	}

	static {
		new TestSpecialStaticInit2();
	}

	static {
		// This static output is output after instance output!
		System.out.println("Static counter = " + counterA++);
	}

	{
		System.out.println("Instance counter = " + counterB++);
	}

	public static void main(String[] args){
		System.out.println("============================================================");
	}
}
