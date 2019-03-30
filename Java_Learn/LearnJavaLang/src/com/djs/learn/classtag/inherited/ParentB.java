
package com.djs.learn.classtag.inherited;

public class ParentB
{
	public ParentB(){
		System.out.println("ParentB:ParentB");
	}

	protected void protLv1(){
		System.out.println("ParentB:protLv1");
	}

	void inpkgLv1(){
		System.out.println("ParentB:inpkgLv1");
	}

	private void privLv1(){
		System.out.println("ParentB:privLv1");
	}
}
