
package com.djs.learn.javalang.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestConcurrent2
{
	public void test1(){
		List<Integer> listA = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		List<Integer> listB = new CopyOnWriteArrayList<>(listA);
		Set<Integer> setC = new ConcurrentSkipListSet<>(listA);
		List<Integer> listD = Collections.synchronizedList(listA);

		System.out.println("listA = " + listA);
		System.out.println("listB = " + listB);
		System.out.println("setC = " + setC);
		System.out.println("listD = " + listD);

		System.out.println("----------------------------------------");

		for (Integer i : listB) {
			listB.add(5);
		}

		for (Integer i : setC) {
			setC.add(5);
		}

		// There will be java.util.ConcurrentModificationException.
		/*
		for (Integer i : listD) {
			listD.add(4);
		}
		*/

		System.out.println("listB = " + listB);
		System.out.println("setC = " + setC);
	}

	public static void main(String[] args){
		TestConcurrent2 testMain = new TestConcurrent2();

		System.out.println("============================================================");

		testMain.test1();
		System.out.println("============================================================");
	}
}
