
package com.djs.learn.classtag.interfacetag;

public interface InterfaceChildBV8 extends InterfaceParentBV8, InterfaceParentCV8
{
	// As both InterfaceParentBV8 and InterfaceParentCV8 have "default String getName()",
	// this InterfaceChildBV8 must implement its own "default String getName()" to solve confusion!
	// Otherwise, not compile.
	default String getName(){
		// If want to refer to super one.
		return InterfaceParentBV8.super.getName() + InterfaceParentCV8.super.getName();
		// If ignore super one.
		// return "InterfaceChildBV8";
	}
}
