
package com.djs.learn.javalang.basic;

import java.util.Arrays;

public class TestEnum
{
	public void test1(){
		for (SampleWeek item : SampleWeek.values()) {
			System.out.println("Week item = " + item);
			System.out.println("Week item: name = " + item.name());
			System.out.println("Week item: ordinal = " + item.ordinal());
			System.out.println("Week item: count = " + item.getCount());
			System.out.println("Week item: actor = " + item.getActor());
			System.out.println("--------------------");
		}

		System.out.println("----------------------------------------");

		SampleWeek.Monday.setCount(SampleWeek.Monday.getCount() + 1);
		System.out.println("SampleWeek.Monday: count = " + SampleWeek.Monday.getCount());

		SampleWeek.Monday.setCount(SampleWeek.Monday.getCount() + 1);
		System.out.println("SampleWeek.Monday: count = " + SampleWeek.Monday.getCount());

		System.out.println("SampleWeek.Tuesday: count = " + SampleWeek.Tuesday.getCount());
	}

	public void test2(){
		System.out.println("Animal = " + Animal.values());
		System.out.println("Animal = " + Arrays.toString(Animal.values()));
		System.out.println("Cat = " + Animal.Cat);
		System.out.println("Cat = " + Animal.valueOf("Cat"));
		System.out.println("Cat = " + Animal.Cat.ordinal());
		System.out.println("Dog = " + Animal.Dog.ordinal());
	}

	public void test3(){
		System.out.println("Size = " + Arrays.toString(Size.values()));
		System.out.println("Large abbr = " + Size.LARGE.getAbbr());
	}

	public void test4(){
		System.out.println("Operation Add = " + Operation.ADD.eval(2, 3));
		System.out.println("Operation Sub = " + Operation.SUB.eval(2, 3));
	}

	public static void main(String[] args){
		TestEnum testMain = new TestEnum();

		System.out.println("============================================================");

		testMain.test1();

		System.out.println("============================================================");

		testMain.test2();

		System.out.println("============================================================");

		testMain.test3();

		System.out.println("============================================================");

		testMain.test4();

		System.out.println("============================================================");
	}
}

enum Animal
{
	Dog, Cat, Bird
}

enum Size
{
	SMALL("S"), MIDIUM("M"), LARGE("L");

	private String abbr;

	private Size(String abbr){
		this.abbr = abbr;
	}

	public String getAbbr(){
		return abbr;
	}
}

enum Operation
{
	ADD {
		@Override
		public int eval(int a, int b){
			return a + b;
		}
	},

	SUB {
		@Override
		public int eval(int a, int b){
			return a - b;
		}
	};

	public abstract int eval(int a, int b);
}
