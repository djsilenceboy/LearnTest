
package dj.test.classtag.nestedtag;

public class WrapperParentA extends OuterParentA.StaticInnerParentA
{
	public WrapperParentA(){
		System.out.println("WrapperParentA:WrapperParentA");
	}

	@Override
	public int getCount(){
		System.out.println("WrapperParentA:getCount");
		return ++count;
	}

	@Override
	public void printCount(){
		System.out.println("WrapperParentA:printCount");
		super.printCount();
	}
}
