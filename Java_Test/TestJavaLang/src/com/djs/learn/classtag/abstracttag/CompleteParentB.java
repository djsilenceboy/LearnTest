
package com.djs.learn.classtag.abstracttag;

public class CompleteParentB extends AbstractParentA
{
	public CompleteParentB(){
		System.out.println("CompleteParentB:CompleteParentB");
	}

	// Compiling error: "Cannot reduce the visibility of the inherited method from AbstractParentA".
	/*
	protected int getCountA(){

	}
	*/

	@Override
	public int getCountA(){
		System.out.println("CompleteParentB:getCountA");

		return count;
	}

	@Override
	public int getCountB(){
		System.out.println("CompleteParentB:getCountB");

		return count;
	}
}
