/*
 ============================================================================
 Name        : HeapPriorityQueueTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct
{
	int maxSize;
	int size;
	int* pdata;
} PRIORITY_QUEUE;

void printData(PRIORITY_QUEUE *priorityQueue)
{
	int i;

	printf("Max/Size = %2d / %2d: ", priorityQueue->maxSize, priorityQueue->size);

	for (i = 0; i < priorityQueue->maxSize; i++)
	{
		printf("%4d", priorityQueue->pdata[i]);
	}
	printf("\n");
}

PRIORITY_QUEUE* initHeap(int maxSize)
{
	PRIORITY_QUEUE *priorityQueue = malloc(sizeof(PRIORITY_QUEUE));
	priorityQueue->maxSize = maxSize;
	priorityQueue->size = 0;
	priorityQueue->pdata = malloc(sizeof(int) * maxSize);

	memset(priorityQueue->pdata, 0, sizeof(int) * maxSize);

	return priorityQueue;
}

void percolateUp(PRIORITY_QUEUE *priorityQueue, int position)
{
	int i;
	int temp = priorityQueue->pdata[position];

	for (i = position; (i > 0) && (priorityQueue->pdata[(i - 1) / 2] > temp); i = (i - 1) / 2)
	{
		priorityQueue->pdata[i] = priorityQueue->pdata[(i - 1) / 2];
	}

	// If any parents is larger than current position.
	if (i != position)
	{
		priorityQueue->pdata[i] = temp;
	}
}

void percolateUpEmpty(PRIORITY_QUEUE *priorityQueue, int position)
{
	int i = position;
	int child = i * 2 + 1;

	// If there is valid child.
	while (child < priorityQueue->size)
	{
		// If right child is smaller.
		if ((child + 1 < priorityQueue->size) && (priorityQueue->pdata[child] > priorityQueue->pdata[child + 1]))
		{
			child++;
		}

		priorityQueue->pdata[i] = priorityQueue->pdata[child];
		i = child;
		child = i * 2 + 1;
	}

	priorityQueue->pdata[priorityQueue->size - 1] = 0;
	priorityQueue->size--;
}

void percolateDown(PRIORITY_QUEUE *priorityQueue, int position)
{
	int i = position;
	int child = i * 2 + 1;

	// If there is valid child.
	while (child < priorityQueue->size)
	{
		// If right child is smaller.
		if ((child + 1 < priorityQueue->size) && (priorityQueue->pdata[child] > priorityQueue->pdata[child + 1]))
		{
			child++;
		}

		printf("i[%2d] = %2d, child[%2d] = %2d\n", i, priorityQueue->pdata[i], child, priorityQueue->pdata[child]);

		// If current is larger than child, swap.
		if (priorityQueue->pdata[i] > priorityQueue->pdata[child])
		{
			int temp = priorityQueue->pdata[i];
			priorityQueue->pdata[i] = priorityQueue->pdata[child];
			priorityQueue->pdata[child] = temp;
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

void insert(PRIORITY_QUEUE *priorityQueue, int data)
{
	priorityQueue->pdata[priorityQueue->size] = data;
	priorityQueue->size++;

	percolateUp(priorityQueue, priorityQueue->size - 1);
}

void insert2(PRIORITY_QUEUE *priorityQueue, int data)
{
	int i = priorityQueue->size;
	priorityQueue->pdata[priorityQueue->size] = data;
	priorityQueue->size++;

	while (i > 0)
	{
		int parentIndex = (i - 1) / 2;

		if (priorityQueue->pdata[parentIndex] > priorityQueue->pdata[i])
		{
			int temp = priorityQueue->pdata[parentIndex];
			priorityQueue->pdata[parentIndex] = priorityQueue->pdata[i];
			priorityQueue->pdata[i] = temp;
		}

		i /= 2;
	}
}

void deleteMin(PRIORITY_QUEUE *priorityQueue)
{
	percolateUpEmpty(priorityQueue, 0);
}

void buildHeap(PRIORITY_QUEUE *priorityQueue)
{
	int i;

	for (i = priorityQueue->size / 2 - 1; i >= 0; i--)
	{
		percolateDown(priorityQueue, i);
		printData(priorityQueue);
	}
}

int main(void)
{
	int data[] =
	{ 10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13 };
	int size = sizeof(data) / sizeof(int);
	int i;

	PRIORITY_QUEUE *priorityQueue = initHeap(size);

	printData(priorityQueue);

	printf("----------\n");

	for (i = 0; i < size; i++)
	{
		// insert and insert2 get same results.
		insert(priorityQueue, data[i]);
		// insert2(priorityQueue, data[i]);
		printData(priorityQueue);
	}

	/*
	 Max/Size = 14 /  0:    0   0   0   0   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  1:   10   0   0   0   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  2:   10  12   0   0   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  3:    1  12  10   0   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  4:    1  12  10  14   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  5:    1   6  10  14  12   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  6:    1   6   5  14  12  10   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  7:    1   6   5  14  12  10   8   0   0   0   0   0   0   0
	 Max/Size = 14 /  8:    1   6   5  14  12  10   8  15   0   0   0   0   0   0
	 Max/Size = 14 /  9:    1   6   5   3  12  10   8  15  14   0   0   0   0   0
	 Max/Size = 14 / 10:    1   6   5   3   9  10   8  15  14  12   0   0   0   0
	 Max/Size = 14 / 11:    1   6   5   3   7  10   8  15  14  12   9   0   0   0
	 Max/Size = 14 / 12:    1   6   4   3   7   5   8  15  14  12   9  10   0   0
	 Max/Size = 14 / 13:    1   3   4   6   7   5   8  15  14  12   9  10  11   0
	 Max/Size = 14 / 14:    1   3   4   6   7   5   8  15  14  12   9  10  11  13
	 */

	printf("----------\n");

	for (i = 0; i < size; i++)
	{
		deleteMin(priorityQueue);
		printData(priorityQueue);
	}

	/*
	 Max/Size = 14 / 13:    3   6   4  14   7   5   8  15  14  12   9  10  11   0
	 Max/Size = 14 / 12:    4   6   5  14   7  10   8  15  14  12   9  10   0   0
	 Max/Size = 14 / 11:    5   6   8  14   7  10   8  15  14  12   9   0   0   0
	 Max/Size = 14 / 10:    6   7   8  14   9  10   8  15  14  12   0   0   0   0
	 Max/Size = 14 /  9:    7   9   8  14  12  10   8  15  14   0   0   0   0   0
	 Max/Size = 14 /  8:    8   9   8  14  12  10   8  15   0   0   0   0   0   0
	 Max/Size = 14 /  7:    8   9   8  14  12  10   8   0   0   0   0   0   0   0
	 Max/Size = 14 /  6:    8   9   8  14  12  10   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  5:    8   9  10  14  12   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  4:    9  12  10  14   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  3:   10  12  10   0   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  2:   10  12   0   0   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  1:   12   0   0   0   0   0   0   0   0   0   0   0   0   0
	 Max/Size = 14 /  0:    0   0   0   0   0   0   0   0   0   0   0   0   0   0
	 */

	printf("----------\n");

	priorityQueue->maxSize = size;
	priorityQueue->pdata = data;
	priorityQueue->size = size;

	printData(priorityQueue);

	/*
	 Max/Size = 14 / 14:   10  12   1  14   6   5   8  15   3   9   7   4  11  13
	 */

	printf("----------\n");

	buildHeap(priorityQueue);

	/*
	 i[ 6] =  8, child[13] = 13
	 Max/Size = 14 / 14:   10  12   1  14   6   5   8  15   3   9   7   4  11  13
	 i[ 5] =  5, child[11] =  4
	 Max/Size = 14 / 14:   10  12   1  14   6   4   8  15   3   9   7   5  11  13
	 i[ 4] =  6, child[10] =  7
	 Max/Size = 14 / 14:   10  12   1  14   6   4   8  15   3   9   7   5  11  13
	 i[ 3] = 14, child[ 8] =  3
	 Max/Size = 14 / 14:   10  12   1   3   6   4   8  15  14   9   7   5  11  13
	 i[ 2] =  1, child[ 5] =  4
	 Max/Size = 14 / 14:   10  12   1   3   6   4   8  15  14   9   7   5  11  13
	 i[ 1] = 12, child[ 3] =  3
	 i[ 3] = 12, child[ 8] = 14
	 Max/Size = 14 / 14:   10   3   1  12   6   4   8  15  14   9   7   5  11  13
	 i[ 0] = 10, child[ 2] =  1
	 i[ 2] = 10, child[ 5] =  4
	 i[ 5] = 10, child[11] =  5
	 Max/Size = 14 / 14:    1   3   4  12   6   5   8  15  14   9   7  10  11  13
	 */

	printf("----------\n");

	return EXIT_SUCCESS;
}
