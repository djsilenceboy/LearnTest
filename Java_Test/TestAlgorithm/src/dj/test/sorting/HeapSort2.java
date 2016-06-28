
package dj.test.sorting;

public class HeapSort2 extends AbstractSorting
{
	public String getMethodName(){
		return "Heap Sort (Variant)";
	}

	/**
	 * Variant heap sort.
	 */
	@Override
	public Integer[] sort(Integer data[], boolean output){
		int i, j, p;

		if (output) {
			System.out.printf("%2c, %2c: ", 'i', 'j');
			printData(data);
		}

		for (i = 0; i < data.length; i++) {
			// Push the smallest element to i position.
			j = data.length - 1;

			while (j > i) {
				p = i + (int)(((float)j - i - 0.5) / 2.0);

				if (data[p] > data[j]) {
					// Swap.
					data[j] = (Integer)swap(data[p], data[p] = data[j]);

					if (output) {
						System.out.printf("      : %2d<=>%2d\n", p, j);
					}
				}

				if (output) {
					System.out.printf("%2d, %2d: ", i, j);
					printData(data);
				}

				j--;
			}
		}

		return data;
	}
}
