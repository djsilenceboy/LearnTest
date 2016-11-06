
package com.djs.learn.sorting;

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
		int position, child, parent;

		if (output) {
			System.out.printf("%2c, %2c: ", 'i', 'j');
			printData(data);
		}

		// Push the smallest element to top position.
		for (position = 0; position < data.length; position++) {
			// From the bottom position.
			child = data.length - 1;

			// Compare each node with its parent,
			// Swap value if child is smaller than parent.
			while (child > position) {
				// Get parent position.
				parent = position + (int)(((float)child - position - 0.5) / 2.0);

				// Compare parent and child.
				if (data[parent] > data[child]) {
					// Swap.
					data[child] = (Integer)swap(data[parent], data[parent] = data[child]);

					if (output) {
						System.out.printf("      : %2d<=>%2d\n", parent, child);
					}
				}

				if (output) {
					System.out.printf("%2d, %2d: ", position, child);
					printData(data);
				}

				// move up one node.
				child--;
			}
		}

		return data;
	}
}
