
package dj.test.se7test;

public class OverrideTest
{
	public static void main(String[] args){
		OverrideTest a = new OverrideTest();
		a.main(5);
		System.out.println("In main method");
	}

	public void main(int a){
		System.out.println(a + " as an int");
	}

	public void main(String a){
		System.out.println(a + " as a String");
	}
}
