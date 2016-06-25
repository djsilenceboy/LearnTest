#include <stdio.h>
#include <stdlib.h>
#include "Tools.h"

void insertionSort(int data[], int size)
{
	int i, j;
	int temp;

	printf("%2c, %2c: ", 'i', 'j');
	printData(data, size);

	// From 1 (not 0) to last element.
	for (i = 1; i < size; i++)
	{
		temp = data[i];

		// From i to 1 (not 0).
		for (j = i; (j > 0) && (data[j - 1] > temp); j--)
		{
			data[j] = data[j - 1];

			printf("      : %2d->%2d\n", j - 1, j);
		}

		// If there is any shift.
		if (j != i)
		{
			data[j] = temp;
		}

		printf("%2d, %2d: ", i, j);
		printData(data, size);
	}
}
