
package com.djs.learn.classtag.inherited;

public class ChildB extends ParentB
{
	public ChildB(){
		System.out.println("ChildB:ChildB");
	}

	protected void protLv2(){
		protLv1();
		System.out.println("ChildB:protLv2");
	}

	void inpkgLv2(){
		inpkgLv1();
		System.out.println("ChildB:inpkgLv2");
	}

	private void privLv2(){
		System.out.println("ChildB:privLv2");
	}

	public void test(){
		protLv1();

		System.out.println("----------");

		protLv2();

		System.out.println("----------");

		inpkgLv1();

		System.out.println("----------");

		inpkgLv2();
	}
}
