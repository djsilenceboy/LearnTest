/*
 ============================================================================
 Name        : FindTwoNonRepeatElements.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Find the two non-repeating elements in an array of repeating elements
               Given an array in which all numbers except two are repeated once.
               (i.e. we have 2n+2 numbers and n numbers are occurring twice and remaining two have occurred once).
               Find those two numbers in the most efficient way.
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void printArray(int data[], int size)
{
	int i;

	for (i = 0; i < size; i++)
	{
		printf("%4d", data[i]);
	}

	printf("\n");
}

void findTwoNonRepeatElements(int *pdata, int size, int *pX, int *pY)
{
	int i;
	int sum = 0;

	// Calculate sum first.
	for (i = 0; i < size; i++)
	{
		sum ^= pdata[i];
	}

	printf("Sum = %04X\n", sum);

	// Find first left "1".
	i = 0;
	while ((sum & 1) == 0)
	{
		sum >>= 1;
		i++;
	}

	printf("First 1 from left (position from 0) = %d\n", i);

	int flag = 1;
	while (i > 0)
	{
		flag <<= 1;
		i--;
	}

	printf("Flag = %04X\n", flag);

	// Calculate sum for X and Y again.
	for (i = 0; i < size; i++)
	{
		if (pdata[i] & flag)
		{
			*pX ^= pdata[i];
		}
		else
		{
			*pY ^= pdata[i];
		}
	}

	printf("X = %d\n", *pX);
	printf("Y = %d\n", *pY);
}

int main(void)
{
	int data[] =
	{ 2, 3, 7, 9, 11, 2, 3, 11 };
	int size = sizeof(data) / sizeof(data[0]);
	int x = 0;
	int y = 0;

	printArray(data, size);

	findTwoNonRepeatElements(data, size, &x, &y);

	/*
	 2   3   7   9  11   2   3  11
	 Sum = 000E
	 First 1 from left (position from 0) = 1
	 Flag = 0002
	 X = 7
	 Y = 9
	 */
}
