
package com.djs.learn.classtag.overridetag;

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

	@Override
	public void testInput(Number num){
		System.out.println("OverrideParentA:testInput");
	}

	// It is overload, not override "OverrideParentA:testInput".
	public void testInput(Integer num){
		System.out.println("OverrideChildB:testInput");
	}

	@Override
	public void testInput2(Integer num){
		System.out.println("OverrideParentA:testInput2");
	}

	// It is overload, not override "OverrideParentA:testInput2".
	public void testInput2(Number num){
		System.out.println("OverrideChildB:testInput2");
	}

	@Override
	public void testException(){
		System.out.println("OverrideChildB:testException");
	}

	@Override
	public void testException2(){
		System.out.println("OverrideChildB:testException2");
	}
}
