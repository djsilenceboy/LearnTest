
package com.djs.learn.javalang.reflection;

public class TestMain
{
	public void test1(){
		{
			String name = "Hello";
			Class<?> cl = name.getClass();
			System.out.println("Class = " + cl);
		}

		{
			Class<?> cl = String[].class;
			System.out.println("Class = " + cl);
		}

		{
			Class<?> cl = int[].class;
			System.out.println("Class = " + cl);
		}

		{
			Class<?> cl = int.class;
			System.out.println("Class = " + cl);
		}

		{
			Class<?> cl = void.class;
			System.out.println("Class = " + cl);
		}

		try {
			Class<?> cl = Class.forName("java.lang.String");
			System.out.println("Class = " + cl);
		} catch (Exception e) {
		}

		try {
			Class<?> cl = Class.forName("[Ljava.lang.String;");
			System.out.println("Class = " + cl);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args){
		TestMain testMain = new TestMain();

		System.out.println("============================================================");

		testMain.test1();

		System.out.println("============================================================");
	}
}
