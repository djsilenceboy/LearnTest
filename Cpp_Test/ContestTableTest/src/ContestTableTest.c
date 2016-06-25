/*
 ============================================================================
 Name        : ContestTableTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void printArray(int *pdata, int sizeX, int sizeY)
{
	int i, j;

	for (i = 0; i < sizeX; i++)
	{
		for (j = 0; j < sizeY; j++)
		{
			printf("%4d", *(pdata + i * sizeX + j));
		}

		printf("\n");
	}

	printf("\n");
}

void swapTable(int *pdata, int dataSize, int partSize, int posX1, int posY1, int posX2, int posY2)
{
	int i, j;

	for (i = 0; i < partSize; i++)
	{
		for (j = 0; j < partSize; j++)
		{
			*(pdata + (posX2 + j) * dataSize + (posY2 + i)) = *(pdata + (posX1 + i) * dataSize + (posY1 + j));
		}
	}
}

void contestTable(int *pdata, int dataSize, int partSize, int posX, int posY)
{
	if (partSize == 2)
	{
		*(pdata + (posX + 1) * dataSize + posY) = *(pdata + posX * dataSize + posY + 1);
		*(pdata + (posX + 1) * dataSize + posY + 1) = *(pdata + posX * dataSize + posY);
	}
	else
	{
		int newSize = partSize / 2;

		contestTable(pdata, dataSize, newSize, posX, posY);
		contestTable(pdata, dataSize, newSize, posX, posY + newSize);

		swapTable(pdata, dataSize, newSize, posX, posY, posX + newSize, posY + newSize);
		swapTable(pdata, dataSize, newSize, posX, posY + newSize, posX + newSize, posY);
	}

	printArray(pdata, dataSize, dataSize);
}

int main(void)
{
	/* Array must be 2^m. */

	int dataSize = 8;
	int data[8][8] =
	{
	{ 1, 2, 3, 4, 5, 6, 7, 8 } };

	printArray(*data, dataSize, dataSize);

	contestTable(*data, dataSize, dataSize, 0, 0);

	return EXIT_SUCCESS;
}
