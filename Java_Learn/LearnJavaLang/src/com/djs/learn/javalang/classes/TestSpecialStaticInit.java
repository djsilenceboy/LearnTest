
package com.djs.learn.javalang.classes;

/**
 * <pre>
Static counter = 1
============================================================
Instance counter = 1
Instance counter = 2
============================================================
 * </pre>
 */
public class TestSpecialStaticInit
{
	public static int counterA = 1;
	static {
		System.out.println("Static counter = " + counterA++);
	}

	public int counterB = 1;
	{
		System.out.println("Instance counter = " + counterB++);
	}

	public TestSpecialStaticInit(){
		System.out.println("Instance counter = " + counterB++);
	}

	static {
		System.out.println("Static counter = " + counterA++);
	}

	{
		System.out.println("Instance counter = " + counterB++);
	}

	public static void main(String[] args){
		// This line will output after those static output.
		// Because at this time, Class is not initialized yet.
		System.out.println("============================================================");

		new TestSpecialStaticInit();

		System.out.println("============================================================");
	}
}
