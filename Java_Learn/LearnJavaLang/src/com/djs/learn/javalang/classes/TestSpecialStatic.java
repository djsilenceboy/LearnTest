
package com.djs.learn.javalang.classes;

// "import static" for all static methods of the class.
// It can use:
// import static com.djs.learn.javalang.classes.SampleHouse.*;
import static com.djs.learn.javalang.classes.SampleHouse.print;
import static com.djs.learn.javalang.classes.SampleHouse.print2;
import static java.util.Arrays.asList;

import java.util.List;

/**
 * <pre>
============================================================
Car = com.djs.learn.javalang.classes.Car@2a139a55
Car num = 123
Car = null
Car num = 123
============================================================
 * </pre>
 */
public class TestSpecialStatic
{
	public static void main(String[] args){
		Car car = new Car();

		System.out.println("============================================================");

		System.out.println("Car = " + car);
		System.out.println("Car num = " + car.num);
		car = null;

		System.out.println("Car = " + car);
		// Even car is null, "car.num" equals to "Car.num".
		System.out.println("Car num = " + car.num);

		System.out.println("============================================================");

		List<String> sample = asList("a", "b", "c");
		System.out.println("Sample = " + sample);

		System.out.println("============================================================");

		print();
		print2();

		System.out.println("============================================================");
	}
}

class Car
{
	public static int num = 123;
}
