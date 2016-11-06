
package com.djs.learn.sorting;

public class ShellSort2 extends AbstractSorting
{
	public String getMethodName(){
		return "Shell Sort (Selection)";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		int i, j, increment;
		int smallestPosition;

		if (output) {
			System.out.printf("%2c, %2c: ", 'k', 'i');
			printData(data);
		}

		// 1. Set the first step (biggest).
		// 2. Sort sub-list with the step.
		// 3. Half the step.
		// 4. Sort sub-list with the step.
		// 5. Repeat 3 and 4.

		for (increment = data.length / 2; increment > 0; increment /= 2) {
			// Selection Sort is used for internal sorting.
			for (i = increment; i < data.length; i += increment) {
				smallestPosition = i;

				for (j = i + increment; j < data.length; j += increment) {
					if (data[j] < data[smallestPosition]) {
						smallestPosition = j;
					}
				}

				if (smallestPosition != i) {
					data[i] = (Integer)swap(data[smallestPosition], data[smallestPosition] = data[i]);

					if (output) {
						System.out.printf("      : %2d->%2d\n", smallestPosition, i);
					}
				}

				if (output) {
					System.out.printf("%2d, %2d: ", increment, i);
					printData(data);
				}
			}
		}

		return data;
	}
}
