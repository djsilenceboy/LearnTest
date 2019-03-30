
package com.djs.learn.classtag.abstracttag;

/*
 * In Abstract class:
 * Function can be overridden.
 * Variable cannot be overridden.
 * Function will only find variable defined in same or previous level of class.
 */
public class CompleteParentA extends AbstractParentA
{
	String nameA = "CompleteParentA_NameA";
	String nameB = "CompleteParentA_NameB";
	private String nameC = "CompleteParentA_NameC";
	private String nameD = "CompleteParentA_NameD";
	String nameF = "CompleteParentA_NameF";
	String nameH = "CompleteParentA_NameH";

	public CompleteParentA(){
		System.out.println("CompleteParentA:CompleteParentA");
	}

	@Override
	public int getCountB(){
		System.out.println("CompleteParentA:getCountB");

		return count;
	}

	@Override
	public void printNameB(){
		System.out.println("CompleteParentA:printNameB:" + nameB);
	}

	@Override
	public void printNameD(){
		System.out.println("CompleteParentA:printNameD:" + nameD);
	}

	public void printNameE(){
		System.out.println("CompleteParentA:printNameE:" + nameE);
	}

	public void printNameF(){
		System.out.println("CompleteParentA:printNameF:" + nameF);
	}

	@Override
	public void printNameG(){
		// nameG in AbstractParentA is invisible to CompleteParentA.
		// System.out.println("CompleteParentA:printNameG:" + nameG);
		System.out.println("CompleteParentA:printNameG");
	}

	public void printNameH(){
		System.out.println("CompleteParentA:printNameH:" + nameH);
	}
}
