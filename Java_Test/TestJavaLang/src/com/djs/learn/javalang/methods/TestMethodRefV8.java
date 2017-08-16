
package com.djs.learn.javalang.methods;

/**
 * Method reference can be:
 * Class::StaticMethod
 * Object::InstanceMethod
 * Class::InstanceMethod
 */
public class TestMethodRefV8
{
	void test1(){
		DoSomething<String, String> action = Convert::addThem;

		action.doIt("Hello", "world");

		System.out.println("------------------------------");

		action = Swap::change;
		action.doIt("Hello", "world");

		System.out.println("------------------------------");

		Convert convert = new Convert();
		action = convert::sumThem;
		action.doIt("Hello", "world");
	}

	void test2(){
		DoSomething<Convert, String> action = Convert::concat;
		Convert convert = new Convert();

		// It actually calls convert.concat("Hello").
		action.doIt(convert, "Hello");
	}

	public static void main(String[] args){
		TestMethodRefV8 testMain = new TestMethodRefV8();

		testMain.test1();
		System.out.println("--------------------------------------------------");

		testMain.test2();
		System.out.println("--------------------------------------------------");
	}
}

interface DoSomething<T, U>
{
	void doIt(T t, U u);
}

class Convert
{
	static void addThem(String a, String b){
		System.out.println("added = " + a + "," + b);
	}

	void sumThem(String a, String b){
		System.out.println("sum = " + a + "," + b);
	}

	void concat(String a){
		System.out.println("Concat = " + a + "," + a);
	}
}

class Swap
{
	static void change(String a, String b){
		System.out.println("changed = " + b + "," + a);
	}
}
