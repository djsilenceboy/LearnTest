
package com.djs.learn.sorting;

public class ShellSort extends AbstractSorting
{
	public String getMethodName(){
		return "Shell Sort (Insertion)";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		int i, j, increment;
		int temp;

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
			// Insertion Sort is used for internal sorting.
			for (i = increment; i < data.length; i += increment) {
				temp = data[i];

				for (j = i; (j - increment > 0) && (data[j - increment] > temp); j -= increment) {
					data[j] = data[j - increment];

					if (output) {
						System.out.printf("      : %2d->%2d\n", j - increment, j);
					}
				}

				if (j != i) {
					data[j] = temp;
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
