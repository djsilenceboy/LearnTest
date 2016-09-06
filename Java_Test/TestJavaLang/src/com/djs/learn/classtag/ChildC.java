
package com.djs.learn.classtag;

public class ChildC extends ParentC
{
	public ChildC(){
		System.out.println("ChildC:ChildC");
	}

	protected void protLv2(){
		System.out.println("ChildC:protLv2");
	}

	void inpkgLv2(){
		inpkgLv1();
		System.out.println("ChildC:inpkgLv2");
	}

	private void privLv2(){
		System.out.println("ChildC:privLv2");
	}
}
