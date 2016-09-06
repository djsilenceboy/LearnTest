
package com.djs.learn.classtag.nestedtag;

public class WrapperParentB implements OuterParentA.StaticInnerParentB
{
	public WrapperParentB(){
		System.out.println("WrapperParentB:WrapperParentB");
	}

	@Override
	public int getCount(){
		System.out.println("WrapperParentB:getCount");
		return 0;
	}

	@Override
	public void printCount(){
		System.out.println("WrapperParentB:printCount");
	}
}
