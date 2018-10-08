
package com.djs.learn.javalang.classes;

/**
 * <pre>
============================================================
Tag = LevelC
Tag = LevelC
Tag = LevelC
============================================================
Tag = LevelB
Tag = LevelB
============================================================
Super Tag = LevelB
============================================================
 * </pre>
 */
public class TestOverride
{
	public static void main(String[] args){
		System.out.println("============================================================");

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

		{
			LevelC.printS();

			System.out.println("----------------------------------------");

			LevelB.printS();

			System.out.println("----------------------------------------");

			LevelA.printS();
		}

		System.out.println("============================================================");

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
	static String tags = "LevelAs";

	private String name = "LevelA";

	void print(){
		System.out.println("Tag = " + tag);
		System.out.println("Tags = " + tags);
		System.out.println("Name = " + name);
	}

	static void printS(){
		System.out.println("Tags = " + tags);
	}

	void printA(){
		System.out.println("Tag = " + tag);
		System.out.println("Tags = " + tags);
		System.out.println("Name = " + name);
	}
}

class LevelB extends LevelA
{
	String tag = "LevelB";
	static String tags = "LevelBs";

	String name = "LevelB";

	@Override
	void print(){
		System.out.println("Tag = " + tag);
		System.out.println("Tags = " + tags);
		System.out.println("Name = " + name);
	}

	static void printS(){
		System.out.println("Tags = " + tags);
	}

	void printB(){
		System.out.println("Tag = " + tag);
		System.out.println("Tags = " + tags);
		System.out.println("Super Tag = " + super.tag);
		System.out.println("Name = " + name);
	}
}

class LevelC extends LevelB
{
	String tag = "LevelC";
	static String tags = "LevelCs";

	@Override
	void print(){
		System.out.println("Tag = " + tag);
		System.out.println("Tags = " + tags);
		System.out.println("Name = " + name);
	}

	static void printS(){
		System.out.println("Tags = " + tags);
	}

	void printC(){
		System.out.println("Tag = " + tag);
		System.out.println("Tags = " + tags);
		System.out.println("Super Tag = " + super.tag);
		System.out.println("Name = " + name);
	}
}
