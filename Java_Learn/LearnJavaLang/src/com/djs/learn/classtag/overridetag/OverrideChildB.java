
package com.djs.learn.classtag.overridetag;

import java.io.IOException;

public class OverrideChildB extends OverrideParentA
{
	public OverrideChildB(){
		System.out.println("OverrideChildB:OverrideChildB");
	}

	@Override
	public void testException2() throws IOException{
		System.out.println("OverrideChildB:testException2");
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

	public int testReturn2(){
		System.out.println("OverrideChildA:testReturn2");

		return 10;
	}
	
	public void testException() throws Exception{
		System.out.println("OverrideChildB:testException");
	}
	
	public void testException3() throws Exception{
		System.out.println("OverrideChildB:testException3");
	}
	*/
}
