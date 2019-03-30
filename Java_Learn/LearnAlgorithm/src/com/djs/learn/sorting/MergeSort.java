
package com.djs.learn.sorting;

public class MergeSort extends AbstractSorting
{
	public String getMethodName(){
		return "Merge Sort";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		Integer[] temp = new Integer[data.length];

		for (int i = 0; i < temp.length; i++) {
			temp[i] = 0;
		}

		return mergeSortInternal(data, temp, 0, data.length, output);
	}

	public Integer[] mergeSortInternal(Integer[] data, Integer[] temp, int leftPosition, int size, boolean output){
		if (size > 1) {
			int rightPosition = leftPosition + size / 2;
			int leftSize = rightPosition - leftPosition;
			int rightSize = size - leftSize;

			if (output) {
				System.out.printf("L: %2d / %2d, R: %2d / %2d\n", leftPosition, leftSize, rightPosition, rightSize);

				System.out.printf("%2c, %2c, %2c: ", 'i', 'j', 'k');
				printData(data, leftPosition, size);
			}

			// Sort left sub list.
			data = mergeSortInternal(data, temp, leftPosition, leftSize, output);
			// Sort right sub list.
			data = mergeSortInternal(data, temp, rightPosition, rightSize, output);

			int i = leftPosition;
			int j = rightPosition;
			int k = leftPosition;

			while ((i < rightPosition) && (j < (leftPosition + size))) {
				if (data[i] < data[j]) {
					temp[k++] = data[i++];
				} else {
					temp[k++] = data[j++];
				}

				if (output) {
					System.out.printf("%2d, %2d, %2d: ", i, j, k);
					printData(data, leftPosition, size);
				}
			}

			while (i < rightPosition) {
				temp[k++] = data[i++];
			}

			while (j < (leftPosition + size)) {
				temp[k++] = data[j++];
			}

			// Copy sorted data from temp array back to original array.
			for (i = leftPosition; i < (leftPosition + size); i++) {
				data[i] = temp[i];
			}

			if (output) {
				System.out.printf("%2c, %2c, %2c: ", 'i', 'j', 'k');
				printData(data);
			}
		}

		return data;
	}
}
