
package com.djs.learn.sorting;

public class InsertionSort extends AbstractSorting
{
	public String getMethodName(){
		return "Insertion Sort";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		int i, j;
		int temp;

		if (output) {
			System.out.printf("%2c, %2c: ", 'i', 'j');
			printData(data);
		}

		// From 1 (not 0) to last element.
		// Insert each new element into already sorted sub-list.
		for (i = 1; i < data.length; i++) {
			// Temporarily keep the top/new element in sub-list, which will be inserted into proper position.
			temp = data[i];

			// From i to 1 (not 0).
			// Move all elements larger than temp one up.
			for (j = i; (j > 0) && (data[j - 1] > temp); j--) {
				data[j] = data[j - 1];

				if (output) {
					System.out.printf("      : %2d->%2d\n", j - 1, j);
				}
			}

			// If there is any movement,
			// put temp one to new position.
			if (j != i) {
				data[j] = temp;
			}

			// The sub-list is always sorted already.

			if (output) {
				System.out.printf("%2d, %2d: ", i, j);
				printData(data);
			}
		}

		return data;
	}

	/**
	 * This version is used by Quick Sort, to sort sub list.
	 */
	public Integer[] sort(Integer[] data, int leftPosition, int size, boolean output){
		int i, j;
		int temp;

		if (output) {
			System.out.printf("%2c, %2c: ", 'i', 'j');
			printData(data, leftPosition, size);
		}

		// From 1 (not 0) to last element.
		for (i = leftPosition; i < leftPosition + size; i++) {
			temp = data[i];

			// From i to 1 (not 0).
			for (j = i; (j > leftPosition) && (data[j - 1] > temp); j--) {
				data[j] = data[j - 1];

				if (output) {
					System.out.printf("      : %2d->%2d\n", j - 1, j);
				}
			}

			// If there is any shift.
			if (j != i) {
				data[j] = temp;
			}

			if (output) {
				System.out.printf("%2d, %2d: ", i, j);
				printData(data, leftPosition, size);
			}
		}

		return data;
	}
}
