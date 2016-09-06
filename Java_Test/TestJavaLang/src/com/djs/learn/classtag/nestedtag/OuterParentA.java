
package com.djs.learn.classtag.nestedtag;

public class OuterParentA
{
	public static class StaticInnerParentA
	{
		// static inner class can have class variable (static).
		static int age = 0;
		int count = 0;

		public StaticInnerParentA(){
			System.out.println("OuterParentA.StaticInnerParentA:StaticInnerParentA");
		}

		public int getCount(){
			System.out.println("OuterParentA.StaticInnerParentA:getCount");

			return ++count;
		}

		public void printCount(){
			System.out.println("OuterParentA.StaticInnerParentA:printCount");
			System.out.println("Count = " + count);
		}

		private void seeMe(){
			System.out.println("OuterParentA.StaticInnerParentA:seeMe");
		}

		public void testOuterClass(){
			System.out.println("OuterParentA.StaticInnerParentA:testOuterClass");

			// Illegal. static inner class does not come from outer class.
			// OuterParentA.this.printCount();

			OuterParentA outerParentA = new OuterParentA();

			outerParentA.printCount();

			// Can access private of outer inner class.
			outerParentA.seeMe();
		}
	}

	public interface StaticInnerParentB
	{
		public int getCount();

		public void printCount();
	}

	int count = 0;

	public OuterParentA(){
		System.out.println("OuterParentA:OuterParentA");
	}

	public int getCount(){
		System.out.println("OuterParentA:getCount");

		return ++count;
	}

	public void printCount(){
		System.out.println("OuterParentA:printCount");
		System.out.println("Count = " + count);
	}

	private void seeMe(){
		System.out.println("OuterParentA:seeMe");
	}

	public void testInnerClass(){
		System.out.println("OuterParentA:testInnerClass");

		StaticInnerParentA staticInnerParentA = new StaticInnerParentA();

		staticInnerParentA.printCount();

		// Can access private of static inner class.
		staticInnerParentA.seeMe();
	}
}
