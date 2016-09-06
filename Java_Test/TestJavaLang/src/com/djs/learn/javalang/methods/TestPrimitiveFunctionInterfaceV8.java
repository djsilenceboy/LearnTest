
package com.djs.learn.javalang.methods;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;

public class TestPrimitiveFunctionInterfaceV8
{
	public void testSupplier(){
		BooleanSupplier bs = () -> true;

		System.out.println("BooleanSupplier = " + bs.getAsBoolean());

		IntSupplier is = () -> 1234;

		System.out.println("IntSupplier = " + is.getAsInt());
	}

	public void testConsumer(){
		IntConsumer ic = System.out::println;

		ic.accept(1234);
	}

	public void testPredicate(){
		IntPredicate ip = x -> x > 100;

		System.out.println("IntPredicate = " + ip.test(1234));
	}

	public void testFunction(){
		IntFunction<String> intf = x -> String.valueOf(x);

		System.out.println("IntFunction<String> = " + intf.apply(1234));
	}

	public void testUnaryOperator(){
		IntUnaryOperator iuo = x -> x * 2;

		System.out.println("IntUnaryOperator = " + iuo.applyAsInt(1234));
	}

	public void testBinaryOperator(){
		IntBinaryOperator ibo = (x, y) -> x + y;

		System.out.println("IntBinaryOperator = " + ibo.applyAsInt(1234, 2345));
	}

	public void testToFunction(){
		{
			ToIntFunction<String> tif = x -> Integer.valueOf(x);

			System.out.println("ToIntFunction<String> = " + tif.applyAsInt("1234"));
		}

		{
			DoubleToIntFunction dtif = x -> (int)x;

			System.out.println("DoubleToIntFunction = " + dtif.applyAsInt(1234.0));
		}
	}

	public static void main(String[] args){
		TestPrimitiveFunctionInterfaceV8 test = new TestPrimitiveFunctionInterfaceV8();

		test.testSupplier();
		System.out.println("============================================================");

		test.testConsumer();
		System.out.println("============================================================");

		test.testPredicate();
		System.out.println("============================================================");

		test.testFunction();
		System.out.println("============================================================");

		test.testUnaryOperator();
		System.out.println("============================================================");

		test.testBinaryOperator();
		System.out.println("============================================================");

		test.testToFunction();
		System.out.println("============================================================");
	}
}
