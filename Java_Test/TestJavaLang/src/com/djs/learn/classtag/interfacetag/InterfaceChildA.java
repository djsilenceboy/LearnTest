
package com.djs.learn.classtag.interfacetag;

public interface InterfaceChildA extends InterfaceParentA
{
	int age = 0;

	// It will hide "InterfaceParentA.count".
	int count = 10;

	public int getAgeA();

	public int getCountB();
}
