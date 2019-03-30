
package com.djs.learn.classtag.interfacetag;

public interface InterfaceParentDV8
{
	default String getName(){
		return "InterfaceParentCV8";
	}

	static int getCode(){
		return 222;
	}
}
