
package com.djs.learn.javalang.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * <pre>
============================================================
java.util.ArrayList = [null, Cat, null, Dog, 7]
java.util.ArrayList = [null, Cat, null, Dog]
java.util.LinkedList = [null, Cat, null, Dog]
java.util.Vector = [null, Cat, null, Dog]
java.util.Stack = [null, Cat, null, Dog]
============================================================
java.util.ArrayList = [1, null]
----------------------------------------
Exception = java.lang.NullPointerException
----------------------------------------
1
Exception = java.lang.NullPointerException
----------------------------------------
Array = [1, 2, 3]
java.util.Arrays$ArrayList = [1, 2, 3]
Array = [1, 4, 3]
java.util.Arrays$ArrayList = [1, 4, 3]
Exception = java.lang.UnsupportedOperationException
Exception = java.lang.UnsupportedOperationException
============================================================
java.util.HashSet = [null, Cat, Dog]
java.util.LinkedHashSet = [null, Cat, Dog]
java.util.TreeSet = [Cat, Dog]
============================================================
java.util.HashMap = {null=null, Vehicle=Car, Animal=Dog}
java.util.LinkedHashMap = {null=null, Animal=Dog}
java.util.TreeMap = {Animal=Dog}
java.util.Hashtable = {Animal=Dog}
============================================================
java.util.ArrayDeque = [Cat1, Dog2]
java.util.ArrayDeque = [Dog2]
java.util.LinkedList = [Cat, Dog]
java.util.LinkedList = [Dog]
============================================================
java.util.ArrayDeque = [Cat1, Dog2]
java.util.ArrayDeque = [Dog2]
java.util.ArrayDeque = [Mice3, Dog2]
java.util.ArrayDeque = [Dog2]
java.util.LinkedList = [Cat, Dog]
java.util.LinkedList = [Dog]
java.util.LinkedList = [Mice, Dog]
============================================================
Item = 0, 0, 10
Item = 1, 1, 9
Item = 2, 2, 8
Item = 3, 3, 7
Item = 4, 4, 6
Item = 5, 5, 5
Item = 6, 6, 4
Item = 7, 7, 3
Item = 8, 8, 2
Item = 9, 9, 1
Top = 9, 9
============================================================
 * </pre>
 */
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
			// Not working.
			// list.add(8);

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

	public void testIntegerList(){
		{
			List<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(null);
			System.out.println(list.getClass().getName() + " = " + list);

			System.out.println("----------------------------------------");

			try {
				int i = list.get(1);
			} catch (Exception e) {
				System.out.println("Exception = " + e);
			}

			System.out.println("----------------------------------------");

			try {
				for (int i : list) {
					System.out.println(i);
				}
			} catch (Exception e) {
				System.out.println("Exception = " + e);
			}
		}

		System.out.println("----------------------------------------");

		{
			// Backed list.
			// It should be an Object array.
			// The new list refers to the same array space.
			// It can change element, but cannot add or delete element.

			Integer[] arr = {1, 2, 3};
			List<Integer> list = Arrays.asList(arr);
			System.out.println("Array = " + Arrays.toString(arr));
			System.out.println(list.getClass().getName() + " = " + list);
			list.set(1, 4);
			System.out.println("Array = " + Arrays.toString(arr));
			System.out.println(list.getClass().getName() + " = " + list);

			try {
				list.remove(1);
			} catch (Exception e) {
				System.out.println("Exception = " + e);
			}

			try {
				list.add(4);
			} catch (Exception e) {
				System.out.println("Exception = " + e);
			}
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

		System.out.println("============================================================");

		testMain.testList();
		System.out.println("============================================================");

		testMain.testIntegerList();
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
