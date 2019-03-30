
package com.djs.learn.classtag.mix;

import com.djs.learn.classtag.abstracttag.AbstractParentC;
import com.djs.learn.classtag.interfacetag.InterfaceParentA;

public class MixCompleteParentB extends AbstractParentC implements InterfaceParentA
{
	// getCountA() defined in InterfaceParentA is implemented in AbstractParentC.

	@Override
	public int getCountB(){
		return count;
	}
}
