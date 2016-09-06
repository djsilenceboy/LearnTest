
package com.djs.learn.classtag.nestedtag;

public class OuterParentB
{
	public class InnerParentA
	{
		// Illegal. No static for non-static inner class.
		// static int age = 0;

		int count = 0;

		public InnerParentA(){
			System.out.println("OuterParentB.InnerParentA:InnerParentA");
		}

		public int getCount(){
			System.out.println("OuterParentB.InnerParentA:getCount");

			return ++count;
		}

		public void printCount(int count){
			System.out.println("OuterParentB.InnerParentA:printCount");
			System.out.println("Count param = " + count);
			System.out.println("Count inner = " + this.count);
			System.out.println("Count outer = " + OuterParentB.this.count);
		}

		private void seeMe(){
			System.out.println("OuterParentB.InnerParentA:seeMe");
		}

		public void testOuterClass(){
			System.out.println("OuterParentB.InnerParentA:testOuterClass");

			OuterParentB.this.printCount();
			// Can access private of outer inner class.
			OuterParentB.this.seeMe();
		}
	}

	int count = 0;

	public OuterParentB(){
		System.out.println("OuterParentB:OuterParentB");
	}

	public int getCount(){
		System.out.println("OuterParentB:getCount");

		return ++count;
	}

	public void printCount(){
		System.out.println("OuterParentB:printCount");
		System.out.println("Count = " + count);
	}

	private void seeMe(){
		System.out.println("OuterParentB:seeMe");
	}

	public void testInnerClass(){
		System.out.println("OuterParentB:testInnerClass");

		InnerParentA innerParentA = new InnerParentA();

		innerParentA.printCount(5);

		// Can access private of inner class.
		innerParentA.seeMe();
	}
}
