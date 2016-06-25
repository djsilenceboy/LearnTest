#include <stdio.h>
#include <stdlib.h>
#include "Tools.h"

void mergeSortInternal(int data[], int temp[], int left, int size)
{
	if (size > 1)
	{
		int rPos = left + size / 2;
		int lSize = rPos - left;
		int rSize = size - lSize;

		printf("L: %2d / %2d, R: %2d / %2d\n", left, lSize, rPos, rSize);

		printf("%2c, %2c, %2c: ", 'i', 'j', 'k');
		printData(data + left, size);

		// Sort left sub list.
		mergeSortInternal(data, temp, left, lSize);
		// Sort right sub list.
		mergeSortInternal(data, temp, rPos, rSize);

		int i = left;
		int j = rPos;
		int k = left;

		while ((i < rPos) && (j < (left + size)))
		{
			if (data[i] < data[j])
			{
				temp[k++] = data[i++];
			}
			else
			{
				temp[k++] = data[j++];
			}

			printf("%2d, %2d, %2d: ", i, j, k);
			printData(data + left, size);
		}

		while (i < rPos)
		{
			temp[k++] = data[i++];
		}

		while (j < (left + size))
		{
			temp[k++] = data[j++];
		}

		// Copy sorted data from temp array back to original array.
		for (i = left; i < (left + size); i++)
		{
			data[i] = temp[i];
		}

		printf("%2c, %2c, %2c: ", 'i', 'j', 'k');
		printData(data, size);
	}
}

void mergeSort(int data[], int size)
{
	int * temp = malloc(sizeof(int) * size) ;

	mergeSortInternal(data, temp, 0, size);
}
