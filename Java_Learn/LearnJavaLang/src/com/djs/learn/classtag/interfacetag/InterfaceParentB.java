
package com.djs.learn.classtag.interfacetag;

public interface InterfaceParentB
{
	// Interface itself is implicit "abstract".
	// Field are implicit "public static final".
	// Field must be "public static final".

	// Method are implicit "public abstract".
	// Method must be "public abstract".

	int count = 10;

	// Illegal, it is implicit final, must be initialized.
	// int age;

	public int getCountA();

	int getCountB();
}
