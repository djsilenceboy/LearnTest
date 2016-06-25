#include <stdio.h>
#include <stdlib.h>
#include "Tools.h"

void shellSort(int data[], int size)
{
	int i, j, k;
	int temp;

	printf("%2c, %2c: ", 'k', 'i');
	printData(data, size);

	// K is the increment.
	for (k = size / 2; k > 0; k /= 2)
	{
		// Use insertion sort.
		for (i = k; i < size; i += k)
		{
			temp = data[i];

			// From i to at least 1 (not 0).
			for (j = i; (j - k > 0) && (data[j - k] > temp); j -= k)
			{
				data[j] = data[j - k];

				printf("      : %2d->%2d\n", j - k, j);
			}

			if (j != i)
			{
				data[j] = temp;
			}

			printf("%2d, %2d: ", k, i);
			printData(data, size);
		}
	}
}
