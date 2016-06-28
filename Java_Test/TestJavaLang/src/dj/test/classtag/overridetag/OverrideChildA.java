
package dj.test.classtag.overridetag;

public class OverrideChildA extends OverrideParentA
{
	public String name = "OverrideChildA";

	public OverrideChildA(){
		System.out.println("OverrideChildA:OverrideChildA");
	}

	@Override
	public void publLv1(){
		System.out.println("OverrideChildA:publLv1");
	}

	@Override
	public void protLv1(){
		System.out.println("OverrideChildA:protLv1");
	}

	@Override
	public void inpkgLv1(){
		System.out.println("OverrideChildA:inpkgLv1");
	}

	// Not override "OverrideParentA:privbLv1".
	private void privLv1(){
		System.out.println("OverrideChildA:privbLv1");
	}

	@Override
	public Integer testReturn(){
		System.out.println("OverrideChildA:testReturn");

		return new Integer(10);
	}

	// Illegal.
	/*
	@Override
	public int testReturn2(){
		System.out.println("OverrideChildA:testReturn2");

		return 10;
	}
	*/
}
