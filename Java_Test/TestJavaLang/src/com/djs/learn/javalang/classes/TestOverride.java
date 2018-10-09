
package com.djs.learn.javalang.classes;

/**
 * <pre>
============================================================
LevelC, PlaceC, NameB
----------------------------------------
LevelC, PlaceC, NameB
----------------------------------------
LevelC, PlaceC, NameB
============================================================
Place = PlaceC
----------------------------------------
Place = PlaceB
----------------------------------------
Place = PlaceA
============================================================
LevelC, PlaceC, NameB
Super Tag = LevelB
----------------------------------------
LevelB, PlaceB, NameB
Super Tag = LevelA
----------------------------------------
LevelA, PlaceA, NameA
============================================================
 * </pre>
 */
public class TestOverride
{
	public static void main(String[] args){
		System.out.println("============================================================");
		System.out.println("Test: Override method");

		{
			LevelC levelC = new LevelC();
			levelC.print();

			System.out.println("----------------------------------------");

			LevelB levelB = levelC;
			levelB.print();

			System.out.println("----------------------------------------");

			LevelA levelA = levelC;
			levelA.print();
		}

		System.out.println("============================================================");
		System.out.println("Test: Hide static");

		{
			LevelC.printS();

			System.out.println("----------------------------------------");

			LevelB.printS();

			System.out.println("----------------------------------------");

			LevelA.printS();
		}

		System.out.println("============================================================");
		System.out.println("Test: Hide variable and super varialble");

		{
			LevelC levelC = new LevelC();
			levelC.printC();

			System.out.println("----------------------------------------");

			LevelB levelB = levelC;
			levelB.printB();

			System.out.println("----------------------------------------");

			LevelA levelA = levelC;
			levelA.printA();
		}

		System.out.println("============================================================");
	}
}

class LevelA
{
	String tag = "LevelA";
	static String place = "PlaceA";
	private String name = "NameA";

	void print(){
		System.out.println(tag + ", " + place + ", " + name);
	}

	static void printS(){
		System.out.println("Place = " + place);
	}

	void printA(){
		System.out.println(tag + ", " + place + ", " + name);
	}
}

class LevelB extends LevelA
{
	String tag = "LevelB";
	static String place = "PlaceB";
	String name = "NameB";

	@Override
	void print(){
		System.out.println(tag + ", " + place + ", " + name);
	}

	static void printS(){
		System.out.println("Place = " + place);
	}

	void printB(){
		System.out.println(tag + ", " + place + ", " + name);
		System.out.println("Super Tag = " + super.tag);
	}
}

class LevelC extends LevelB
{
	String tag = "LevelC";
	static String place = "PlaceC";

	@Override
	void print(){
		System.out.println(tag + ", " + place + ", " + name);
	}

	static void printS(){
		System.out.println("Place = " + place);
	}

	void printC(){
		System.out.println(tag + ", " + place + ", " + name);
		System.out.println("Super Tag = " + super.tag);
	}
}
