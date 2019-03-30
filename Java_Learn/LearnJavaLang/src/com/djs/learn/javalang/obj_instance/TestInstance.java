
package com.djs.learn.javalang.obj_instance;

import java.io.Serializable;

public class TestInstance
{
	Cat cat = null;
	Serializable serializable = null;
	AnimalInterface animalInterface = null;
	AbstactAnimal abstractAnimal = null;
	Tiger tiger = null;

	public void test1(){
		cat = new Cat();
		serializable = cat;
		animalInterface = cat;
		abstractAnimal = cat;
	}

	public void test2(){
		serializable = new Cat();
		animalInterface = (Cat)serializable;
		abstractAnimal = (Cat)serializable;
		cat = (Cat)serializable;
	}

	public void test3(){
		animalInterface = new Cat();
		serializable = (Cat)animalInterface;
		abstractAnimal = (Cat)animalInterface;
		cat = (Cat)animalInterface;
	}

	public void test4(){
		abstractAnimal = new Cat();
		serializable = abstractAnimal;
		animalInterface = abstractAnimal;
		cat = (Cat)abstractAnimal;
	}

	public void test5(){
		cat = new Cat();
		tiger = new Tiger();
		// Incompatible conditional operand types Cat and Tiger
		// System.out.println("Cat is Tiger : " + (cat instanceof Tiger));
		System.out.println("null is Tiger : " + (null instanceof Tiger));
	}

	public void info(){
		String[] instanceNames = {"serializable", "animalInterface", "abstractAnimal", "cat"};
		Object[] instances = {serializable, animalInterface, abstractAnimal, cat};

		for (int i = 0; i < instances.length; i++) {
			System.out.printf("%15s is %15s : %b\n", instanceNames[i], "Cat", (instances[i] instanceof Cat));
			System.out.printf("%15s is %15s : %b\n", instanceNames[i], "Serializable", (instances[i] instanceof Serializable));
			System.out.printf("%15s is %15s : %b\n", instanceNames[i], "AnimalInterface", (instances[i] instanceof AnimalInterface));
			System.out.printf("%15s is %15s : %b\n", instanceNames[i], "AbstactAnimal", (instances[i] instanceof AbstactAnimal));
		}
	}

	public void content(){
		System.out.println("Cat             : " + cat.getName());
		System.out.println("AnimalInterface : " + animalInterface.getName());
		System.out.println("AbstractAnimal  : " + abstractAnimal.getName());
	}

	public static void main(String[] args){
		TestInstance test = new TestInstance();

		System.out.println("===== test 1 =====");
		test.test1();
		test.info();
		System.out.println("===== test 2 =====");
		test.test2();
		test.info();
		System.out.println("===== test 3 =====");
		test.test3();
		test.info();
		System.out.println("===== test 4 =====");
		test.test4();
		test.info();
		System.out.println("===== test 5 =====");
		test.test5();
		System.out.println("===== content =====");
		test.content();
	}
}
