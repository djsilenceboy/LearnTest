
package com.djs.learn.classtag.interfacetag;

public class ImplementChildC implements InterfaceChildC
{
	public ImplementChildC(){
		System.out.println("ImplementChildC:ImplementChildC");
	}

	@Override
	public int getCountA(){
		System.out.println("ImplementChildC:getCountA");

		// Get hidden count.
		return InterfaceParentA.count;
	}

	@Override
	public int getCountB(){
		System.out.println("ImplementChildC:getCountB");

		// Get hidden count.
		return InterfaceParentB.count;
	}
}
