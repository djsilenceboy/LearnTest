/*
 ============================================================================
 Name        : TopologicalSortTest.c
 Author      :
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define QUEUE_SIZE 10

typedef struct _QUEUE
{
	int data[QUEUE_SIZE];
	int size;
	int qsize;
	int position;
} QUEUE;

void printQueue(QUEUE *queue)
{
	int i;

	printf("Queue: %2d, %2d, %2d: ", queue->size, queue->qsize, queue->position);

	for (i = 0; i < queue->size; i++)
	{
		printf("%4d", queue->data[i]);
	}
	printf("\n");
}

void enQueue(QUEUE *queue, int data)
{
	int dataPositon = (queue->position + queue->qsize) % queue->size;
	queue->data[dataPositon] = data;
	queue->qsize++;
}

/**
 * Return -1 means no data.
 */
int deQueue(QUEUE *queue)
{
	int data = -1;

	if (queue->qsize > 0)
	{
		data = queue->data[queue->position];
		queue->data[queue->position] = -1;
		queue->position = (queue->position + 1) % queue->size;
		queue->qsize--;
	}

	return data;
}

#define VERTEX_NUMBER 7

typedef struct _VERTEX
{
	int number; // For head node, it is indegree.
	struct _VERTEX *next;
} VERTEX;

void printVertice(VERTEX data[], int size)
{
	int i;

	printf("Adjacent table:\n");

	for (i = 0; i < size; i++)
	{
		printf("[%2d] <%2d>:", i, data[i].number);

		VERTEX *head = data[i].next;

		while (head != NULL )
		{
			printf("%4d", head->number);
			head = head->next;
		}
		printf("\n");
	}
}

void printData(int data[], int size)
{
	int i;

	printf("Result:");

	for (i = 0; i < size; i++)
	{
		printf("%4d", data[i]);
	}
	printf("\n");
}

VERTEX* buildData()
{
	VERTEX *data = malloc(sizeof(VERTEX) * VERTEX_NUMBER);
	VERTEX *temp;
	int i;

	/*
	 0 -> 1
	 0 -> 2
	 0 -> 3
	 1 -> 3
	 1 -> 4
	 2 -> 5
	 3 -> 2
	 3 -> 5
	 3 -> 6
	 4 -> 3
	 4 -> 6
	 6 -> 5
	 */

	// Reset.
	for (i = 0; i < VERTEX_NUMBER; i++)
	{
		data[i].number = 0;
		data[i].next = NULL;
	}

	// Add edges.
	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 3;
		temp->next = NULL;
		data[0].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 2;
		temp->next = data[0].next;
		data[0].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 1;
		temp->next = data[0].next;
		data[0].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 4;
		temp->next = NULL;
		data[1].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 3;
		temp->next = data[1].next;
		data[1].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 5;
		temp->next = NULL;
		data[2].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 6;
		temp->next = NULL;
		data[3].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 5;
		temp->next = data[3].next;
		data[3].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 2;
		temp->next = data[3].next;
		data[3].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 6;
		temp->next = NULL;
		data[4].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 3;
		temp->next = data[4].next;
		data[4].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 5;
		temp->next = NULL;
		data[6].next = temp;
	}

	// Calculate indegree.
	for (i = 0; i < VERTEX_NUMBER; i++)
	{
		temp = data[i].next;
		while (temp != NULL )
		{
			data[temp->number].number++;
			temp = temp->next;
		}
	}

	return data;
}

VERTEX* buildDataWithCycle()
{
	VERTEX *data = malloc(sizeof(VERTEX) * VERTEX_NUMBER);
	VERTEX *temp;
	int i;

	/*
	 0 -> 1
	 0 -> 2
	 0 -> 3
	 1 -> 3
	 1 -> 4
	 2 -> 5
	 3 -> 2
	 3 -> 5
	 3 -> 6
	 4 -> 3
	 6 -> 4
	 6 -> 5
	 */

	// Reset.
	for (i = 0; i < VERTEX_NUMBER; i++)
	{
		data[i].number = 0;
		data[i].next = NULL;
	}

	// Add edges.
	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 3;
		temp->next = NULL;
		data[0].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 2;
		temp->next = data[0].next;
		data[0].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 1;
		temp->next = data[0].next;
		data[0].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 4;
		temp->next = NULL;
		data[1].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 3;
		temp->next = data[1].next;
		data[1].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 5;
		temp->next = NULL;
		data[2].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 6;
		temp->next = NULL;
		data[3].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 5;
		temp->next = data[3].next;
		data[3].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 2;
		temp->next = data[3].next;
		data[3].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 3;
		temp->next = NULL;
		data[4].next = temp;
	}

	{
		temp = malloc(sizeof(VERTEX));
		temp->number = 5;
		temp->next = NULL;
		data[6].next = temp;

		temp = malloc(sizeof(VERTEX));
		temp->number = 4;
		temp->next = data[6].next;
		data[6].next = temp;
	}

	// Calculate indegree.
	for (i = 0; i < VERTEX_NUMBER; i++)
	{
		temp = data[i].next;
		while (temp != NULL )
		{
			data[temp->number].number++;
			temp = temp->next;
		}
	}

	return data;
}

