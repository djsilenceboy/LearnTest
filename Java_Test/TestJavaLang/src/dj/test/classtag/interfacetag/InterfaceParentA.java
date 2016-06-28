
package dj.test.classtag.interfacetag;

public interface InterfaceParentA
{
	// Interface itself is implicit "abstract".
	// Field are implicit "public static final".
	// Field must be "public static final".

	// Method are implicit "public abstract".
	// Method must be "public abstract".

	int count = 0;

	// Illegal, it is implicit final, must be initialized.
	// int age;

	public int getCountA();

	int getCountB();
}
