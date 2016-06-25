#include <stdio.h>
#include <stdlib.h>
#include "Tools.h"

void bubbleSort(int data[], int size)
{
	int i, j;
	// This flag is for enhanced bubble sort.
	int haveSwap;

	printf("%2c, %2c: ", 'i', 'j');
	printData(data, size);

	// From last element to 1 (not 0).
	for (i = size - 1; i > 0; i--)
	{
		haveSwap = 0;

		// From 1 (not 0) to i.
		for (j = 1; j <= i; j++)
		{
			if (data[j] < data[j - 1])
			{
				// Swap.
				swap(&data[j], &data[j - 1]);

				printf("      : %2d<=>%2d\n", j - 1, j);

				// There is swap in this turn.
				haveSwap = 1;
			}
		}

		printf("%2d, %2d: ", i, j);
		printData(data, size);

		// If no swap in this turn, then it means all sorted, no need to do further sorting.
		if (haveSwap == 0)
		{
			break;
		}
	}
}
