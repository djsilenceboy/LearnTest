
package com.djs.learn.javalang.basic2;

import java.util.Arrays;

import com.djs.learn.javalang.basic.SampleWeek;
import com.djs.learn.javalang.basic.TestEnum.Animal;

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

	public static void main(String[] args){
		TestEnum testMain = new TestEnum();

		System.out.println("============================================================");

		testMain.test1();

		System.out.println("============================================================");

		testMain.test2();

		System.out.println("============================================================");
	}
}
