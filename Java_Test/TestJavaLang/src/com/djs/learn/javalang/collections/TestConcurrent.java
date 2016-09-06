
package com.djs.learn.javalang.collections;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class TestConcurrent
{
	public void testList(){
		// For List:
		// Multiple null are permitted.

		{
			// Raw type. Default is Object type.
			List list = new CopyOnWriteArrayList();

			list.add(null);
			list.add("Cat");
			list.add(null);
			list.add("Dog");
			list.add(7);

			System.out.println(list.getClass().getName() + " = " + list);
		}

		{
			List<String> list = new CopyOnWriteArrayList<String>();

			list.add(null);
			list.add("Cat");
			list.add(null);
			list.add("Dog");

			System.out.println(list.getClass().getName() + " = " + list);
		}
	}

	public void testSet(){
		{
			Set<String> set = new CopyOnWriteArraySet<String>();

			set.add(null);
			set.add("Cat");
			set.add(null);
			set.add("Dog");

			System.out.println(set.getClass().getName() + " = " + set);
		}

		{
			// For SortedSet:
			// Cannot add null, it requires to compare value to sort.

			Set<String> set = new ConcurrentSkipListSet<String>();

			set.add("Cat");
			set.add("Dog");

			System.out.println(set.getClass().getName() + " = " + set);
		}
	}

	public void testMap(){
		// For Map:
		// Value can be null.

		{
			// Cannot add null key.

			Map<String, String> map = new ConcurrentHashMap<String, String>();

			map.put("Vehicle", "Car");
			map.put("Animal", "Dog");

			System.out.println(map.getClass().getName() + " = " + map);
		}

		{
			// For SortedMap:
			// Cannot add null key and null value.

			Map<String, String> map = new ConcurrentSkipListMap<String, String>();

			map.put("Animal", "Dog");

			System.out.println(map.getClass().getName() + " = " + map);
		}
	}

	public void testQueue(){
		{
			Queue<String> queue = new ConcurrentLinkedQueue<String>();

			queue.offer("Cat1");
			queue.offer("Dog2");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.peek();
			queue.poll();

			System.out.println(queue.getClass().getName() + " = " + queue);
		}

		{
			Queue<String> queue = new ConcurrentLinkedDeque<String>();

			queue.offer("Cat");
			queue.offer("Dog");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.peek();
			queue.poll();

			System.out.println(queue.getClass().getName() + " = " + queue);
		}

		{
			Queue<String> queue = new LinkedBlockingQueue<String>();

			queue.offer("Cat2");
			queue.offer("Dog2");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.peek();
			queue.poll();

			System.out.println(queue.getClass().getName() + " = " + queue);
		}
	}

	public void testDeque(){
		{
			Deque<String> queue = new ConcurrentLinkedDeque<String>();

			queue.offer("Cat");
			queue.offer("Dog");

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.peek();
			queue.poll();

			System.out.println(queue.getClass().getName() + " = " + queue);

			queue.push("Mice");

			System.out.println(queue.getClass().getName() + " = " + queue);
		}

		{
			Deque<String> queue = new LinkedBlockingDeque<String>();

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
		TestConcurrent testMain = new TestConcurrent();

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
