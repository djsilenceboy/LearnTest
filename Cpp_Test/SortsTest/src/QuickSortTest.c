#include <stdio.h>
#include <stdlib.h>
#include "Tools.h"
#include "SortsTest.h"

#define CUTOFF 3

/**
 * Use median of three to select pivot.
 * The size is better >= 3.
 * While selection, the data will be exchanged.
 * After selection, data[left] <= data[center] <= data[size - 1].
 */
int selectPivot(int data[], int left, int size)
{
	int center = left + size / 2;
	int right = left + size - 1;

	// Compare left, center, right like bubble sort.

	if (data[left] > data[center])
	{
		swap(&data[left], &data[center]);
	}

	if (data[center] > data[right])
	{
		swap(&data[center], &data[right]);
	}

	if (data[left] > data[center])
	{
		swap(&data[left], &data[center]);
	}

	// Now, data[left] <= data[center] <= data[right].

	printf("[%2d], [%2d], [%2d] = %2d, %2d, %2d\n", left, center, right, data[left], data[center], data[right]);

	return center;
}

/**
 * Standard quick sort.
 * Internal sorts part of the data array.
 */
void quickSortInternal(int data[], int left, int size)
{
	printf("left, size: %2d, %2d\n", left, size);
	printf("%2c, %2c: ", 'i', 'j');
	printData(data + left, size);

	// If small array, better use other sorting method.
	if (size <= CUTOFF)
	{
		insertionSort(&data[left], size);
	}
	else
	{
		int right = left + size - 1;
		// Get pivot pos.
		int pivotPos = selectPivot(data, left, size);
		// After selection, data[left] <= data[center/pivotPos] <= data[size - 1].
		int pivot = data[pivotPos];

		// Exchange pivot with the (right - 1) element.
		if (pivotPos != right - 1)
		{
			data[pivotPos] = data[right - 1];
			data[right - 1] = pivot;
		}

		// Left element of this section.
		// But sorting will start from (left + 1), because in selectPivot(), data[left] <= data[center].
		int i = left;
		// (right - 1) element of this section.
		// Because in selectPivot(), data[center] <= data[right].
		// But sorting will start from (right - 2), because pivot has been exchanged to data[right - 1].
		int j = right - 1;

		printf("%2d, %2d: ", i, j);
		printData(data + left, size);

		while (1)
		{
			// From i up, find the first element >= pivot.
			while (data[++i] < pivot)
			{
				printf("%2d->%2d\n", i, j);
			}

			// From j down, find the first element <= pivot.
			while ((data[--j] > pivot))
			{
				printf("%2d<-%2d\n", i, j);
			}

			// If i does not meet j, exchange.
			if (i < j)
			{
				swap(&data[i], &data[j]);

				printf("%2d<>%2d: ", i, j);
				printData(data + left, size);

				// Note that:
				// Do not i++ and j-- here. Because this method will do it in the next turn of while loop.
				// Thus avoiding the special case: after i++ and j-- here, i = j, but data[i] < pivot.
				// Because in that case:
				// 1. While loop will break here.
				// 2. Then it will exchange pivot back from the last element to i's pos.
				// 3. But data[i] < pivot.
				// 4. So, the data[i], smaller than pivot is exchanged to the position where data should be larger than pivot.
			}
			else
			{
				break;
			}
		}

		// Exchange pivot back from (right - 1) element to i's pos.
		// Remember that: i pos means element >= pivot in this round of sorting.
		data[right - 1] = data[i];
		data[i] = pivot;

		// Now i pos is where the pivot is.

		printf("%2d--%2d: ", i, j);
		printData(data + left, size);

		// Now, recursively sort the first half of data array (before pivot pos).
		quickSortInternal(data, left, i - left);
		// Now, recursively sort the second half of data array (after pivot pos).
		quickSortInternal(data, i + 1, right - i);

		printf("%2d==%2d: ", i, j);
		printData(data + left, size);
	}
}

void quickSort(int data[], int size)
{
	quickSortInternal(data, 0, size);
}

/**
 * Variant quick sort.
 * Internal sorts part of the data array.
 */
void quickSort2Internal(int data[], int left, int size)
{
	printf("left, size: %2d, %2d\n", left, size);
	printf("%2c, %2c: ", 'i', 'j');
	printData(data + left, size);

	// If small array, better use other sorting method.
	if (size <= CUTOFF)
	{
		insertionSort(&data[left], size);
	}
	else
	{
		int center = left + size / 2;
		int right = left + size - 1;
		int pivot = data[center];
		int i = left;
		int j = right;

		printf("[%2d] = %2d\n", center, data[center]);

		while (1)
		{
			// From i up, find the first element >= pivot.
			while (data[i] < pivot)
			{
				i++;

				printf("%2d->%2d\n", i, j);
			}

			// From j down, find the first element <= pivot.
			while ((data[j] > pivot))
			{
				j--;

				printf("%2d<-%2d\n", i, j);
			}

			// If i does not meet j, exchange.
			if (i <= j)
			{
				swap(&data[i], &data[j]);

				printf("%2d<>%2d: ", i, j);
				printData(data + left, size);

				i++;
				j--;

				printf("%2d><%2d\n", i, j);
			}
			else
			{
				break;
			}
		}

		printf("%2d--%2d: ", i, j);
		printData(data + left, size);

		// Now, recursively sort the first half of data array (before pivot pos).
		quickSort2Internal(data, left, j - left + 1);
		// Now, recursively sort the second half of data array (after pivot pos).
		quickSort2Internal(data, i, right - i + 1);

		printf("%2d==%2d: ", i, j);
		printData(data + left, size);
	}
}

void quickSort2(int data[], int size)
{
	quickSort2Internal(data, 0, size);
}
