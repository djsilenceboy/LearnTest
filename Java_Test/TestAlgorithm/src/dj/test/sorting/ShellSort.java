
package dj.test.sorting;

public class ShellSort extends AbstractSorting
{
	public String getMethodName(){
		return "Shell Sort";
	}

	@Override
	public Integer[] sort(Integer[] data, boolean output){
		int i, j, k;
		int temp;

		if (output) {
			System.out.printf("%2c, %2c: ", 'k', 'i');
			printData(data);
		}

		// K is the increment.
		for (k = data.length / 2; k > 0; k /= 2) {
			// Use insertion sort.
			for (i = k; i < data.length; i += k) {
				temp = data[i];

				// From i to at least 1 (not 0).
				for (j = i; (j - k > 0) && (data[j - k] > temp); j -= k) {
					data[j] = data[j - k];

					if (output) {
						System.out.printf("      : %2d->%2d\n", j - k, j);
					}
				}

				if (j != i) {
					data[j] = temp;
				}

				if (output) {
					System.out.printf("%2d, %2d: ", k, i);
					printData(data);
				}
			}
		}

		return data;
	}
}
