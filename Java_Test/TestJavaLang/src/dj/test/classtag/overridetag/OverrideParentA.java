
package dj.test.classtag.overridetag;

public class OverrideParentA
{
	public String name = "OverrideParentA";

	public OverrideParentA(){
		System.out.println("OverrideParentA:OverrideParentA");
	}

	public void publLv1(){
		System.out.println("OverrideParentA:publLv1");
	}

	protected void protLv1(){
		System.out.println("OverrideParentA:protLv1");
	}

	void inpkgLv1(){
		System.out.println("OverrideParentA:inpkgLv1");
	}

	private void privLv1(){
		System.out.println("OverrideParentA:privbLv1");
	}

	public Number testReturn(){
		System.out.println("OverrideParentA:testReturn");

		return new Integer(10);
	}

	public short testReturn2(){
		System.out.println("OverrideParentA:testReturn2");

		return 10;
	}
}
