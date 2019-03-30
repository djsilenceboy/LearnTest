
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

		// 1. In first sub-list, find the smallest element, and put in the first position.
		// 2. Decrease the sub-list.
		// 3. In second sub-list, find the smallest element, and put in the second position.
		// 4. Repeat 2 and 3.

		for (i = 0; i < data.length; i++) {
			// Keep current position, suppose the element is the smallest in the following sub-list.
			smallestPosition = i;

			// Find real smallest element in the sub-list,
			// and keep that position.
			for (j = i + 1; j < data.length; j++) {
				if (data[j] < data[smallestPosition]) {
					smallestPosition = j;
				}
			}

			// If there is another real smallest element,
			// swap them.
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