void topologicalSort(VERTEX data[], int size)
{
	printVertice(data, size);

	QUEUE *queue = malloc(sizeof(QUEUE));

	memset(queue->data, -1, sizeof(int) * QUEUE_SIZE);
	queue->size = QUEUE_SIZE;
	queue->qsize = 0;
	queue->position = 0;

	int i, count = 0;
	int sorted[size];

	memset(sorted, -1, sizeof(int) * size);

	// EnQueue those indegree = 0.
	for (i = 0; i < size; i++)
	{
		if (data[i].number == 0)
		{
			printf("EnQueue: %2d\n", i);
			enQueue(queue, i);
		}
	}

	printQueue(queue);

	while (queue->qsize > 0)
	{
		int dataIndex = deQueue(queue);
		printf("DeQueue: %2d\n", dataIndex);

		sorted[count++] = dataIndex;

		VERTEX *temp = data[dataIndex].next;
		while (temp != NULL )
		{
			data[temp->number].number--;

			if (data[temp->number].number <= 0)
			{
				printf("EnQueue: %2d\n", temp->number);
				enQueue(queue, temp->number);
			}

			temp = temp->next;
		}

		printVertice(data, size);
		printQueue(queue);
	}

	if (count < size)
	{
		printf("Graph has a cycle.\n");
	}
	else
	{
		printData(sorted, size);
	}
}

int main(void)
{
	VERTEX *data = buildData();

	topologicalSort(data, VERTEX_NUMBER);

	/*
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 1>:   3   4
	 [ 2] < 2>:   5
	 [ 3] < 3>:   2   5   6
	 [ 4] < 1>:   3   6
	 [ 5] < 3>:
	 [ 6] < 2>:   5
	 EnQueue:  0
	 Queue: 10,  1,  0:    0  -1  -1  -1  -1  -1  -1  -1  -1  -1
	 DeQueue:  0
	 EnQueue:  1
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 1>:   5
	 [ 3] < 2>:   2   5   6
	 [ 4] < 1>:   3   6
	 [ 5] < 3>:
	 [ 6] < 2>:   5
	 Queue: 10,  1,  1:   -1   1  -1  -1  -1  -1  -1  -1  -1  -1
	 DeQueue:  1
	 EnQueue:  4
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 1>:   5
	 [ 3] < 1>:   2   5   6
	 [ 4] < 0>:   3   6
	 [ 5] < 3>:
	 [ 6] < 2>:   5
	 Queue: 10,  1,  2:   -1  -1   4  -1  -1  -1  -1  -1  -1  -1
	 DeQueue:  4
	 EnQueue:  3
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 1>:   5
	 [ 3] < 0>:   2   5   6
	 [ 4] < 0>:   3   6
	 [ 5] < 3>:
	 [ 6] < 1>:   5
	 Queue: 10,  1,  3:   -1  -1  -1   3  -1  -1  -1  -1  -1  -1
	 DeQueue:  3
	 EnQueue:  2
	 EnQueue:  6
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 0>:   5
	 [ 3] < 0>:   2   5   6
	 [ 4] < 0>:   3   6
	 [ 5] < 2>:
	 [ 6] < 0>:   5
	 Queue: 10,  2,  4:   -1  -1  -1  -1   2   6  -1  -1  -1  -1
	 DeQueue:  2
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 0>:   5
	 [ 3] < 0>:   2   5   6
	 [ 4] < 0>:   3   6
	 [ 5] < 1>:
	 [ 6] < 0>:   5
	 Queue: 10,  1,  5:   -1  -1  -1  -1  -1   6  -1  -1  -1  -1
	 DeQueue:  6
	 EnQueue:  5
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 0>:   5
	 [ 3] < 0>:   2   5   6
	 [ 4] < 0>:   3   6
	 [ 5] < 0>:
	 [ 6] < 0>:   5
	 Queue: 10,  1,  6:   -1  -1  -1  -1  -1  -1   5  -1  -1  -1
	 DeQueue:  5
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 0>:   5
	 [ 3] < 0>:   2   5   6
	 [ 4] < 0>:   3   6
	 [ 5] < 0>:
	 [ 6] < 0>:   5
	 Queue: 10,  0,  7:   -1  -1  -1  -1  -1  -1  -1  -1  -1  -1
	 Result:   0   1   4   3   2   6   5
	 */

	printf("----------------------------------------\n");

	data = buildDataWithCycle();

	topologicalSort(data, VERTEX_NUMBER);

	/*
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 1>:   3   4
	 [ 2] < 2>:   5
	 [ 3] < 3>:   2   5   6
	 [ 4] < 2>:   3
	 [ 5] < 3>:
	 [ 6] < 1>:   4   5
	 EnQueue:  0
	 Queue: 10,  1,  0:    0  -1  -1  -1  -1  -1  -1  -1  -1  -1
	 DeQueue:  0
	 EnQueue:  1
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 1>:   5
	 [ 3] < 2>:   2   5   6
	 [ 4] < 2>:   3
	 [ 5] < 3>:
	 [ 6] < 1>:   4   5
	 Queue: 10,  1,  1:   -1   1  -1  -1  -1  -1  -1  -1  -1  -1
	 DeQueue:  1
	 Adjacent table:
	 [ 0] < 0>:   1   2   3
	 [ 1] < 0>:   3   4
	 [ 2] < 1>:   5
	 [ 3] < 1>:   2   5   6
	 [ 4] < 1>:   3
	 [ 5] < 3>:
	 [ 6] < 1>:   4   5
	 Queue: 10,  0,  2:   -1  -1  -1  -1  -1  -1  -1  -1  -1  -1
	 Graph has a cycle.
	 */

	return EXIT_SUCCESS;
}
