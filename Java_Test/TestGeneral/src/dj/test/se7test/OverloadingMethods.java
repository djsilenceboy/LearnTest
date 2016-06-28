
package dj.test.se7test;

public class OverloadingMethods
{
	String name;

	public static void main(String[] args){
		Integer i = new Integer(17);
		double j = 2.0;

		stuff(i);
		stuff(j);
	}

	static void stuff(int a){
		System.out.println("int");
	}

	static void stuff(Integer a){
		System.out.println("Integer");
	}

	static void stuff(double a){
		System.out.println("double");
	}

	static void stuff(Double a){
		System.out.println("Double");
	}
}
