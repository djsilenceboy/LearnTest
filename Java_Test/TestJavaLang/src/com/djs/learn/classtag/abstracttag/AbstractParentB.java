
package com.djs.learn.classtag.abstracttag;

import com.djs.learn.classtag.interfacetag.InterfaceParentA;

// Cannot be visible outside package.
abstract class AbstractParentB implements InterfaceParentA
{
	public AbstractParentB(){
		System.out.println("AbstractParentB:AbstractParentB");
	}

	@Override
	public int getCountA(){
		System.out.println("AbstractParentB:getCountA");

		return count;
	}
}
