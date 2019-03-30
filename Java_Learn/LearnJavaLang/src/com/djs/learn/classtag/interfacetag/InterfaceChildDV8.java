
package com.djs.learn.classtag.interfacetag;

public interface InterfaceChildDV8 extends InterfaceParentCV8, InterfaceParentDV8
{
	// As both InterfaceParentBV8 and InterfaceParentCV8 have "default String getName()",
	// this InterfaceChildBV8 must implement its own "default String getName()" to solve confusion!
	// Otherwise, not compile.
	default String getName(){
		// If want to refer to super one.
		return InterfaceParentCV8.super.getName() + InterfaceParentDV8.super.getName();
		// If ignore super one.
		// return "InterfaceChildBV8";
	}
}
