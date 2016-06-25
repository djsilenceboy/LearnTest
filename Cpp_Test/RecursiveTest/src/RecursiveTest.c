/*
 ============================================================================
 Name        : RecursiveTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void printArray(int data[])
{
	int i;

	for (i = 0; i < 3; i++)
	{
		printf("%4d", data[i]);
	}

	printf("\n");
}

void comb(int n, int r, int lvl, int data[])
{
	int i;

	for (i = n; i >= r; i--)
	{
		data[lvl] = i;

		if (r == 1)
		{
			printArray(data);
		}
		else
		{
			comb(i - 1, r - 1, lvl + 1, data);
		}
	}
}

int main(void)
{
	int data[3] =
	{ 0 };

	comb(5, 3, 0, data);

	/*
	 5   4   3
	 5   4   2
	 5   4   1
	 5   3   2
	 5   3   1
	 5   2   1
	 4   3   2
	 4   3   1
	 4   2   1
	 3   2   1
	 */

	return EXIT_SUCCESS;
}
