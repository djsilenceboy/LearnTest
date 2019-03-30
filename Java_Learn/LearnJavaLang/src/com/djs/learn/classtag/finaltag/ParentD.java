
package com.djs.learn.classtag.finaltag;

public final class ParentD
{
	// If not initialized here, it can be initialized for one and only one time in constructor.
	final int id;

	// Initialized here, it cannot be initialized any more in constructor.
	final int sum = 123;

	// Illegal. "static final" must be initialized.
	// static final int age;

	public ParentD(){
		System.out.println("ParentD:ParentD");

		id = (int)(Math.random() * 1000);

		// Illegal. "final" instance variable can only be initialized one and only one time.
		// id = 20;

		System.out.println("ID = " + id);

		// Illegal. "final" instance variable has already been initialized.
		// sum = 456;
	}

	public void changeId(){
		// Illegal. "final" instance variable can only be initialized one and only one time in constructor.
		// id = 20;
	}
}
