
package com.djs.learn.javalang.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMainV8
{
	public void testList(){
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

	public void testMap(){
		Map<String, Integer> map = new HashMap<String, Integer>() {
			{
				put("Jack", 10);
				put("Mary", 8);
				put("Tom", 12);
			}
		};

		map.forEach((name, age) -> System.out.println(name + " is " + age + " old."));

		System.out.println("------------------------------");

		System.out.println("Jerry = " + map.get("Jerry"));
		System.out.println("Jerry = " + map.getOrDefault("Jerry", 6));

		System.out.println("------------------------------");

		System.out.println("Jerry = " + map.computeIfAbsent("Jerry", key -> 6));
		System.out.println("Jerry = " + map.computeIfPresent("Jerry", (key, value) -> 8));
		System.out.println("Jack = " + map.computeIfPresent("Jack", (key, value) -> null));
	}

	public static void main(String[] args){
		TestMainV8 testMain = new TestMainV8();

		testMain.testList();
		System.out.println("------------------------------------------------------------");
		testMain.testMap();
		System.out.println("============================================================");
	}
}
