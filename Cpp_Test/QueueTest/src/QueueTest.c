/*
 ============================================================================
 Name        : QueueTest.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define QUEUE_SIZE 6

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

void testQueue()
{
	QUEUE *queue = malloc(sizeof(QUEUE));
	int data = -1;

	memset(queue->data, -1, sizeof(int) * QUEUE_SIZE);
	queue->size = QUEUE_SIZE;
	queue->qsize = 0;
	queue->position = 0;

	printQueue(queue);

	data = deQueue(queue);
	printf("DeQueue: %2d\n", data);
	printQueue(queue);

	data = 2;
	printf("EnQueue: %2d\n", data);
	enQueue(queue, data);
	printQueue(queue);

	data = 6;
	printf("EnQueue: %2d\n", data);
	enQueue(queue, data);
	printQueue(queue);

	data = 4;
	printf("EnQueue: %2d\n", data);
	enQueue(queue, data);
	printQueue(queue);

	data = deQueue(queue);
	printf("DeQueue: %2d\n", data);
	printQueue(queue);

	data = deQueue(queue);
	printf("DeQueue: %2d\n", data);
	printQueue(queue);

	data = 8;
	printf("EnQueue: %2d\n", data);
	enQueue(queue, data);
	printQueue(queue);
}

int main(void)
{
	testQueue();

	return EXIT_SUCCESS;
}
