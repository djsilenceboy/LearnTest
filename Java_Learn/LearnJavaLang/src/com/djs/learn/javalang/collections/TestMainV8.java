
package com.djs.learn.javalang.collections;

import java.util.ArrayList;
import java.util.List;

public class TestMainV8
{
	public void testNew(){
		List<Integer> list = new ArrayList<>();

		list.add(8);
		list.add(18);
		list.add(28);
		list.add(38);
		list.add(48);

		System.out.println(list.getClass().getName() + " = " + list);

		list.removeIf(x -> x > 30);

		System.out.println(list.getClass().getName() + " = " + list);

		list.replaceAll(x -> x * 10);

		System.out.println(list.getClass().getName() + " = " + list);

		list.forEach(x -> System.out.println("-> " + x));
	}

	public static void main(String[] args){
		TestMainV8 testMain = new TestMainV8();

		testMain.testNew();
		System.out.println("============================================================");
	}
}
