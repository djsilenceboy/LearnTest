#include <stdio.h>
#include <stdlib.h>
#include "Tools.h"

void selectionSort(int data[], int size)
{
	int i, j, k;

	printf("%2c, %2c: ", 'i', 'j');
	printData(data, size);

	for (i = 0; i < size; i++)
	{
		k = i;

		for (j = i + 1; j < size; j++)
		{
			if (data[j] < data[k])
			{
				k = j;
			}
		}

		// If there is any shift.
		if (k != i)
		{
			swap(&data[k], &data[i]);

			printf("      : %2d->%2d\n", k, i);
		}

		printf("%2d, %2d: ", i, j);
		printData(data, size);
	}
}
