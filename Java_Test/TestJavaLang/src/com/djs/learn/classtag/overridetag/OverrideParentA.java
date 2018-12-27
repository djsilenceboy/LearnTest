
package com.djs.learn.classtag.overridetag;

import java.io.IOException;

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

	public void testInput(Number num){
		System.out.println("OverrideParentA:testInput");
	}

	public void testInput2(Integer num){
		System.out.println("OverrideParentA:testInput2");
	}

	public void testException(){
		System.out.println("OverrideParentA:testException");
	}

	public void testException2() throws Exception{
		System.out.println("OverrideParentA:testException2");
	}

	public void testException3() throws IOException{
		System.out.println("OverrideParentA:testException3");
	}
}
