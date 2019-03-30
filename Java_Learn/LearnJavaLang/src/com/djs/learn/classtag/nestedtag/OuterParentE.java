
package com.djs.learn.classtag.nestedtag;

public interface OuterParentE
{
	public static class StaticInnerA
	{
		public StaticInnerA(){
			System.out.println("StaticInnerA:StaticInnerA");
		}
	}

	public static interface StaticInnerB
	{}
}
