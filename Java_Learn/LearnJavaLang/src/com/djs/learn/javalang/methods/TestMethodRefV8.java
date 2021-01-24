
package com.djs.learn.javalang.methods;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Method reference can be:
 * Class::StaticMethod
 * Object::InstanceMethod
 * Class::InstanceMethod
 */
public class TestMethodRefV8
{
	void test1(){
		{
			// Class::StaticMethod
			// Convert::addThem
			// (String a, String b) -> Convert.addThem(a, b)
			DoSomething<String, String> action = Convert::addThem;

			action.doIt("Hello", "world");
		}

		System.out.println("------------------------------");

		{
			// Class::StaticMethod
			// Swap::change
			// (String a, String b) -> Swap.change(a, b)
			DoSomething<String, String> action = Swap::change;
			action.doIt("Hello", "world");
		}

		System.out.println("------------------------------");

		{
			// Object::InstanceMethod
			// convert::addThem
			// (String a, String b) -> convert.addThem(a, b)
			Convert convert = new Convert();
			DoSomething<String, String> action = convert::sumThem;
			action.doIt("Hello", "world");
		}
	}

	void test2(){
		{
			// Class::InstanceMethod
			// Convert::concat
			// (Convert c, String a) -> c.concat(a)
			DoSomething<Convert, String> action = Convert::concat;
			Convert convert = new Convert();

			// It actually calls convert.concat("Hello").
			action.doIt(convert, "Hello");
		}

		System.out.println("------------------------------");

		{
			// Class::InstanceMethod
			// Convert::concat2
			// (Convert c, String a, String b) -> c.concat(a, b)
			DoSomething2<Convert, String, String> action = Convert::concat2;
			Convert convert = new Convert();

			// It actually calls convert.concat("Hello", "world").
			action.doIt(convert, "Hello", "world");
		}
	}

	<T> boolean checkFruit(T fruit, Predicate<T> p){
		return p.test(fruit);
	}

	<T, U> Boolean checkFruit2(T fruit, U weight, BiFunction<T, U, Boolean> f){
		return f.apply(fruit, weight);
	}

	<T> boolean checkRed(T fruit){
		return true;
	}

	<T, U> boolean checkLarge(T fruit, U weight){
		return true;
	}

	void test3(){
		Apple apple = new Apple();

		{
			// this::checkRed
			// (Apple a) -> this.checkRed(a)
			boolean results = checkFruit(apple, this::checkRed);
			System.out.println("Apple results = " + results);
		}

		{
			// Apple::checkRed
			// (Apple a) -> Apple.checkRed(a)
			boolean results = checkFruit(apple, Apple::checkRed);
			System.out.println("Apple results = " + results);
		}

		{
			// Apple::isRed
			// (Apple a) -> a.isRed()
			boolean results = checkFruit(apple, Apple::isRed);
			System.out.println("Apple results = " + results);
		}

		{
			// this::checkLarge
			// (Apple a, Integer w) -> this.checkLarge(a, w)
			boolean results = checkFruit2(apple, 1, this::checkLarge);
			System.out.println("Apple results2 = " + results);
		}

		{
			// Apple::checkLarge
			// (Apple a, Integer w) -> Apple.checkLarge(a, w)
			boolean results = checkFruit2(apple, 1, Apple::checkLarge);
			System.out.println("Apple results2 = " + results);
		}

		{
			// Apple::isLarge
			// (Apple a, Integer w) -> a.isLarge( w)
			boolean results = checkFruit2(apple, 1, Apple::isLarge);
			System.out.println("Apple results2 = " + results);
		}
	}

	public static void main(String[] args){
		TestMethodRefV8 testMain = new TestMethodRefV8();

		testMain.test1();
		System.out.println("--------------------------------------------------");

		testMain.test2();
		System.out.println("--------------------------------------------------");

		testMain.test3();
		System.out.println("--------------------------------------------------");
	}
}

interface DoSomething<T, U>
{
	void doIt(T t, U u);
}

interface DoSomething2<T, U, V>
{
	void doIt(T t, U u, V v);
}

class Convert
{
	static void addThem(String a, String b){
		System.out.println("added = " + a + "," + b);
	}

	void sumThem(String a, String b){
		System.out.println("sum = " + a + "," + b);
	}

	void concat(String a){
		System.out.println("Concat = " + a + "," + a);
	}

	void concat2(String a, String b){
		System.out.println("Concat = " + a + "," + b);
	}
}

class Swap
{
	static void change(String a, String b){
		System.out.println("changed = " + b + "," + a);
	}
}

class Apple
{
	boolean isRed(){
		return true;
	}

	boolean isLarge(int weight){
		return (weight > 0);
	}

	static boolean checkRed(Apple apple){
		return true;
	}

	static boolean checkLarge(Apple apple, int weight){
		return (weight > 0);
	}
}
