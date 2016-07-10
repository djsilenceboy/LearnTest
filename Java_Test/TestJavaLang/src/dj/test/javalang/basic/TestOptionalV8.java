
package dj.test.javalang.basic;

import java.util.Optional;

public class TestOptionalV8
{
	public void test1(){
		Optional<String> optional1 = Optional.empty();

		System.out.println("Optional 1 = " + optional1);
		System.out.println("Optional 1 isPresent() = " + optional1.isPresent());

		try {
			optional1.get();
		} catch (Exception e) {
			System.out.println("Optional 1 get() = " + e);
		}

		System.out.println("Optional 1 orElse() = " + optional1.orElse("Nothing"));
		System.out.println("Optional 1 orElseGet() = " + optional1.orElseGet(() -> "Dog"));

		try {
			optional1.orElseThrow(() -> new NullPointerException());
		} catch (Exception e) {
			System.out.println("Optional 1 orElseThrow() = " + e);
		}
	}

	public void test2(){
		Optional<String> optional2 = Optional.of("Cat");

		System.out.println("Optional 2 = " + optional2);
		System.out.println("Optional 2 isPresent() = " + optional2.isPresent());
		System.out.println("Optional 2 get() = " + optional2.get());
		System.out.println("Optional 2 orElse() = " + optional2.orElse("Nothing"));
		System.out.println("Optional 2 orElseGet() = " + optional2.orElseGet(() -> "Dog"));
	}

	public void test3(){
		Optional<String> optional3 = Optional.ofNullable(null);

		System.out.println("Optional 3 = " + optional3);

		try {
			Optional<String> optional4 = Optional.of(null);
		} catch (Exception e) {
			System.out.println("Optional 4 of(null) = " + e);
		}
	}

	public static void main(String[] args){
		TestOptionalV8 testMain = new TestOptionalV8();

		testMain.test1();
		System.out.println("============================================================");

		testMain.test2();
		System.out.println("============================================================");

		testMain.test3();
		System.out.println("============================================================");
	}
}
