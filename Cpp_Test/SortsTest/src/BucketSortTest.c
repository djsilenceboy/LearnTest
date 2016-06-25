#include <stdio.h>
#include <stdlib.h>
#include "Tools.h"

void bucketSort(int data[], int size, int maxValue)
{
	int *count = malloc(sizeof(int) * (maxValue + 1));
	int i, j;

	printData(data, size);

	memset(count, 0, sizeof(int) * (maxValue + 1));

	for (i = 0; i < size; i++)
	{
		count[data[i]]++;

		printf("count[data[%2d]=%2d] = %2d\n", i, data[i], count[data[i]]);
	}

	for (i = 0; i <= maxValue; i++)
	{
		if (count[i] > 0)
		{
			for (j = 0; j < count[i]; j++)
			{
				printf("%4d", i);
			}
		}
	}

	printf("\n");
}
