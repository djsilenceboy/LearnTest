
package com.djs.learn.classtag.interfacetag2;

import com.djs.learn.classtag.interfacetag.InterfaceParentA;

public class ImplementParentC implements InterfaceParentA
{
	public ImplementParentC(){
		System.out.println("ImplementParentC:ImplementParentC");
	}

	@Override
	public int getCountA(){
		System.out.println("ImplementParentC:getCountA");

		return count;
	}

	@Override
	public int getCountB(){
		System.out.println("ImplementParentC:getCountB");

		return count;
	}
}
