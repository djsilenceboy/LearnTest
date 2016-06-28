
package dj.test.classtag;

public class ParentC
{
	public ParentC(){
		System.out.println("ParentC:ParentC");
	}

	protected void protLv1(){
		System.out.println("ParentC:protLv1");
	}

	void inpkgLv1(){
		System.out.println("ParentC:inpkgLv1");
	}

	private void privLv1(){
		System.out.println("ParentC:privLv1");
	}
}
