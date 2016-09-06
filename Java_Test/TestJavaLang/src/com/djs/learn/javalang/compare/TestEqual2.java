
package com.djs.learn.javalang.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * <pre>
a.Car: name = aCar
b.Car: name = bCar
Exception 1 = java.lang.ClassCastException: com.djs.learn.javalang.obj_compare.sample2.Car cannot be cast to com.djs.learn.javalang.obj_compare.sample1.Car
Exception 2 = java.lang.ClassCastException: com.djs.learn.javalang.obj_compare.sample1.Car cannot be cast to com.djs.learn.javalang.obj_compare.sample2.Car
 * </pre>
 */
public class TestEqual2
{
	public void printA(com.djs.learn.javalang.compare.sample1.Car car){
		System.out.println("a.Car: " + car);
	}

	public void printB(com.djs.learn.javalang.compare.sample2.Car car){
		System.out.println("b.Car: " + car);
	}

	public void testClassEqual(){
		com.djs.learn.javalang.compare.sample1.Car aCar = new com.djs.learn.javalang.compare.sample1.Car("aCar");
		com.djs.learn.javalang.compare.sample2.Car bCar = new com.djs.learn.javalang.compare.sample2.Car("bCar");

		printA(aCar);
		printB(bCar);

		// Cannot compile.
		// printA( bCar );
		// printB( aCar );

		// Cannot compile.
		// printA( (com.a.Car)bCar );
		// printB( (com.b.Car)aCar );

		System.out.println("----------------------------------------");

		try {
			printA((com.djs.learn.javalang.compare.sample1.Car)((Object)bCar));
		} catch (Exception e) {
			System.err.println("Exception 1 = " + e);
		}

		try {
			printB((com.djs.learn.javalang.compare.sample2.Car)((Object)aCar));
		} catch (Exception e) {
			System.err.println("Exception 2 = " + e);
		}

		// Exception 1 = java.lang.ClassCastException: com.djs.learn.javalang.obj_compare.sample2.Car cannot be cast to com.djs.learn.javalang.obj_compare.sample1.Car
		// Exception 2 = java.lang.ClassCastException: com.djs.learn.javalang.obj_compare.sample1.Car cannot be cast to com.djs.learn.javalang.obj_compare.sample2.Car
	}

	public void testListEqual(){
		List<String> listA = new ArrayList<String>();
		listA.add("Tom");
		listA.add("Jerry");

		List<String> listB = new ArrayList<String>();
		listB.add("Tom");
		listB.add("Jerry");

		List<String> listC = new Vector<String>();
		listC.add("Tom");
		listC.add("Jerry");

		System.out.println("List A (" + listA.getClass().getName() + ") = " + listA);
		System.out.println("List B (" + listB.getClass().getName() + ") = " + listB);
		System.out.println("List C (" + listC.getClass().getName() + ") = " + listC);
		System.out.println("List A == List B: " + (listA == listB));
		System.out.println("List A equals List B: " + (listA.equals(listB)));
		System.out.println("List A == List C: " + (listA == listC));
		System.out.println("List A equals List C: " + (listA.equals(listC)));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		TestEqual2 testMain = new TestEqual2();

		System.out.println("============================================================");

		testMain.testClassEqual();

		System.out.println("============================================================");

		testMain.testListEqual();

		System.out.println("============================================================");
	}
}
