
package com.djs.learn.javalang.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class TestMain
{
	public void testList(){
		// For List:
		// Multiple null are permitted.

		{
			// Raw type. Default is Object type.
			List list = new ArrayList();

			list.add(null);
			list.add("Cat");
			list.add(null);
			list.add("Dog");
			list.add(7);

			System.out.println(list.getClass().getName() + " = " + list);
		}

		{
			List<String> list = new ArrayList<String>();

			list.add(null);
			list.add("Cat");
			list.add(null);
			list.add("Dog");

			System.out.println(list.getClass().getName() + " = " + list);
		}

		{
			List<String> list = new LinkedList<String>();

			list.add(null);
			list.add("Cat");
			list.add(null);
			list.add("Dog");

			System.out.println(list.getClass().getName() + " = " + list);
		}

		{
			// Old.
			// For Vector:
			// It is synchronized.

			List<String> list = new Vector<String>();

			list.add(null);
			list.add("Cat");
			list.add(null);
			list.add("Dog");

			System.out.println(list.getClass().getName() + " = " + list);
		}

		{
			// Old.
			// For Stack:
			// It is extended Vector.

			List<String> list = new Stack<String>();

			list.add(null);
			list.add("Cat");
			list.add(null);
			list.add("Dog");

			System.out.println(list.getClass().getName() + " = " + list);
		}
	}

	public void testSet(){
		{
			// For HashSet:
			// Only one null is permitted.
			// Adding more null will only keep one.

			Set<String> set = new HashSet<String>();

			set.add(null);
			set.add("Cat");
			set.add(null);
			set.add("Dog");

			System.out.println(set.getClass().getName() + " = " + set);
		}

		{
			// For LinkedHashSet:
			// Only one null is permitted.
			// Adding more null will only keep one.

			Set<String> set = new LinkedHashSet<String>();

			set.add(null);
			set.add("Cat");
			set.add(null);
			set.add("Dog");

			System.out.println(set.getClass().getName() + " = " + set);
		}

		{
			// For TreeSet:
			// Cannot add null, it requires to compare value to sort.

			Set<String> set = new TreeSet<String>();

			set.add("Dog");
			set.add("Cat");

			System.out.println(set.getClass().getName() + " = " + set);
		}
	}

	public void testMap(){
		// For Map:
		// Value can be null.

		{
			// For HashMap:
			// Only one null key is permitted.
			// Adding more null key will only keep one.
			// It treats null's hash code as 0.

			Map<String, String> map = new HashMap<String, String>();

			map.put(null, "Cat");
			map.put(null, null);
			map.put(null, null);
			map.put("Vehicle", "Car");
			map.put("Animal", "Dog");

			System.out.println(map.getClass().getName() + " = " + map);
		}

		{
			// For LinkedHashMap:
			// Only one null key is permitted.
			// Adding more null key will only keep one.
			// It treats null's hash code as 0.

			Map<String, String> map = new LinkedHashMap<String, String>();

			map.put(null, "Cat");
			map.put(null, null);
			map.put(null, null);
			map.put("Animal", "Dog");

			System.out.println(map.getClass().getName() + " = " + map);
		}

		{
			// For TreeMap:
			// Cannot add null key, it requires to compare key to sort.

			Map<String, String> map = new TreeMap<String, String>();

			map.put("Animal", "Dog");

			System.out.println(map.getClass().getName() + " = " + map);
		}

		{
			// Old.
			// For Hashtable:
			// Cannot add null key, it requires to check key's hash code.

			Map<String, String> map = new Hashtable<String, String>();

			map.put("Animal", "Dog");

			System.out.println(map.getClass().getName() + " = " + map);
		}
	}

	public void testQueue(){
		{
			Queue<String> queue = new ArrayDeque<String>();

			queue.offer("Cat1");
			queue.offer("Dog2");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.peek();
			queue.poll();

			System.out.println(queue.getClass().getName() + " = " + queue);
		}

		{
			Queue<String> queue = new LinkedList<String>();

			queue.offer("Cat");
			queue.offer("Dog");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.peek();
			queue.poll();

			System.out.println(queue.getClass().getName() + " = " + queue);
		}
	}

	public void testDeque(){
		{
			Deque<String> queue = new ArrayDeque<String>();

			queue.offer("Cat1");
			queue.offer("Dog2");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.peek();
			queue.poll();

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.push("Mice3");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.pop();

			System.out.println(queue.getClass().getName() + " = " + queue);
		}

		{
			Deque<String> queue = new LinkedList<String>();

			queue.offer("Cat");
			queue.offer("Dog");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.peek();
			queue.poll();

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.push("Mice");

			System.out.println(queue.getClass().getName() + " = " + queue);
		}
	}

	public void testStack(){
		Stack<String> stack = new Stack<String>();

		for (int i = 0; i < 10; i++) {
			stack.push(i + "");
		}

		for (String item : stack) {
			System.out.println("Item = " + item + ", " + stack.indexOf(item) + ", " + stack.search(item));
		}

		System.out.println("Top = " + stack.peek() + ", " + stack.indexOf(stack.peek()));
	}

	public static void main(String[] args){
		TestMain testMain = new TestMain();

		testMain.testList();
		System.out.println("============================================================");

		testMain.testSet();
		System.out.println("============================================================");

		testMain.testMap();
		System.out.println("============================================================");

		testMain.testQueue();
		System.out.println("============================================================");

		testMain.testDeque();
		System.out.println("============================================================");

		testMain.testStack();
		System.out.println("============================================================");
	}
}
