
package com.djs.learn.classtag.abstracttag;

import com.djs.learn.classtag.interfacetag.InterfaceParentA;

public abstract class AbstractParentA implements InterfaceParentA
{
	String nameA = "AbstractParentA_NameA";
	String nameB = "AbstractParentA_NameB";
	private String nameC = "AbstractParentA_NameC";
	private String nameD = "AbstractParentA_NameD";
	String nameE = "AbstractParentA_NameE";
	String nameF = "AbstractParentA_NameF";
	private String nameG = "AbstractParentA_NameG";
	private String nameH = "AbstractParentA_NameH";

	public AbstractParentA(){
		System.out.println("AbstractParentA:AbstractParentA");
	}

	@Override
	public int getCountA(){
		System.out.println("AbstractParentA:getCountA");

		return count;
	}

	public void printNameA(){
		System.out.println("AbstractParentA:printNameA:" + nameA);
	}

	public void printNameB(){
		System.out.println("AbstractParentA:printNameB:" + nameB);
	}

	public void printNameC(){
		System.out.println("AbstractParentA:printNameC:" + nameC);
	}

	public void printNameD(){
		System.out.println("AbstractParentA:printNameD:" + nameD);
	}

	public void printNameG(){
	}
}
