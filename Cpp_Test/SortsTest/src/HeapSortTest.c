#include <stdio.h>
#include <stdlib.h>
#include "Tools.h"

void percolateDown(int data[], int size, int position)
{
	int i = position;
	int child = i * 2 + 1;

	// If there is valid child.
	while (child < size)
	{
		// If right child is smaller.
		if ((child + 1 < size) && (data[child] > data[child + 1]))
		{
			child++;
		}

		// If current is larger than child, swap.
		if (data[i] > data[child])
		{
			printf("   : %2d->%2d\n", i, child);

			swap(&data[i], &data[child]);
		}
		// No need to further down, break.
		else
		{
			break;
		}

		i = child;
		child = i * 2 + 1;
	}
}

void buildHeap(int data[], int size)
{
	int i;

	printf("%2c: ", 'i');
	printData(data, size);

	// From the last non-leaf node.
	for (i = size / 2 - 1; i >= 0; i--)
	{
		percolateDown(data, size, i);

		printf("%2d: ", i);
		printData(data, size);
	}
}

/**
 * Standard heap sort.
 */
void heapSort(int data[], int size)
{
	int i;

	buildHeap(data, size);

	// Following is sorting for MinHeap.

	// From last element to 1 (not 0).
	for (i = size - 1; i > 0; i--)
	{
		// Swap last element with element 0 (the smallest one).
		swap(&data[i], &data[0]);

		percolateDown(data, i, 0);

		printf("%2d: ", i);
		printData(data, size);
	}
}

/**
 * Variant heap sort.
 */
void heapSort2(int data[], int size)
{
	int i, j, p;

	printf("%2c, %2c: ", 'i', 'j');
	printData(data, size);

	for (i = 0; i < size; i++)
	{
		// Push the smallest element to i position.
		j = size - 1;

		while (j > i)
		{
			p = i + (int) (((float) j - i - 0.5) / 2.0);

			if (data[p] < data[j])
			{
				// Swap.
				swap(&data[p], &data[j]);

				printf("      : %2d<=>%2d\n", p, j);
			}

			printf("%2d, %2d: ", i, j);
			printData(data, size);

			j--;
		}
	}
}
