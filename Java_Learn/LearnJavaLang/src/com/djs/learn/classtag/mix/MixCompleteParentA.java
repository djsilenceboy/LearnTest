
package com.djs.learn.classtag.mix;

import com.djs.learn.classtag.abstracttag.CompleteParentC;
import com.djs.learn.classtag.interfacetag.InterfaceParentA;

public class MixCompleteParentA extends CompleteParentC implements InterfaceParentA
{
	@Override
	public int getCountA(){
		return InterfaceParentA.count;
	}

	@Override
	public int getCountB(){
		// "The field count is ambiguous".
		// return count;

		return super.count;
	}
}
