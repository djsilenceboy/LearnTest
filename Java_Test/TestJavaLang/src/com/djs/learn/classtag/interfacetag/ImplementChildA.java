
package com.djs.learn.classtag.interfacetag;

public class ImplementChildA implements InterfaceChildA
{
	public ImplementChildA(){
		System.out.println("ImplementChildA:ImplementChildA");
	}

	@Override
	public int getCountA(){
		System.out.println("ImplementChildA:getCountA");

		return count;
	}

	@Override
	public int getAgeA(){
		System.out.println("ImplementChildA:getAge");

		return age;
	}

	@Override
	public int getCountB(){
		System.out.println("ImplementChildA:getCountB");

		return count;
	}

}
