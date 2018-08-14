/*
 ============================================================================
 Name        : ShortestPathTest.c
 Author      :
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_INT 32768

typedef struct _VERTEX
{
	int endVertex;
	int weight;
	struct _VERTEX *next;
} VERTEX;

void printLineData(int data[], int size)
{
	int i;

	for (i = 0; i < size; i++)
	{
		if (data[i] == MAX_INT)
		{
			printf("%5s", "MAX");
		}
		else
		{
			printf("%5d", data[i]);
		}
	}
	printf("\n");
}

void printArrayData(int *pdata, int size)
{
	int i;

	printf("Array data:\n");

	for (i = 0; i < size; i++)
	{
		printLineData(pdata + i * size, size);
	}
}

void printIntermediateData(int checkedVertex[], int vertexWeight[], int vertexParent[], int size)
{
	int i;

	printf("%6s:%8s,%8s,%8s\n", "Vertex", "Checked", "Weight", "Parent");

	for (i = 0; i < size; i++)
	{
		printf("%6d:", i);
		printf("%8d", checkedVertex[i]);
		if (vertexWeight[i] == MAX_INT)
		{
			printf("%8s", "MAX");
		}
		else
		{
			printf("%8d", vertexWeight[i]);
		}
		printf("%8d", vertexParent[i]);
		printf("\n");
	}
}

void printAllShortestPath(int checkedVertex[], int vertexWeight[], int vertexParent[], int size)
{
	int i;

	printf("Vertex: revert path (weight)\n");

	for (i = size - 1; i >= 0; i--)
	{
		printf("%6d", i);

		int current = i;

		while (vertexParent[current] != -1)
		{
			current = vertexParent[current];

			printf(" -> %2d", current);

		}

		if (vertexWeight[i] == MAX_INT)
		{
			printf(" (%s)", "MAX");
		}
		else
		{
			printf(" (%2d)", vertexWeight[i]);
		}
		printf("\n");
	}
}

void dijkstra(int *pdata, int size, int startVertex)
{
	int checkedVertex[size];
	int vertexWeight[size];
	int vertexParent[size];
	int checkedVertexCount = 0;
	int i, selectedVertex;
	int tempWeight;

	for (i = 0; i < size; i++)
	{
		checkedVertex[i] = 0;
		vertexWeight[i] = MAX_INT;
		vertexParent[i] = -1;
	}

	selectedVertex = startVertex;
	vertexWeight[selectedVertex] = 0;

	printf("---------- Start vertex %2d ----------\n", startVertex);

	printIntermediateData(checkedVertex, vertexWeight, vertexParent, size);
	printf("Selected: %2d\n", selectedVertex);

	while (checkedVertexCount < size)
	{
		// Set mark.
		checkedVertex[selectedVertex] = 1;
		checkedVertexCount++;

		// Update new weight.
		for (i = 0; i < size; i++)
		{
			// For each vertex not checked.
			if (checkedVertex[i] != 1)
			{
				tempWeight = *(pdata + selectedVertex * size + i);

				if ((tempWeight < MAX_INT) && (vertexWeight[selectedVertex] + tempWeight < vertexWeight[i]))
				{
					vertexWeight[i] = vertexWeight[selectedVertex] + tempWeight;
					vertexParent[i] = selectedVertex;
				}
			}
		}

		printIntermediateData(checkedVertex, vertexWeight, vertexParent, size);

		selectedVertex = -1;
		tempWeight = MAX_INT;

		// Find next selected vertex.
		for (i = 0; i < size; i++)
		{
			// For each vertex not checked.
			if (checkedVertex[i] != 1)
			{
				if (vertexWeight[i] < tempWeight)
				{
					selectedVertex = i;
					tempWeight = vertexWeight[i];
				}
			}
		}

		printf("Selected: %2d\n", selectedVertex);
	}

	printAllShortestPath(checkedVertex, vertexWeight, vertexParent, size);
}

void printShortestPath(VERTEX *pdata, int startVertex)
{
	VERTEX *head = pdata;

	printf("Path from %2d:", startVertex);

	while (head != NULL )
	{
		printf("%4d(%2d)", head->endVertex, head->weight);

		head = head->next;
	}

	printf("\n");
}

VERTEX* dijkstra2(int *pdata, int size, int startVertex)
{
	VERTEX *head = NULL, *tail = NULL, *tempVertex = NULL;
	int i;
	int checkedVertex[size];
	int checkedVertexCount = 0;
	int tempWeight, tempWeight2;

	printf("----------\n");
	printf("Start vertex: %2d\n", startVertex);

	memset(checkedVertex, 0, sizeof(int) * size);

	checkedVertex[startVertex] = 1;
	checkedVertexCount++;

	// If still have vertex not counted.
	while (checkedVertexCount < size)
	{
		printf("Line %2d:", startVertex);
		printLineData(pdata + startVertex * size, size);

		tempVertex = (VERTEX*) malloc(sizeof(VERTEX));

		tempVertex->endVertex = -1;
		tempVertex->weight = MAX_INT;
		tempVertex->next = NULL;

		// Find the vertex, which is not checked yet and with smallest weight.
		for (i = 0; i < size; i++)
		{
			// Find one vertex, which is not checked yet.
			if (checkedVertex[i] == 0)
			{
				tempWeight = *(pdata + startVertex * size + i);

				// Find the vertex with smallest weight.
				if (tempWeight < tempVertex->weight)
				{
					tempVertex->endVertex = i;
					tempVertex->weight = tempWeight;
				}
			}
		}

		printf("Find vertex: %2d\n", tempVertex->endVertex);

		// If no more such vertex.
		// 1. all vertex checked.
		// 2. or there is disconnected vertex.
		if (tempVertex->endVertex == -1)
		{
			break;
		}

		// Mark it.
		checkedVertex[tempVertex->endVertex] = 1;
		checkedVertexCount++;

		// Add found vertex into path.
		if (head == NULL )
		{
			head = tail = tempVertex;
		}
		else
		{
			tail->next = tempVertex;
			tail = tempVertex;
		}

		// For those vertex adjacent to just found vertex, reset vertex's weight.
		for (i = 0; i < size; i++)
		{
			if (checkedVertex[i] == 0)
			{
				// Its previous value.
				tempWeight = *(pdata + startVertex * size + i);
				// The new value through new found vertex.
				tempWeight2 = *(pdata + startVertex * size + tempVertex->endVertex) + *(pdata + tempVertex->endVertex * size + i);

				// Which one is smaller.
				*(pdata + startVertex * size + i) = (tempWeight < tempWeight2) ? tempWeight : tempWeight2;
			}
		}
	}

	printShortestPath(head, startVertex);

	return head;
}

void floyed(int *pdata, int size)
{
	int i, j, k;
	int tempWeight1, tempWeight2;

	printf("%2c: %2c -> %2c: weight\n", 'k', 'i', 'j');

	// Using each vertex as intermediate.
	for (k = 0; k < size; k++)
	{
		for (i = 0; i < size; i++)
		{
			if (i != k)
			{
				for (j = 0; j < size; j++)
				{
					if ((j != k) && (i != j))
					{
						// Compare E<i,j> and E<i,k>+E<k,j>.
						tempWeight1 = *(pdata + i * size + j);
						tempWeight2 = *(pdata + i * size + k) + *(pdata + k * size + j);

						if (tempWeight2 < MAX_INT)
						{
							printf("%2d: %2d -> %2d: %5d, %5d\n", k, i, j, tempWeight1, tempWeight2);

							// Which one is smaller.
							*(pdata + i * size + j) = (tempWeight1 < tempWeight2) ? tempWeight1 : tempWeight2;
						}
					}
				}
			}
		}
	}

	printArrayData(pdata, size);
}

int main(void)
{
	int size = 6;
	int data[][6] =
	{
	{ 0, MAX_INT, 10, MAX_INT, 55, MAX_INT },
	  { MAX_INT, 0, MAX_INT, 25, MAX_INT, MAX_INT },
	  { MAX_INT, 20, 0, MAX_INT, 30, 5 },
	  { MAX_INT, MAX_INT, MAX_INT, 0, MAX_INT, 40 },
	  { MAX_INT, MAX_INT, MAX_INT, 3, 0, MAX_INT },
	  { MAX_INT, 8, MAX_INT, MAX_INT, 15, 0 } };
	VERTEX *shortestPath = NULL;
	int tempData[size][size];
	int i;

	printf("---------- Original data ----------\n");

	/*
	 |v1    20    v2
	 |    8     5    10
	 |25    v5    30    v0
	 |   40    15    55
	 |v3     3    v4
	 0->2, 0->4
	 1->3
	 2->1, 2->4, 2->5
	 3->5
	 4->3
	 5->1, 5->4
	 */

	/*
	 ---------- Original data ----------
	 Array data:
	 --0  MAX   10  MAX   55  MAX
	 MAX    0  MAX   25  MAX  MAX
	 MAX   20    0  MAX   30    5
	 MAX  MAX  MAX    0  MAX   40
	 MAX  MAX  MAX    3    0  MAX
	 MAX    8  MAX  MAX   15    0
	 */

	printArrayData(data, size);

	printf("---------- Dijkstra ----------\n");

	for (i = 0; i < size; i++)
	{
		memcpy(tempData, *data, sizeof(data));
		dijkstra(*tempData, size, i);
	}

	/*
	 ---------- Dijkstra ----------
	 ---------- Start vertex  0 ----------
	 Vertex: Checked,  Weight,  Parent
	 0:       0       0      -1
	 1:       0     MAX      -1
	 2:       0     MAX      -1
	 3:       0     MAX      -1
	 4:       0     MAX      -1
	 5:       0     MAX      -1
	 Selected:  0
	 Vertex: Checked,  Weight,  Parent
	 0:       1       0      -1
	 1:       0     MAX      -1
	 2:       0      10       0
	 3:       0     MAX      -1
	 4:       0      55       0
	 5:       0     MAX      -1
	 Selected:  2
	 Vertex: Checked,  Weight,  Parent
	 0:       1       0      -1
	 1:       0      30       2
	 2:       1      10       0
	 3:       0     MAX      -1
	 4:       0      40       2
	 5:       0      15       2
	 Selected:  5
	 Vertex: Checked,  Weight,  Parent
	 0:       1       0      -1
	 1:       0      23       5
	 2:       1      10       0
	 3:       0     MAX      -1
	 4:       0      30       5
	 5:       1      15       2
	 Selected:  1
	 Vertex: Checked,  Weight,  Parent
	 0:       1       0      -1
	 1:       1      23       5
	 2:       1      10       0
	 3:       0      48       1
	 4:       0      30       5
	 5:       1      15       2
	 Selected:  4
	 Vertex: Checked,  Weight,  Parent
	 0:       1       0      -1
	 1:       1      23       5
	 2:       1      10       0
	 3:       0      33       4
	 4:       1      30       5
	 5:       1      15       2
	 Selected:  3
	 Vertex: Checked,  Weight,  Parent
	 0:       1       0      -1
	 1:       1      23       5
	 2:       1      10       0
	 3:       1      33       4
	 4:       1      30       5
	 5:       1      15       2
	 Selected: -1
	 Vertex: revert path (weight)
	 5 ->  2 ->  0 (15)
	 4 ->  5 ->  2 ->  0 (30)
	 3 ->  4 ->  5 ->  2 ->  0 (33)
	 2 ->  0 (10)
	 1 ->  5 ->  2 ->  0 (23)
	 0 ( 0)
	 ---------- Start vertex  1 ----------
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0       0      -1
	 2:       0     MAX      -1
	 3:       0     MAX      -1
	 4:       0     MAX      -1
	 5:       0     MAX      -1
	 Selected:  1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       0      -1
	 2:       0     MAX      -1
	 3:       0      25       1
	 4:       0     MAX      -1
	 5:       0     MAX      -1
	 Selected:  3
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       0      -1
	 2:       0     MAX      -1
	 3:       1      25       1
	 4:       0     MAX      -1
	 5:       0      65       3
	 Selected:  5
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       0      -1
	 2:       0     MAX      -1
	 3:       1      25       1
	 4:       0      80       5
	 5:       1      65       3
	 Selected:  4
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       0      -1
	 2:       0     MAX      -1
	 3:       1      25       1
	 4:       1      80       5
	 5:       1      65       3
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       0      -1
	 2:       0     MAX      -1
	 3:       1      25       1
	 4:       1      80       5
	 5:       1      65       3
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       0      -1
	 2:       0     MAX      -1
	 3:       1      25       1
	 4:       1      80       5
	 5:       1      65       3
	 Selected: -1
	 Vertex: revert path (weight)
	 5 ->  3 ->  1 (65)
	 4 ->  5 ->  3 ->  1 (80)
	 3 ->  1 (25)
	 2 (MAX)
	 1 ( 0)
	 0 (MAX)
	 ---------- Start vertex  2 ----------
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0     MAX      -1
	 2:       0       0      -1
	 3:       0     MAX      -1
	 4:       0     MAX      -1
	 5:       0     MAX      -1
	 Selected:  2
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0      20       2
	 2:       1       0      -1
	 3:       0     MAX      -1
	 4:       0      30       2
	 5:       0       5       2
	 Selected:  5
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0      13       5
	 2:       1       0      -1
	 3:       0     MAX      -1
	 4:       0      20       5
	 5:       1       5       2
	 Selected:  1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      13       5
	 2:       1       0      -1
	 3:       0      38       1
	 4:       0      20       5
	 5:       1       5       2
	 Selected:  4
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      13       5
	 2:       1       0      -1
	 3:       0      23       4
	 4:       1      20       5
	 5:       1       5       2
	 Selected:  3
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      13       5
	 2:       1       0      -1
	 3:       1      23       4
	 4:       1      20       5
	 5:       1       5       2
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      13       5
	 2:       1       0      -1
	 3:       1      23       4
	 4:       1      20       5
	 5:       1       5       2
	 Selected: -1
	 Vertex: revert path (weight)
	 5 ->  2 ( 5)
	 4 ->  5 ->  2 (20)
	 3 ->  4 ->  5 ->  2 (23)
	 2 ( 0)
	 1 ->  5 ->  2 (13)
	 0 (MAX)
	 ---------- Start vertex  3 ----------
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0     MAX      -1
	 2:       0     MAX      -1
	 3:       0       0      -1
	 4:       0     MAX      -1
	 5:       0     MAX      -1
	 Selected:  3
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0     MAX      -1
	 2:       0     MAX      -1
	 3:       1       0      -1
	 4:       0     MAX      -1
	 5:       0      40       3
	 Selected:  5
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0      48       5
	 2:       0     MAX      -1
	 3:       1       0      -1
	 4:       0      55       5
	 5:       1      40       3
	 Selected:  1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      48       5
	 2:       0     MAX      -1
	 3:       1       0      -1
	 4:       0      55       5
	 5:       1      40       3
	 Selected:  4
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      48       5
	 2:       0     MAX      -1
	 3:       1       0      -1
	 4:       1      55       5
	 5:       1      40       3
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      48       5
	 2:       0     MAX      -1
	 3:       1       0      -1
	 4:       1      55       5
	 5:       1      40       3
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      48       5
	 2:       0     MAX      -1
	 3:       1       0      -1
	 4:       1      55       5
	 5:       1      40       3
	 Selected: -1
	 Vertex: revert path (weight)
	 5 ->  3 (40)
	 4 ->  5 ->  3 (55)
	 3 ( 0)
	 2 (MAX)
	 1 ->  5 ->  3 (48)
	 0 (MAX)
	 ---------- Start vertex  4 ----------
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0     MAX      -1
	 2:       0     MAX      -1
	 3:       0     MAX      -1
	 4:       0       0      -1
	 5:       0     MAX      -1
	 Selected:  4
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0     MAX      -1
	 2:       0     MAX      -1
	 3:       0       3       4
	 4:       1       0      -1
	 5:       0     MAX      -1
	 Selected:  3
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0     MAX      -1
	 2:       0     MAX      -1
	 3:       1       3       4
	 4:       1       0      -1
	 5:       0      43       3
	 Selected:  5
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0      51       5
	 2:       0     MAX      -1
	 3:       1       3       4
	 4:       1       0      -1
	 5:       1      43       3
	 Selected:  1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      51       5
	 2:       0     MAX      -1
	 3:       1       3       4
	 4:       1       0      -1
	 5:       1      43       3
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      51       5
	 2:       0     MAX      -1
	 3:       1       3       4
	 4:       1       0      -1
	 5:       1      43       3
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1      51       5
	 2:       0     MAX      -1
	 3:       1       3       4
	 4:       1       0      -1
	 5:       1      43       3
	 Selected: -1
	 Vertex: revert path (weight)
	 5 ->  3 ->  4 (43)
	 4 ( 0)
	 3 ->  4 ( 3)
	 2 (MAX)
	 1 ->  5 ->  3 ->  4 (51)
	 0 (MAX)
	 ---------- Start vertex  5 ----------
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0     MAX      -1
	 2:       0     MAX      -1
	 3:       0     MAX      -1
	 4:       0     MAX      -1
	 5:       0       0      -1
	 Selected:  5
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0       8       5
	 2:       0     MAX      -1
	 3:       0     MAX      -1
	 4:       0      15       5
	 5:       1       0      -1
	 Selected:  1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       8       5
	 2:       0     MAX      -1
	 3:       0      33       1
	 4:       0      15       5
	 5:       1       0      -1
	 Selected:  4
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       8       5
	 2:       0     MAX      -1
	 3:       0      18       4
	 4:       1      15       5
	 5:       1       0      -1
	 Selected:  3
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       8       5
	 2:       0     MAX      -1
	 3:       1      18       4
	 4:       1      15       5
	 5:       1       0      -1
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       8       5
	 2:       0     MAX      -1
	 3:       1      18       4
	 4:       1      15       5
	 5:       1       0      -1
	 Selected: -1
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       1       8       5
	 2:       0     MAX      -1
	 3:       1      18       4
	 4:       1      15       5
	 5:       1       0      -1
	 Selected: -1
	 Vertex: revert path (weight)
	 5 ( 0)
	 4 ->  5 (15)
	 3 ->  4 ->  5 (18)
	 2 (MAX)
	 1 ->  5 ( 8)
	 0 (MAX)
	 */

	printf("---------- Dijkstra 2 ----------\n");

	for (i = 0; i < size; i++)
	{
		memcpy(tempData, *data, sizeof(data));
		shortestPath = dijkstra2(*tempData, size, i);
	}

	/*
	 ---------- Dijkstra 2 ----------
	 ----------
	 Start vertex:  0
	 Line  0:    0  MAX   10  MAX   55  MAX
	 Find vertex:  2
	 Line  0:    0   30   10  MAX   40   15
	 Find vertex:  5
	 Line  0:    0   23   10  MAX   30   15
	 Find vertex:  1
	 Line  0:    0   23   10   48   30   15
	 Find vertex:  4
	 Line  0:    0   23   10   33   30   15
	 Find vertex:  3
	 Path from  0:   2(10)   5(15)   1(23)   4(30)   3(33)
	 ----------
	 Start vertex:  1
	 Line  1:  MAX    0  MAX   25  MAX  MAX
	 Find vertex:  3
	 Line  1:  MAX    0  MAX   25  MAX   65
	 Find vertex:  5
	 Line  1:  MAX    0  MAX   25   80   65
	 Find vertex:  4
	 Line  1:  MAX    0  MAX   25   80   65
	 Find vertex: -1
	 Path from  1:   3(25)   5(65)   4(80)
	 ----------
	 Start vertex:  2
	 Line  2:  MAX   20    0  MAX   30    5
	 Find vertex:  5
	 Line  2:  MAX   13    0  MAX   20    5
	 Find vertex:  1
	 Line  2:  MAX   13    0   38   20    5
	 Find vertex:  4
	 Line  2:  MAX   13    0   23   20    5
	 Find vertex:  3
	 Line  2:  MAX   13    0   23   20    5
	 Find vertex: -1
	 Path from  2:   5( 5)   1(13)   4(20)   3(23)
	 ----------
	 Start vertex:  3
	 Line  3:  MAX  MAX  MAX    0  MAX   40
	 Find vertex:  5
	 Line  3:  MAX   48  MAX    0   55   40
	 Find vertex:  1
	 Line  3:  MAX   48  MAX    0   55   40
	 Find vertex:  4
	 Line  3:  MAX   48  MAX    0   55   40
	 Find vertex: -1
	 Path from  3:   5(40)   1(48)   4(55)
	 ----------
	 Start vertex:  4
	 Line  4:  MAX  MAX  MAX    3    0  MAX
	 Find vertex:  3
	 Line  4:  MAX  MAX  MAX    3    0   43
	 Find vertex:  5
	 Line  4:  MAX   51  MAX    3    0   43
	 Find vertex:  1
	 Line  4:  MAX   51  MAX    3    0   43
	 Find vertex: -1
	 Path from  4:   3( 3)   5(43)   1(51)
	 ----------
	 Start vertex:  5
	 Line  5:  MAX    8  MAX  MAX   15    0
	 Find vertex:  1
	 Line  5:  MAX    8  MAX   33   15    0
	 Find vertex:  4
	 Line  5:  MAX    8  MAX   18   15    0
	 Find vertex:  3
	 Line  5:  MAX    8  MAX   18   15    0
	 Find vertex: -1
	 Path from  5:   1( 8)   4(15)   3(18)
	 */

	printf("---------- Floyed ----------\n");

	memcpy(tempData, *data, sizeof(data));
	floyed(*tempData, size);

	/*
	 ---------- Floyed ----------
	 k:  i ->  j: weight
	 1:  2 ->  3: 32768,    45
	 1:  5 ->  3: 32768,    33
	 2:  0 ->  1: 32768,    30
	 2:  0 ->  3: 32768,    55
	 2:  0 ->  4:    55,    40
	 2:  0 ->  5: 32768,    15
	 3:  0 ->  5:    15,    95
	 3:  1 ->  5: 32768,    65
	 3:  2 ->  5:     5,    85
	 3:  4 ->  5: 32768,    43
	 4:  0 ->  3:    55,    43
	 4:  0 ->  5:    15,    83
	 4:  2 ->  3:    45,    33
	 4:  2 ->  5:     5,    73
	 4:  5 ->  3:    33,    18
	 5:  0 ->  1:    30,    23
	 5:  0 ->  3:    43,    33
	 5:  0 ->  4:    40,    30
	 5:  1 ->  3:    25,    83
	 5:  1 ->  4: 32768,    80
	 5:  2 ->  1:    20,    13
	 5:  2 ->  3:    33,    23
	 5:  2 ->  4:    30,    20
	 5:  3 ->  1: 32768,    48
	 5:  3 ->  4: 32768,    55
	 5:  4 ->  1: 32768,    51
	 5:  4 ->  3:     3,    61
	 Array data:
	 --0   23   10   33   30   15
	 MAX    0  MAX   25   80   65
	 MAX   13    0   23   20    5
	 MAX   48  MAX    0   55   40
	 MAX   51  MAX    3    0   43
	 MAX    8  MAX   18   15    0
	 */

	return EXIT_SUCCESS;
}
