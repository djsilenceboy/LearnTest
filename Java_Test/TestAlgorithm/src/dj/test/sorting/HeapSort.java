
package dj.test.sorting;

public class HeapSort extends AbstractSorting
{
	public String getMethodName(){
		return "Heap Sort";
	}

	/**
	 * Standard heap sort.
	 */
	@Override
	public Integer[] sort(Integer[] data, boolean output){
		int i;

		buildHeap(data, output);

		if (output) {
			System.out.printf("%2c: ", 'i');
			printData(data);
		}

		// Following is sorting for MinHeap.

		// From last element to 1 (not 0).
		for (i = data.length - 1; i > 0; i--) {
			// Swap last element with element 0 (the smallest one).
			data[0] = (Integer)swap(data[i], data[i] = data[0]);

			percolateDown(data, i, 0, output);

			if (output) {
				System.out.printf("%2d: ", i);
				printData(data);
			}
		}

		return data;
	}

	public void buildHeap(Integer[] data, boolean output){
		int i;

		if (output) {
			System.out.println("*** Build healp *** ");
			System.out.printf("%2c: ", 'i');
			printData(data);
		}

		// From the last non-leaf node.
		for (i = data.length / 2 - 1; i >= 0; i--) {
			percolateDown(data, data.length, i, output);

			if (output) {
				System.out.printf("%2d: ", i);
				printData(data);
			}
		}

		if (output) {
			System.out.println("*** Build healp *** ");
		}
	}

	public void percolateDown(Integer[] data, int size, int position, boolean output){
		int i = position;
		int child = i * 2 + 1;

		// If there is valid child.
		while (child < size) {
			// If right child is smaller.
			if ((child + 1 < size) && (data[child] < data[child + 1])) {
				child++;
			}

			// If current is larger than child, swap.
			if (data[i] < data[child]) {
				if (output) {
					System.out.printf("   : %2d->%2d\n", i, child);
				}

				data[child] = (Integer)swap(data[i], data[i] = data[child]);
			}
			// No need to further down, break.
			else {
				break;
			}

			i = child;
			child = i * 2 + 1;
		}
	}
}
