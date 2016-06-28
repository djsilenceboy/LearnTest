
package dj.test.classtag.statictag;

public class StaticParentB
{
	private static int countA;

	static {
		System.out.println("StaticParentB:static");
	}

	public static int getCountA(){
		System.out.println("StaticParentB:getCountA");

		return ++countA;
	}
}
