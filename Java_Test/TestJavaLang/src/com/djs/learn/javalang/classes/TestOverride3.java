
package com.djs.learn.javalang.classes;

/**
 * <pre>
============================================================
Test: Override/hide method
----------------------------------------
Tag0
----------------------------------------
============================================================
 * </pre>
 */
public class TestOverride3
{
	private void print(){
		System.out.println("Tag0");
	}

	public static void main(String[] args){
		System.out.println("============================================================");
		System.out.println("Test: Override/hide method");
		System.out.println("----------------------------------------");

		{
			TestOverride3 test = new HomeA();
			// the main can see its own private.
			test.print();
		}

		System.out.println("----------------------------------------");

		{
			HomeB test = new HomeC();
			// the main cannot see other's private.
			// test.print();
		}

		System.out.println("============================================================");
	}
}

class HomeA extends TestOverride3
{
	// It re-defines print(), not override.
	void print(){
		System.out.println("TagA");
	}
}

class HomeB
{
	private void print(){
		System.out.println("TagB");
	}
}

class HomeC extends HomeB
{
	// It re-defines print(), not override.
	void print(){
		System.out.println("TagC");
	}
}
