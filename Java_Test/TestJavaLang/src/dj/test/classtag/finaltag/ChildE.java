
package dj.test.classtag.finaltag;

public class ChildE extends ParentE
{
	public ChildE(){
		System.out.println("ChildE:ChildE");

		// id = (int)(Math.random() * 1000);

		System.out.println("ID = " + id);
	}

	// Illegal.

	/*
	public static final String getName(){
		return "Tom";
	}
	
	@Override
	public final int getCounter(){
		return 10;
	}
	*/
}
