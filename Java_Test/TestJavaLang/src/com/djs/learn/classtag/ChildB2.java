
package com.djs.learn.classtag;

import com.djs.learn.classtag.inherited.ParentB;

public class ChildB2 extends ParentB
{
	public ChildB2(){
		System.out.println("ChildB2:ChildB2");
	}

	protected void protLv2(){
		protLv1();
		System.out.println("ChildB2:protLv2");
	}

	private void privLv2(){
		System.out.println("ChildB2:privLv2");
	}
}
