
package com.djs.learn.sorting;

public class SelectionSort extends AbstractSorting
{
	public String getMethodName(){
		return "Selection Sort";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		int i, j, k;

		if (output) {
			System.out.printf("%2c, %2c: ", 'i', 'j');
			printData(data);
		}

		for (i = 0; i < data.length; i++) {
			k = i;

			for (j = i + 1; j < data.length; j++) {
				if (data[j] < data[k]) {
					k = j;
				}
			}

			// If there is any shift.
			if (k != i) {
				data[i] = (Integer)swap(data[k], data[k] = data[i]);

				if (output) {
					System.out.printf("      : %2d->%2d\n", k, i);
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
