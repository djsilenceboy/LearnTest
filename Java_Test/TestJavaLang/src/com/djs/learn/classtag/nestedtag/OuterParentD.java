
package com.djs.learn.classtag.nestedtag;

import com.djs.learn.classtag.interfacetag.InterfaceParentA;

public class OuterParentD
{
	public OuterParentD(){
		System.out.println("OuterParentD:OuterParentD");
	}

	public void testAnonymousClass(InterfaceParentA interfaceParentA){
		System.out.println("OuterParentD:testAnonymousClass");

		interfaceParentA.getCountA();
	}
}
