
package com.djs.learn.classtag.inherited2;

import com.djs.learn.classtag.ChildB2;

public class ChildB3 extends ChildB2
{
	public ChildB3(){
		System.out.println("ChildB3:ChildB3");
	}

	protected void protLv3(){
		// Still can see protected protLv1()!
		protLv1();
		// Still can see protected protLv2()!
		protLv2();
		System.out.println("ChildB3:protLv3");
	}

	void inpkgLv3(){
		System.out.println("ChildB3:inpkgLv3");
	}

	public void testProtPkg(){
		protLv3();
	}

	public void testInPkg(){
		inpkgLv3();
	}
}
