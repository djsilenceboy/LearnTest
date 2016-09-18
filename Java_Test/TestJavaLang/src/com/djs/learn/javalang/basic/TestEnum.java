
package com.djs.learn.javalang.basic;

import java.util.Arrays;

public class TestEnum
{
	enum Animal
	{
		Dog, Cat, Pig
	}

	public static void main(String[] args){
		System.out.println("============================================================");

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

		System.out.println("============================================================");

		System.out.println("Animal = " + Animal.values());
		System.out.println("Animal = " + Arrays.toString(Animal.values()));
		System.out.println("Cat = " + Animal.Cat);
		System.out.println("Cat = " + Animal.Cat.ordinal());
		System.out.println("Dog = " + TestEnum.Animal.Dog.ordinal());

		System.out.println("============================================================");
	}
}
