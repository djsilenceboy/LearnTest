
package com.djs.learn.classtag.finaltag;

public class ParentE
{
	final int id;

	public ParentE(){
		System.out.println("ParentE:ParentE");

		id = (int)(Math.random() * 1000);

		System.out.println("ID = " + id);
	}

	public ParentE(int assignedId){
		System.out.println("ParentE:ParentE2");

		id = assignedId;

		System.out.println("ID = " + id);
	}

	public static final String getName(){
		return "Tom";
	}

	public final int getCounter(){
		return 10;
	}
}
