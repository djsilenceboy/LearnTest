/*
 ============================================================================
 Name        : DisjointSetTest.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void printData(int data[], int size)
{
	int i;

	for (i = 0; i < size; i++)
	{
		printf("%4d", i);
	}
	printf("\n");

	for (i = 0; i < size; i++)
	{
		printf("%4d", data[i]);
	}
	printf("\n");
}

/**
 * bySize: "> 0" means by size; "<= 0" means by height.
 */
void init(int data[], int size, int bySize)
{
	int i;

	for (i = 0; i < size; i++)
	{
		data[i] = (bySize > 0) ? -1 : 0;
	}
}

/**
 * bySize: "> 0" means by size; "<= 0" means by height.
 */
void setUnion(int data[], int rootA, int rootB, int bySize)
{
	// By size.
	if (bySize > 0)
	{
		// rootA is shallower.
		if (data[rootA] > data[rootB])
		{
			data[rootB] += data[rootA];
			data[rootA] = rootB;
		}
		// rootA is equal or deeper.
		else
		{
			data[rootA] += data[rootB];
			data[rootB] = rootA;
		}
	}
	// By height.
	else
	{
		// rootA is shallower.
		if (data[rootA] > data[rootB])
		{
			data[rootA] = rootB;
		}
		// rootA is equal or deeper.
		else
		{
			// rootA is equal to rootB.
			if (data[rootA] == data[rootB])
			{
				// rootB increases 1 in height.
				data[rootA]--;
			}
			data[rootB] = rootA;
		}
	}
}

/**
 * pathCompression: "> 0" means path compression; "<= 0" means no path compression.
 */
int findRoot(int data[], int element, int pathCompression)
{
	// If find root.
	if (data[element] <= 0)
	{
		return element;
	}
	else
	{
		// Path compression.
		if (pathCompression > 0)
		{
			return data[element] = findRoot(data, data[element], pathCompression);
		}
		// No path compression.
		else
		{
			return findRoot(data, data[element], pathCompression);
		}
	}
}

void testSetUnion(int data[], int size, int bySize)
{
	int i;
	int pairs[][2] =
	{
	{ 1, 2 },
	  { 1, 3 },
	  { 4, 5 },
	  { 6, 7 },
	  { 4, 6 },
	  { 8, 9 },
	  { 10, 11 },
	  { 8, 10 },
	  { 4, 8 } };

	int pairSize = sizeof(pairs) / sizeof(int[2]);

	printf("----------Test SetUnion----------\n");
	printData(data, size);

	for (i = 0; i < pairSize; i++)
	{
		printf("Union by %s: %2d, %2d\n", (bySize > 0) ? "size" : "height", pairs[i][0], pairs[i][1]);

		setUnion(data, pairs[i][0], pairs[i][1], bySize);

		printData(data, size);
	}
}

void testFindRoot(int data[], int size, int pathCompression)
{
	int i;
	int findElements[] =
	{ 0, 1, 3, 7, 9, 11 };
	int elementSize = sizeof(findElements) / sizeof(int);

	printf("----------Test FindRoot----------\n");
	printData(data, size);

	for (i = 0; i < elementSize; i++)
	{
		int root = findRoot(data, findElements[i], pathCompression);

		printf("Find by %s: %2d -> %2d\n", (pathCompression > 0) ? "path compression" : "normal", findElements[i], root);

		if (pathCompression > 0)
		{
			printData(data, size);
		}
	}
}

int main(void)
{
	int size = 12;
	int* pdata = malloc(sizeof(int) * size);

	int bySize = 1;
	int pathCompression = 0;

	init(pdata, size, bySize);

	testSetUnion(pdata, size, bySize);

	testFindRoot(pdata, size, pathCompression);

	testFindRoot(pdata, size, !pathCompression);

	/*
	 ----------Test SetUnion----------
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -1  -1  -1  -1  -1  -1  -1  -1  -1  -1  -1
	 Union by size:  1,  2
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -2   1  -1  -1  -1  -1  -1  -1  -1  -1  -1
	 Union by size:  1,  3
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -1  -1  -1  -1  -1  -1  -1  -1
	 Union by size:  4,  5
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -2   4  -1  -1  -1  -1  -1  -1
	 Union by size:  6,  7
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -2   4  -2   6  -1  -1  -1  -1
	 Union by size:  4,  6
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -4   4   4   6  -1  -1  -1  -1
	 Union by size:  8,  9
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -4   4   4   6  -2   8  -1  -1
	 Union by size: 10, 11
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -4   4   4   6  -2   8  -2  10
	 Union by size:  8, 10
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -4   4   4   6  -4   8   8  10
	 Union by size:  4,  8
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   6   4   8   8  10
	 ----------Test FindRoot----------
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   6   4   8   8  10
	 Find by normal:  0 ->  0
	 Find by normal:  1 ->  1
	 Find by normal:  3 ->  1
	 Find by normal:  7 ->  4
	 Find by normal:  9 ->  4
	 Find by normal: 11 ->  4
	 ----------Test FindRoot----------
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   6   4   8   8  10
	 Find by path compression:  0 ->  0
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   6   4   8   8  10
	 Find by path compression:  1 ->  1
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   6   4   8   8  10
	 Find by path compression:  3 ->  1
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   6   4   8   8  10
	 Find by path compression:  7 ->  4
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   4   4   8   8  10
	 Find by path compression:  9 ->  4
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   4   4   4   8  10
	 Find by path compression: 11 ->  4
	 0   1   2   3   4   5   6   7   8   9  10  11
	 -1  -3   1   1  -8   4   4   4   4   4   4   4
	 */

	bySize = 0;
	pathCompression = 0;

	init(pdata, size, bySize);

	testSetUnion(pdata, size, bySize);

	testFindRoot(pdata, size, pathCompression);

	testFindRoot(pdata, size, !pathCompression);

	/*
	 ----------Test SetUnion----------
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0   0   0   0   0   0   0   0   0   0   0   0
	 Union by height:  1,  2
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   0   0   0   0   0   0   0   0   0
	 Union by height:  1,  3
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1   0   0   0   0   0   0   0   0
	 Union by height:  4,  5
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -1   4   0   0   0   0   0   0
	 Union by height:  6,  7
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -1   4  -1   6   0   0   0   0
	 Union by height:  4,  6
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -2   4   4   6   0   0   0   0
	 Union by height:  8,  9
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -2   4   4   6  -1   8   0   0
	 Union by height: 10, 11
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -2   4   4   6  -1   8  -1  10
	 Union by height:  8, 10
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -2   4   4   6  -2   8   8  10
	 Union by height:  4,  8
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   6   4   8   8  10
	 ----------Test FindRoot----------
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   6   4   8   8  10
	 Find by normal:  0 ->  0
	 Find by normal:  1 ->  1
	 Find by normal:  3 ->  1
	 Find by normal:  7 ->  4
	 Find by normal:  9 ->  4
	 Find by normal: 11 ->  4
	 ----------Test FindRoot----------
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   6   4   8   8  10
	 Find by path compression:  0 ->  0
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   6   4   8   8  10
	 Find by path compression:  1 ->  1
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   6   4   8   8  10
	 Find by path compression:  3 ->  1
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   6   4   8   8  10
	 Find by path compression:  7 ->  4
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   4   4   8   8  10
	 Find by path compression:  9 ->  4
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   4   4   4   8  10
	 Find by path compression: 11 ->  4
	 0   1   2   3   4   5   6   7   8   9  10  11
	 0  -1   1   1  -3   4   4   4   4   4   4   4
	 */

	return EXIT_SUCCESS;
}
