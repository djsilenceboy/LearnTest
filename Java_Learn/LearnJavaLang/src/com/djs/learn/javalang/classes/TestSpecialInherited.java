
package com.djs.learn.javalang.classes;

/**
 * <pre>
============================================================
Test: Static inherited
ActionB name = NameA
============================================================
 * </pre>
 */
public class TestSpecialInherited
{
	public static void main(String[] args){
		System.out.println("============================================================");
		System.out.println("Test: Static inherited");

		ActionB actiionB = new ActionB();
		actiionB.printB();

		System.out.println("============================================================");
	}
}

class ActionA
{
	static String name = "NameA";

	void printA(){
		System.out.println("ActionA name = " + name);
	}

}

class ActionB extends ActionA
{
	void printB(){
		System.out.println("ActionB name = " + name);
	}
}
