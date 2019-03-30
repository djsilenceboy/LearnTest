
package com.djs.learn.classtag.interfacetag;

public interface InterfaceChildC extends InterfaceParentA, InterfaceParentB
{
	// If the atstracted methods in two super interfaces are same, it is ok.
	// If not same, there will be compile error.
}
