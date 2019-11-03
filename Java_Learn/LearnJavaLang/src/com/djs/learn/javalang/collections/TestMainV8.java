
package com.djs.learn.javalang.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
		System.out.println("Map = " + map);
		System.out.println("Jerry = " + map.getOrDefault("Jerry", 6));
		System.out.println("Map = " + map);

		System.out.println("------------------------------");

		{
			Map<String, Integer> map2 = (Map)((HashMap)map).clone();
			map2.computeIfAbsent("Jerry", key -> 6);
			System.out.println("Map2 = " + map2);
			map2.computeIfPresent("Jerry", (key, value) -> 8);
			System.out.println("Map2 = " + map2);
			map2.computeIfPresent("Jerry", (key, value) -> null);
			System.out.println("Map2 = " + map2);
		}

		System.out.println("------------------------------");

		{
			Map<String, Integer> map3 = (Map)((HashMap)map).clone();
			map3.remove("Jack", 8);
			System.out.println("Map3 = " + map3);
			map3.remove("Jack", 10);
			System.out.println("Map3 = " + map3);
		}

		System.out.println("------------------------------");

		{
			Map<String, Integer> map4 = (Map)((HashMap)map).clone();
			map4.replace("Jack", 20);
			System.out.println("Map4 = " + map4);
			map4.replaceAll((name, age) -> age + 1);
			System.out.println("Map4 = " + map4);
		}

		System.out.println("------------------------------");

		Map<String, Integer> mapA = new HashMap<String, Integer>() {
			{
				put("Jack", 10);
				put("Mary", 10);
			}
		};
		Map<String, Integer> mapB = new HashMap<String, Integer>() {
			{
				put("Jack", 5);
				put("Tom", 5);
			}
		};
		System.out.println("MapA = " + mapA);
		System.out.println("MapB = " + mapB);
		mapB.forEach((k, v) -> mapA.merge(k, v, (v1, v2) -> v1 + v2));
		System.out.println("MapA = " + mapA);
		System.out.println("MapB = " + mapB);
	}

	public void testConcurrentMap(){
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		Stream.iterate(0, v -> v + 1).limit(1_000_000).forEach(v -> map.put(Integer.toString(v), v));

		{
			long startTime = System.currentTimeMillis();
			System.out.println("Found  298 = " + map.search(100, (k, v) -> {
				if (v == 298) return k;
				else return null;
			}));
			long stopTime = System.currentTimeMillis();
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}
		{
			long startTime = System.currentTimeMillis();
			System.out.println("Found 900_298 = " + map.search(100, (k, v) -> {
				if (v == 900_298) return k;
				else return null;
			}));
			long stopTime = System.currentTimeMillis();
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}
		{
			long startTime = System.currentTimeMillis();
			System.out.println("Found 1_000_298 = " + map.search(100, (k, v) -> {
				if (v == 1_000_298) return k;
				else return null;
			}));
			long stopTime = System.currentTimeMillis();
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}

		System.out.println("------------------------------");

		{

			long startTime = System.currentTimeMillis();
			IntStream.rangeClosed(1, 10).forEach(i -> map.forEach(10000, (k, v) -> {
				v = v + i;
			}));
			long stopTime = System.currentTimeMillis();
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}
		{
			HashMap<String, Integer> mapB = new HashMap<>();
			mapB.putAll(map);

			long startTime = System.currentTimeMillis();
			IntStream.rangeClosed(1, 10).forEach(i -> mapB.forEach((k, v) -> {
				v = v + i;
			}));
			long stopTime = System.currentTimeMillis();
			System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		}
	}

	public static void main(String[] args){
		TestMainV8 testMain = new TestMainV8();

		testMain.testList();
		System.out.println("------------------------------------------------------------");
		testMain.testMap();
		System.out.println("------------------------------------------------------------");
		testMain.testConcurrentMap();
		System.out.println("============================================================");
	}
}
