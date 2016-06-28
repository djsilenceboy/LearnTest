
package dj.test.classtag.nestedtag;

import dj.test.classtag.interfacetag.InterfaceParentA;

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
