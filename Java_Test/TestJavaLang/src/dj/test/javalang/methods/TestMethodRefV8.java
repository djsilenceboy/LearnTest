
package dj.test.javalang.methods;

public class TestMethodRefV8
{
	void test1(){
		DoSomething<String, String> action = Combine::addThem;

		action.doIt("Hello", "world");

		action = Swap::change;

		action.doIt("Hello", "world");
	}

	public static void main(String[] args){
		TestMethodRefV8 testMain = new TestMethodRefV8();

		testMain.test1();
		System.out.println("--------------------------------------------------");
	}
}

interface DoSomething<T, U>
{
	void doIt(T t, U u);
}

class Combine
{
	static void addThem(String a, String b){
		System.out.println("added = " + a + "," + b);
	}
}

class Swap
{
	static void change(String a, String b){
		System.out.println("changed = " + b + "," + a);
	}
}
