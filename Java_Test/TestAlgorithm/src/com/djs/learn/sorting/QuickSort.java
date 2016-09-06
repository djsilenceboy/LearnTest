
package com.djs.learn.sorting;

public class QuickSort extends AbstractSorting
{
	private static final int CUTOFF = 3;

	public String getMethodName(){
		return "Quick Sort";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		return quickSortInternal(data, 0, data.length, output);
	}

	/**
	 * Use median of three to select pivot.
	 * The size is better >= 3.
	 * While selection, the data will be exchanged.
	 * After selection, data[left] <= data[center] <= data[size - 1].
	 */
	private int selectPivot(Integer[] data, int left, int size, boolean output){
		int center = left + size / 2;
		int right = left + size - 1;

		// Compare left, center, right like bubble sort.

		if (data[left] > data[center]) {
			data[center] = (Integer)swap(data[left], data[left] = data[center]);
		}

		if (data[center] > data[right]) {
			data[right] = (Integer)swap(data[center], data[center] = data[right]);
		}

		if (data[left] > data[center]) {
			data[center] = (Integer)swap(data[left], data[left] = data[center]);
		}

		// Now, data[left] <= data[center] <= data[right].

		if (output) {
			System.out.printf("[%2d], [%2d], [%2d] = %2d, %2d, %2d\n", left, center, right, data[left], data[center], data[right]);
		}

		return center;
	}

	/**
	 * Standard quick sort.
	 * Internal sorts part of the data array.
	 */
	private Integer[] quickSortInternal(Integer[] data, int leftPosition, int size, boolean output){
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
			int rightPosition = leftPosition + size - 1;
			// Get pivot pos.
			int pivotPosition = selectPivot(data, leftPosition, size, output);
			// After selection, data[left] <= data[center/pivotPos] <= data[size - 1].
			int pivot = data[pivotPosition];

			// Exchange pivot with the (right - 1) element.
			if (pivotPosition != rightPosition - 1) {
				data[pivotPosition] = data[rightPosition - 1];
				data[rightPosition - 1] = pivot;
			}

			// Left element of this section.
			// But sorting will start from (left + 1), because in selectPivot(), data[left] <= data[center].
			int i = leftPosition;
			// (right - 1) element of this section.
			// Because in selectPivot(), data[center] <= data[right].
			// But sorting will start from (right - 2), because pivot has been exchanged to data[right - 1].
			int j = rightPosition - 1;

			if (output) {
				System.out.printf("%2d, %2d: ", i, j);
				printData(data, leftPosition, size);
			}

			while (true) {
				// From i up, find the first element >= pivot.
				while (data[++i] < pivot) {
					if (output) {
						System.out.printf("%2d->%2d\n", i, j);
					}
				}

				// From j down, find the first element <= pivot.
				while ((data[--j] > pivot)) {
					if (output) {
						System.out.printf("%2d<-%2d\n", i, j);
					}
				}

				// If i does not meet j, exchange.
				if (i < j) {
					data[j] = (Integer)swap(data[i], data[i] = data[j]);

					if (output) {
						System.out.printf("%2d<>%2d: ", i, j);
						printData(data, leftPosition, size);
					}

					// Note that:
					// Do not i++ and j-- here. Because this method will do it in the next turn of while loop.
					// Thus avoiding the special case: after i++ and j-- here, i = j, but data[i] < pivot.
					// Because in that case:
					// 1. While loop will break here.
					// 2. Then it will exchange pivot back from the last element to i's pos.
					// 3. But data[i] < pivot.
					// 4. So, the data[i], smaller than pivot is exchanged to the position where data should be larger than pivot.
				} else {
					break;
				}
			}

			// Exchange pivot back from (right - 1) element to i's pos.
			// Remember that: i pos means element >= pivot in this round of sorting.
			data[rightPosition - 1] = data[i];
			data[i] = pivot;

			// Now i pos is where the pivot is.

			if (output) {
				System.out.printf("%2d--%2d: ", i, j);
				printData(data, leftPosition, size);
			}

			// Now, recursively sort the first half of data array (before pivot pos).
			data = quickSortInternal(data, leftPosition, i - leftPosition, output);
			// Now, recursively sort the second half of data array (after pivot pos).
			data = quickSortInternal(data, i + 1, rightPosition - i, output);

			if (output) {
				System.out.printf("%2d==%2d: ", i, j);
				printData(data, leftPosition, size);
			}

			return data;
		}
	}
}
