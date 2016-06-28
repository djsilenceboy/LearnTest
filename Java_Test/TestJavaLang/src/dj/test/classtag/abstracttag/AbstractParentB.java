
package dj.test.classtag.abstracttag;

import dj.test.classtag.interfacetag.InterfaceParentA;

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
