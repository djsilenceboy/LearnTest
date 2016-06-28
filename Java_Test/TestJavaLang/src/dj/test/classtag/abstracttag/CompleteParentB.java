
package dj.test.classtag.abstracttag;

public class CompleteParentB extends AbstractParentA
{
	public CompleteParentB(){
		System.out.println("CompleteParentB:CompleteParentB");
	}

	@Override
	public int getCountA(){
		System.out.println("CompleteParentB:getCountA");

		return count;
	}

	@Override
	public int getCountB(){
		System.out.println("CompleteParentB:getCountB");

		return count;
	}
}
