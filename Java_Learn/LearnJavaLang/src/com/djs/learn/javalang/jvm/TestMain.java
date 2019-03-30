
package com.djs.learn.javalang.jvm;

public class TestMain
{
	public void testRuntime(){
		try {
			Runtime rt = Runtime.getRuntime();

			System.out.println("Runtime.maxMemory = " + rt.maxMemory());
			System.out.println("Runtime.totalMemory = " + rt.totalMemory());
			System.out.println("Runtime.freeMemory = " + rt.freeMemory());

			System.out.println("Runtime.maxMemory (MB) = " + rt.maxMemory() / 1024 / 1024);
			System.out.println("Runtime.totalMemory (MB) = " + rt.totalMemory() / 1024 / 1024);
			System.out.println("Runtime.freeMemory (MB) = " + rt.freeMemory() / 1024 / 1024);

			System.out.println("Runtime.availableProcessors = " + rt.availableProcessors());
		} catch (Exception e) {
		}
	}

	public static void main(String[] args){
		TestMain test = new TestMain();

		System.out.println("========================================");

		test.testRuntime();

		System.out.println("========================================");
	}
}
