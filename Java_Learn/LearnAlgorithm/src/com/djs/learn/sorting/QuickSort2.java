
package com.djs.learn.sorting;

public class QuickSort2 extends AbstractSorting
{
	private static final int CUTOFF = 3;

	public String getMethodName(){
		return "Quick Sort (Variant)";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		return quickSortInternal(data, 0, data.length, output);
	}

	/**
	 * Variant quick sort.
	 * Internal sorts part of the data array.
	 */
	private Integer[] quickSortInternal(Integer data[], int leftPosition, int size, boolean output){
		if (output) {
			System.out.printf("left, size: %2d, %2d\n", leftPosition, size);
			System.out.printf("%2c, %2c: ", 'i', 'j');
			printData(data, leftPosition, size);
		}

		// If small array, better use other sorting method.
		if (size <= CUTOFF) {
			InsertionSort insertionSort = new InsertionSort();
			return insertionSort.sort(data, leftPosition, size, output);
		} else {
			int centerPosition = leftPosition + size / 2;
			int rightPosition = leftPosition + size - 1;
			int pivot = data[centerPosition];
			int i = leftPosition;
			int j = rightPosition;

			if (output) {
				System.out.printf("[%2d] = %2d\n", centerPosition, data[centerPosition]);
			}

			while (true) {
				// From i up, find the first element >= pivot.
				while (data[i] < pivot) {
					i++;

					if (output) {
						System.out.printf("%2d->%2d\n", i, j);
					}
				}

				// From j down, find the first element <= pivot.
				while ((data[j] > pivot)) {
					j--;

					if (output) {
						System.out.printf("%2d<-%2d\n", i, j);
					}
				}

				// If i does not meet j, exchange.
				if (i <= j) {
					data[j] = (Integer)swap(data[i], data[i] = data[j]);

					if (output) {
						System.out.printf("%2d<>%2d: ", i, j);
						printData(data, leftPosition, size);
					}

					i++;
					j--;

					if (output) {
						System.out.printf("%2d><%2d\n", i, j);
					}
				} else {
					break;
				}
			}

			if (output) {
				System.out.printf("%2d--%2d: ", i, j);
				printData(data, leftPosition, size);
			}

			// Now, recursively sort the first half of data array (before pivot pos).
			data = quickSortInternal(data, leftPosition, j - leftPosition + 1, output);
			// Now, recursively sort the second half of data array (after pivot pos).
			data = quickSortInternal(data, i, rightPosition - i + 1, output);

			if (output) {
				System.out.printf("%2d==%2d: ", i, j);
				printData(data, leftPosition, size);
			}

			return data;
		}
	}
}
