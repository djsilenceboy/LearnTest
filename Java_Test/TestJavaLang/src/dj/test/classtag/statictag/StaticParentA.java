
package dj.test.classtag.statictag;

public class StaticParentA
{
	private static int countA;

	private static int countB = 100;

	private static int countC;

	static {
		System.out.println("StaticParentA:static");

		countC = 500;
	}

	public static int getCountA(){
		System.out.println("StaticParentA:getCountA");

		return ++countA;
	}

	public int getCountB(){
		System.out.println("StaticParentA:getCountB");

		return ++countB;
	}

	public static int getCountC(){
		System.out.println("StaticParentA:getCountC");

		return ++countC;
	}
}
