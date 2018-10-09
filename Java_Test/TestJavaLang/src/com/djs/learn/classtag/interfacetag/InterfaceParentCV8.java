
package com.djs.learn.classtag.interfacetag;

public interface InterfaceParentCV8
{
	default String getName(){
		return "InterfaceParentBV8";
	}

	static int getCode(){
		return 111;
	}
}
