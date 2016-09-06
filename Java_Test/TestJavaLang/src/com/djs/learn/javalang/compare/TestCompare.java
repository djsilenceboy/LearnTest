
package com.djs.learn.javalang.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * <pre>
============================================================
T <java.lang.Integer> = 10
T <java.lang.Integer> = 20
T <java.lang.Double> = 30.0
T <java.lang.Float> = 40.0
T <java.lang.String> = Hello
============================================================
A: T <com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB> = com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB@2a139a55
B: T <com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB> = com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB@15db9742
B: T <com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB> = com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB@6d06d69c
B: T <com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB> = com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB@7852e922
C: T <com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB> = com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB@4e25154f
C: T <com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB> = com.djs.learn.javalang.generics.SampleGeneric$SampleTypeB@70dea4e
============================================================
T <java.lang.Integer> = 10
============================================================
List <java.lang.String> = Tom
List <java.lang.String> = Jerry
List <java.lang.String> = Mary
============================================================
List <java.io.IOException> = java.io.IOException: Problem 1
List <java.io.IOException> = java.io.IOException: Problem 2
List <java.io.FileNotFoundException> = java.io.FileNotFoundException: Problem 3
 * </pre>
 */
public class TestCompare
{
	void testListSet(){
		List<String> list = new LinkedList<String>();

		list.add("test");
		list.add("test");
		list.add("Test");
		list.add("Test");

		System.out.println("list = " + list);

		Set<String> set = new HashSet<String>();

		set.add("test");
		set.add("test");
		set.add("Test");
		set.add("Test");

		System.out.println("set = " + set);
	}

	void testBinarySearch1(){
		List<Integer> list = new ArrayList<Integer>();
		int index;

		list.add(10);
		list.add(50);
		list.add(20);
		list.add(40);

		System.out.println("list 2 = " + list);

		index = Collections.binarySearch(list, 20, new MyComparator());

		System.out.println("search index = " + index);

		// Collections.sort() can only sort list! Not set or queue!!
		Collections.sort(list);

		System.out.println("list 2 = " + list);

		index = Collections.binarySearch(list, 20);

		System.out.println("search index = " + index);

		index = Collections.binarySearch(list, 20, new MyComparator());

		System.out.println("search index = " + index);

		Collections.sort(list, new MyComparator());

		System.out.println("list 2 = " + list);

		index = Collections.binarySearch(list, 40);

		System.out.println("search index = " + index);
	}

	void testArraySort(){
		List<Integer> list = new ArrayList<Integer>();

		list.add(10);
		list.add(50);
		list.add(20);
		list.add(40);

		// Another format: Integer[] arr = list.<Integer> toArray(new Integer[0]);
		Integer[] arr = list.toArray(new Integer[0]);

		System.out.println("Array = " + Arrays.toString(arr));

		Arrays.sort(arr);

		System.out.println("Array = " + Arrays.toString(arr));

		Arrays.sort(arr, new MyComparator());

		System.out.println("Array = " + Arrays.toString(arr));
	}

	void testBinarySearch2(){
		int[] arr2 = {6, -4, 12, 0, -10};

		System.out.println("Array 2 = " + Arrays.toString(arr2));

		int index = Arrays.binarySearch(arr2, 6);

		System.out.println("search index = " + index);

		Arrays.sort(arr2);

		System.out.println("Array 2 = " + Arrays.toString(arr2));

		index = Arrays.binarySearch(arr2, 6);

		System.out.println("search index = " + index);
	}

	void testComparable(){
		List<Student> students = new ArrayList<>();

		students.add(new Student("Tom", 8));
		students.add(new Student("Jerry", 6));

		System.out.println("students = " + students);

		Collections.sort(students);

		System.out.println("students (sorted) = " + students);
	}

	void testComparator(){
		List<Product> products = new ArrayList<Product>();

		products.add(new Product(50));
		products.add(new Product(10));
		products.add(new Product(20));
		products.add(new Product(40));

		System.out.println("products = " + products);

		Collections.sort(products, new ProductComparator());

		System.out.println("products (sorted) = " + products);
	}

	public static void main(String[] args){
		TestCompare test = new TestCompare();

		test.testListSet();
		System.out.println("--------------------------------------------------");

		test.testBinarySearch1();
		System.out.println("--------------------------------------------------");

		test.testArraySort();
		System.out.println("--------------------------------------------------");

		test.testBinarySearch2();
		System.out.println("--------------------------------------------------");

		test.testComparable();
		System.out.println("--------------------------------------------------");

		test.testComparator();
	}
}

class MyComparator implements Comparator<Integer>
{
	@Override
	public int compare(Integer o1, Integer o2){
		return o2 - o1;
	}
}

class Product
{
	public int price;

	public Product(int price){
		this.price = price;
	}

	@Override
	public String toString(){
		return "[price=" + price + "]";
	}
}

class ProductComparator implements Comparator<Product>
{
	@Override
	public int compare(Product o1, Product o2){
		return o1.price - o2.price;
	}
}

class Student implements Comparable<Student>
{
	String name;
	int age;

	public Student(String name, int age){
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString(){
		return "Student [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Student o){
		return name.compareTo(o.name);
	}
}
