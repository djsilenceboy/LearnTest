
package com.djs.learn.classtag.overridetag;

public class OverrideChildB extends OverrideParentA
{
	public OverrideChildB(){
		System.out.println("OverrideChildB:OverrideChildB");
	}

	// All illegal.

	/*
	public static void publLv1(){
		System.out.println("OverrideChildB:publLv1");
	}
	
	@Override
	protected void publLv1(){
		System.out.println("OverrideChildB:publLv1");
	}

	@Override
	void protLv1(){
		System.out.println("OverrideChildB:protLv1");
	}

	@Override
	private void inpkgLv1(){
		System.out.println("OverrideChildB:inpkgLv1");
	}
	*/
}
