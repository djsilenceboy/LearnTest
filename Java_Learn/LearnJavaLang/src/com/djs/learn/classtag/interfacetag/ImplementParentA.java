
package com.djs.learn.classtag.interfacetag;

public class ImplementParentA implements InterfaceParentA
{
	public ImplementParentA(){
		System.out.println("ImplementParentA:ImplementParentA");
	}

	@Override
	public int getCountA(){
		System.out.println("ImplementParentA:getCountA");

		return count;
	}

	@Override
	public int getCountB(){
		System.out.println("ImplementParentA:getCountB");

		return count;
	}
}
