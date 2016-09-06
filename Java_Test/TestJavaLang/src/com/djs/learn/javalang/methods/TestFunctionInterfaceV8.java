
package com.djs.learn.javalang.methods;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class TestFunctionInterfaceV8
{
	public void testSupplier(){
		Supplier<LocalDate> s1 = LocalDate::now;
		Supplier<LocalDate> s2 = () -> LocalDate.now();

		System.out.println("s1 = " + s1);
		System.out.println("s2 = " + s2);

		System.out.println("s1.get() = " + s1.get());
		System.out.println("s2.get() = " + s2.get());
	}

	public void testConsumer(){
		Consumer<String> c1 = System.out::println;
		Consumer<String> c2 = x -> System.out.println(x);

		System.out.println("c1 = " + c1);
		System.out.println("c2 = " + c2);

		c1.accept("Hello");
		c2.accept("Hello");
	}

	public void testBiConsumer(){
		Map<String, Integer> map = new HashMap<>();
		BiConsumer<String, Integer> bc1 = map::put;
		BiConsumer<String, Integer> bc2 = (k, v) -> map.put(k, v);

		System.out.println("c1 = " + bc1);
		System.out.println("c2 = " + bc2);

		bc1.accept("Hello", 1);
		bc2.accept("World", 2);

		System.out.println("map = " + map);
	}

	public void testPredicate(){
		Predicate<String> p1 = String::isEmpty;
		Predicate<String> p2 = x -> x.isEmpty();

		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);

		System.out.println("p1.test() = " + p1.test("Hello"));
		System.out.println("p2.test() = " + p2.test(""));
	}

	public void testBiPredicate(){
		BiPredicate<String, String> bp1 = String::startsWith;
		BiPredicate<String, String> bp2 = (x, y) -> x.startsWith(y);

		System.out.println("bp1 = " + bp1);
		System.out.println("bp2 = " + bp2);

		System.out.println("bp1.test() = " + bp1.test("Hello", "He"));
		System.out.println("bp2.test() = " + bp2.test("Hello", "Hi"));
	}

	public void testFunction(){
		Function<String, Integer> f1 = String::length;
		Function<String, Integer> f2 = x -> x.length();

		System.out.println("f1 = " + f1);
		System.out.println("f2 = " + f2);

		System.out.println("f1.apply() = " + f1.apply("Hello"));
		System.out.println("f2.apply() = " + f2.apply(""));
	}

	public void testBiFunction(){
		BiFunction<String, String, String> bf1 = String::concat;
		BiFunction<String, String, String> bf2 = (x, y) -> x.concat(y);

		System.out.println("bf1 = " + bf1);
		System.out.println("bf2 = " + bf2);

		System.out.println("bf1.apply() = " + bf1.apply("Hello", "World"));
		System.out.println("bf2.apply() = " + bf2.apply("Hel", "Wor"));
	}

	public void testUnaryOperator(){
		UnaryOperator<String> uo1 = String::toUpperCase;
		UnaryOperator<String> uo2 = x -> x.toUpperCase();

		System.out.println("uo1 = " + uo1);
		System.out.println("uo2 = " + uo2);

		System.out.println("uo1.apply() = " + uo1.apply("Hello"));
		System.out.println("uo2.apply() = " + uo2.apply("Hello"));
	}

	public void testBinaryOperator(){
		BinaryOperator<String> bo1 = String::concat;
		BinaryOperator<String> bo2 = (x, y) -> x.concat(y);

		System.out.println("bo1 = " + bo1);
		System.out.println("bo2 = " + bo2);

		System.out.println("bo1.apply() = " + bo1.apply("Hello", "World"));
		System.out.println("bo2.apply() = " + bo2.apply("Hel", "Wor"));
	}

	public static void main(String[] args){
		TestFunctionInterfaceV8 test = new TestFunctionInterfaceV8();

		test.testSupplier();
		System.out.println("============================================================");

		test.testConsumer();
		System.out.println("============================================================");

		test.testBiConsumer();
		System.out.println("============================================================");

		test.testPredicate();
		System.out.println("============================================================");

		test.testBiPredicate();
		System.out.println("============================================================");

		test.testFunction();
		System.out.println("============================================================");

		test.testBiFunction();
		System.out.println("============================================================");

		test.testUnaryOperator();
		System.out.println("============================================================");

		test.testBinaryOperator();
		System.out.println("============================================================");
	}
}
