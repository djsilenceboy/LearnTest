
package com.djs.learn.javalang.methods;

import java.util.ArrayList;
import java.util.List;

public class TestLambdaV8
{
	/**
	 * To make an interface lambda compatible:
	 * 1. One and only one abstract method is allowed in interface definition, no matter extended how many interface already.
	 * 2. But it can have other default and static methods defined! Because they are not counted as "abstract method".
	 * 3. It is optional (but good habit) to add "@FunctionalInterface", which will force to check "only one abstract method" at compiling time.
	 */
	@FunctionalInterface
	interface CheckPerson
	{
		boolean test(Person p);
	}

	public void printPerson(List<Person> persons, CheckPerson tester){
		for (Person p : persons) {
			if (tester.test(p)) {
				System.out.println(p);
			}
		}
	}

	public void testPerson(){
		List<Person> persons = new ArrayList<>();

		persons.add(new Person("Jerry", 8));
		persons.add(new Person("Tom", 10));
		persons.add(new Person("Mary", 15));

		// Lambda.

		printPerson(persons, p -> p.age > 8 && p.age < 12);
		printPerson(persons, (p) -> p.age > 8 && p.age < 12);
		printPerson(persons, (Person p) -> p.age > 8 && p.age < 12);
		printPerson(persons, (Person p) -> {
			return p.age > 8 && p.age < 12;
		});
		printPerson(persons, (Person p) -> {
			int min = 8, max = 12;
			return p.age > min && p.age < max;
		});

		// Input parameter cannot be re-defined.
		/*
		printPerson(persons, (Person p) -> {
			Person p = new Person(); // Wrong!
			return p.age == 8;
		});
		*/

		System.out.println("----------------------------------------");

		// Method reference.

		printPerson(persons, p -> Person.test1(p));
		printPerson(persons, Person::test1);
		printPerson(persons, (new Person("", 0))::test2);

		System.out.println("----------------------------------------");

		// Stream.

		persons.stream().filter(p -> p.age > 8 && p.age < 12).forEach(item -> System.out.println(item));
	}

	public void printMathAction(int a, int b, MathAction act){
		System.out.println(a + " and " + b + " = " + act.action(a, b));
	}

	public void testMathAction(){
		printMathAction(20, 10, (a, b) -> a + b);
		printMathAction(20, 10, (a, b) -> a - b);
		printMathAction(20, 10, (a, b) -> a * b);
		printMathAction(20, 10, (a, b) -> a / b);

		// For multiple parameter, it must all adding type or not.
		// printMathAction(20, 10, (int a, b) -> a + b); // Wrong!
		printMathAction(20, 10, (int a, int b) -> a + b);
	}

	public void testInfoOnly(){
		InfoOnly infoOnly = () -> System.out.println("Hello!");

		infoOnly.info();
	}

	public static void main(String[] args){
		TestLambdaV8 test = new TestLambdaV8();

		System.out.println("============================================================");

		test.testPerson();

		System.out.println("============================================================");

		test.testMathAction();

		System.out.println("============================================================");

		test.testInfoOnly();

		System.out.println("============================================================");
	}
}

class Person
{
	public String name;
	public int age;

	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString(){
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public static boolean test1(Person p){
		return p.age > 8 && p.age < 12;
	}

	public boolean test2(Person p){
		return p.age > 8 && p.age < 12;
	}
}

/**
 * Only one abstract method.
 */
interface MathAction
{
	int action(int a, int b);
}

/**
 * Only one abstract method.
 */
interface InfoOnly
{
	void info();
}
