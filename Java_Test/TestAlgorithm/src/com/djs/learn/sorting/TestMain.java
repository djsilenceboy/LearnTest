
package com.djs.learn.sorting;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestMain
{
	public static void main(String[] args){
		Integer[][] sampleData = {{11, 99, 33, 55, 77, 66, 22, 33, 88, 44, 11}, {11, 4, 99, 33, 8, 55, 9, 33, 77, 2, 3, 66, 11, 5, 22, 6, 1, 88, 7, 4, 44},
		                          {88, 88, 88, 88, 88, 88, 88, 88, 88}};

		SortingInterface[] sortingMethods = new SortingInterface[]{new BubbleSort(), new BucketSort(), new HeapSort(), new HeapSort2(), new InsertionSort(),
		                                                           new MergeSort(), new QuickSort(), new QuickSort2(), new SelectionSort(), new ShellSort(),
		                                                           new ShellSort2()};
		boolean output = false;

		// SortingInterface[] sortingMethods = new SortingInterface[]{new QuickSort(), new QuickSort2()};
		// boolean output = true;

		for (Integer[] data : sampleData) {
			Map<Double, String> result = new TreeMap<Double, String>();

			System.out.println("------------------------------------------------------------");

			for (SortingInterface sortingMethod : sortingMethods) {
				long startTime, stopTime;
				double durationTime;

				System.out.println("----------------------------------------");
				System.out.println("===== " + sortingMethod.getMethodName() + " =====");
				startTime = System.nanoTime();

				Integer[] sortedData = sortingMethod.sort(data.clone(), output);

				stopTime = System.nanoTime();
				durationTime = (stopTime - startTime + 1) / 1000.0 / 1000.0;

				System.out.println(Arrays.toString(sortedData));
				System.out.println("Time: " + durationTime + " ms");

				result.put(durationTime, sortingMethod.getMethodName());
			}

			System.out.println("----------------------------------------");
			Set<Double> keys = result.keySet();

			for (Double key : keys) {
				System.out.printf("%25s: %10.6f\n", result.get(key), key);
			}
		}
	}
}
