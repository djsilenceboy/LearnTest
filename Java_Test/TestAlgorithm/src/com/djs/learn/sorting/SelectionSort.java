
package com.djs.learn.sorting;

public class SelectionSort extends AbstractSorting
{
	public String getMethodName(){
		return "Selection Sort";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		int i, j, smallestPosition;

		if (output) {
			System.out.printf("%2c, %2c: ", 'i', 'j');
			printData(data);
		}

		for (i = 0; i < data.length; i++) {
			// Keep current position, suppose the element is the smallest.
			smallestPosition = i;

			for (j = i + 1; j < data.length; j++) {
				if (data[j] < data[smallestPosition]) {
					smallestPosition = j;
				}
			}

			// If there is any shift.
			if (smallestPosition != i) {
				data[i] = (Integer)swap(data[smallestPosition], data[smallestPosition] = data[i]);

				if (output) {
					System.out.printf("      : %2d->%2d\n", smallestPosition, i);
				}
			}

			if (output) {
				System.out.printf("%2d, %2d: ", i, j);
				printData(data);
			}
		}

		return data;
	}
}
