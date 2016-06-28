
package dj.test.classtag.interfacetag;

public interface InterfaceParentBV8
{
	default String getName(){
		return "InterfaceParentBV8";
	}

	static int getCode(){
		return 111;
	}
}
