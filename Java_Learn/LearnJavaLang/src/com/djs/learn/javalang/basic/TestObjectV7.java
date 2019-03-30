
package com.djs.learn.javalang.basic;

import java.util.Objects;

public class TestObjectV7
{
	public void test(PersonName pn){
		PersonName pn2 = Objects.requireNonNull(pn);
	}

	public static void main(String[] args){
		TestObjectV7 testMain = new TestObjectV7();

		testMain.test(null);
		System.out.println("--------------------------------------------------");
	}
}

class PersonName
{
	private String first;
	private String last;

	@Override
	public int hashCode(){

		return Objects.hash(first, last);
	}

	@Override
	public boolean equals(Object obj){
		return Objects.equals(first, ((PersonName)obj).first) && Objects.equals(last, ((PersonName)obj).last);
	}
}
