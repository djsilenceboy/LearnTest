/*
 ============================================================================
 Name        : MinCostTreeTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define MAX_INT 32768

typedef struct _EDGE
{
	int startVertex;
	int endVertex;
	int weight;
	struct _EDGE *next;
} EDGE;

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

void printEdges(EDGE *edges)
{
	EDGE *head = edges;

	while (head != NULL )
	{
		printf("Edge: %2d -> %2d (%2d)\n", head->startVertex, head->endVertex, head->weight);

		head = head->next;
	}

	printf("\n");
}

EDGE* buildEdges(int *pdata, int size)
{
	EDGE *edgesHead = NULL;
	int i, j;

	// Check upper-half triangle.
	for (i = 0; i < size - 1; i++)
	{
		for (j = i + 1; j < size; j++)
		{
			int weight = *(pdata + i * size + j);

			printf("Edge: %2d -> %2d (%2d)\n", i, j, weight);

			// If there is a connection.
			if ((weight > 0) && (weight < MAX_INT))
			{
				EDGE *edge = (EDGE*) malloc(sizeof(EDGE));
				edge->startVertex = i;
				edge->endVertex = j;
				edge->weight = weight;
				edge->next = NULL;

				// Insert into link, and sorted as smaller weight first.
				if (edgesHead == NULL )
				{
					edgesHead = edge;

					printf("Edge: Get head.\n");
				}
				else if (edge->weight < edgesHead->weight)
				{
					edge->next = edgesHead;
					edgesHead = edge;

					printf("Edge: Insert before head.\n");
				}
				else if (edgesHead->next == NULL )
				{
					edgesHead->next = edge;

					printf("Edge: Insert after head.\n");
				}
				else
				{
					EDGE *edgesCurrent = edgesHead;

					printf("Edge: Insert in list.\n");

					while ((edgesCurrent->next != NULL )&& (edge->weight >= edgesCurrent->next->weight )){
					edgesCurrent = edgesCurrent->next;
				}

					edge->next = edgesCurrent->next;
					edgesCurrent->next = edge;
				}
			}
		}
	}

	return edgesHead;
}

EDGE* prim(int *pdata, int size, int startVertex)
{
	EDGE *edgesHead = NULL;
	int checkedVertex[size];
	int vertexWeight[size];
	int vertexParent[size];
	int checkedVertexCount = 0;
	int i, selectedVertex = startVertex;
	int tempWeight;

	for (i = 0; i < size; i++)
	{
		checkedVertex[i] = 0;
		vertexWeight[i] = MAX_INT;
		vertexParent[i] = -1;
	}

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

				if (tempWeight < vertexWeight[i])
				{
					vertexWeight[i] = tempWeight;
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

	// Get all edges for min span tree.
	for (i = 0; i < size; i++)
	{
		if (vertexParent[i] != -1)
		{
			EDGE *edge = (EDGE*) malloc(sizeof(EDGE));
			edge->startVertex = vertexParent[i];
			edge->endVertex = i;
			edge->weight = vertexWeight[i];
			edge->next = NULL;

			if (edgesHead == NULL )
			{
				edgesHead = edge;
			}
			else
			{
				edge->next = edgesHead;
				edgesHead = edge;
			}
		}
	}

	return edgesHead;
}

EDGE* kruskal(int *pdata, int size, EDGE *allEdges)
{
	EDGE *edgesHead = NULL;
	int checkedVertex[size];
	int i;

	for (i = 0; i < size; i++)
	{
		checkedVertex[i] = 0;
	}

	checkedVertex[allEdges->startVertex] = 1;

	// Find next unchecked vertex with smallest edge.
	while (allEdges != NULL )
	{
		// Should use Disjoint set to check.
		// So, this method is not working 100% correct!!
		if ((checkedVertex[allEdges->startVertex] == 0) || (checkedVertex[allEdges->endVertex] == 0))
		{
			checkedVertex[allEdges->startVertex] = 1;
			checkedVertex[allEdges->endVertex] = 1;

			EDGE *edge = allEdges;
			allEdges = allEdges->next;
			edge->next = NULL;

			if (edgesHead == NULL )
			{
				edgesHead = edge;
			}
			else
			{
				edge->next = edgesHead;
				edgesHead = edge;
			}

			printf("Edge: %2d -> %2d (%2d)\n", edgesHead->startVertex, edgesHead->endVertex, edgesHead->weight);
		}
		else
		{
			allEdges = allEdges->next;
		}
	}

	return edgesHead;
}

int main(void)
{
	int size = 5;
	int data[][5] =
	{
	{ 0, 2, 12, 10, MAX_INT },
	  { 2, 0, 8, MAX_INT, 9 },
	  { 12, 8, 0, 6, 3 },
	  { 10, MAX_INT, 6, 0, 7 },
	  { MAX_INT, 9, 3, 7, 0 } };

	printf("---------- Original data ----------\n");

	printArrayData(data, size);

	printf("---------- Build edges ----------\n");

	EDGE *allEdges = buildEdges(data, size);

	printf("---------- Original edges ----------\n");

	printEdges(allEdges);

	printf("---------- Prim ----------\n");

	EDGE *edges = prim(data, size, allEdges->startVertex);
	printf("----------\n");
	printEdges(edges);

	/*
	 printf("---------- Kruskal ----------\n");

	 edges = kruskal(data, size, allEdges);
	 printf("----------\n");
	 printEdges(edges);
	 */

	/*
	 ---------- Original data ----------
	 Array data:
	 --0    2   12   10  MAX
	 --2    0    8  MAX    9
	 -12    8    0    6    3
	 -10  MAX    6    0    7
	 MAX    9    3    7    0
	 ---------- Builde edges ----------
	 Edge:  0 ->  1 ( 2)
	 Edge: Get head.
	 Edge:  0 ->  2 (12)
	 Edge: Insert after head.
	 Edge:  0 ->  3 (10)
	 Edge: Insert in list.
	 Edge:  0 ->  4 (32768)
	 Edge:  1 ->  2 ( 8)
	 Edge: Insert in list.
	 Edge:  1 ->  3 (32768)
	 Edge:  1 ->  4 ( 9)
	 Edge: Insert in list.
	 Edge:  2 ->  3 ( 6)
	 Edge: Insert in list.
	 Edge:  2 ->  4 ( 3)
	 Edge: Insert in list.
	 Edge:  3 ->  4 ( 7)
	 Edge: Insert in list.
	 ---------- Original edges ----------
	 Edge:  0 ->  1 ( 2)
	 Edge:  2 ->  4 ( 3)
	 Edge:  2 ->  3 ( 6)
	 Edge:  3 ->  4 ( 7)
	 Edge:  1 ->  2 ( 8)
	 Edge:  1 ->  4 ( 9)
	 Edge:  0 ->  3 (10)
	 Edge:  0 ->  2 (12)

	 ---------- Prim ----------
	 Vertex: Checked,  Weight,  Parent
	 0:       0     MAX      -1
	 1:       0     MAX      -1
	 2:       0     MAX      -1
	 3:       0     MAX      -1
	 4:       0     MAX      -1
	 Selected:  0
	 Vertex: Checked,  Weight,  Parent
	 0:       1     MAX      -1
	 1:       0       2       0
	 2:       0      12       0
	 3:       0      10       0
	 4:       0     MAX      -1
	 Selected:  1
	 Vertex: Checked,  Weight,  Parent
	 0:       1     MAX      -1
	 1:       1       2       0
	 2:       0       8       1
	 3:       0      10       0
	 4:       0       9       1
	 Selected:  2
	 Vertex: Checked,  Weight,  Parent
	 0:       1     MAX      -1
	 1:       1       2       0
	 2:       1       8       1
	 3:       0       6       2
	 4:       0       3       2
	 Selected:  4
	 Vertex: Checked,  Weight,  Parent
	 0:       1     MAX      -1
	 1:       1       2       0
	 2:       1       8       1
	 3:       0       6       2
	 4:       1       3       2
	 Selected:  3
	 Vertex: Checked,  Weight,  Parent
	 0:       1     MAX      -1
	 1:       1       2       0
	 2:       1       8       1
	 3:       1       6       2
	 4:       1       3       2
	 Selected: -1
	 ----------
	 Edge:  2 ->  4 ( 3)
	 Edge:  2 ->  3 ( 6)
	 Edge:  1 ->  2 ( 8)
	 Edge:  0 ->  1 ( 2)
	 */

	return EXIT_SUCCESS;
}
