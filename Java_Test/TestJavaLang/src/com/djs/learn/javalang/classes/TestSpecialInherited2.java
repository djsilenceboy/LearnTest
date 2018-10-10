
package com.djs.learn.javalang.classes;

/**
 * <pre>
============================================================
Test: Interface static member inherited
AnimalA name = NameA
AnimalB name = NameB
============================================================
 * </pre>
 */
public class TestSpecialInherited2
{
	public static void main(String[] args){
		System.out.println("============================================================");
		System.out.println("Test: Interface static member inherited");

		AnimalX animalX = new AnimalX();
		animalX.printX();

		System.out.println("============================================================");
	}
}

interface AnimalA
{
	static String name = "NameA";
}

interface AnimalB
{
	static void print(){
		System.out.println("AnimalB name = NameB");
	}
}

class AnimalX implements AnimalA, AnimalB
{
	void printX(){
		// Cannot use static member directly as extented from a class.
		// Must use <Interface>.<StaticMember> to refer static variable in interface.
		System.out.println("AnimalA name = " + AnimalA.name);
		AnimalB.print();
	}
}
