
package com.djs.learn.classtag.mix;

public class SameNameA
{
	public SameNameA(){
		System.out.println("Constructor:SameNameA");
	}

	/**
	 * The method name is same as constructor.
	 */
	public void SameNameA(){
		System.out.println("Method:SameNameA");
	}

	public static void main(String[] args){
		SameNameA test = new SameNameA();

		test.SameNameA();
	}
}
