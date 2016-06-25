/*
 ============================================================================
 Name        : FindMajorityElement.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Majority Element: A majority element in an array A[] of size n is an element that appears more than n/2 times (and hence there is at most one such element).
               Write a function which takes an array and emits the majority element (if it exists), otherwise prints NONE as follows:
               I/P : 3 3 4 2 4 4 2 4 4
               O/P : 4

               I/P : 3 3 4 2 4 4 2 4
               O/P : NONE
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

int findMajorityElement(int data[], int size)
{
	printArray(data, size);

	if (size == 0)
	{
		return -1;
	}
	else if (size == 1)
	{
		return data[0];
	}
	else
	{
		int i, j = 0;
		int data2[size / 2];

		for (i = 0; i < size; i += 2)
		{
			if ((i == (size - 1)) || (data[i] == data[i + 1]))
			{
				data2[j] = data[i];
				j++;
			}
		}

		return findMajorityElement(data2, j);
	}
}

int main(void)
{
	int data[] =
	// { 3, 3, 4, 2, 4, 4, 2, 4, 4 }; // 4
	// { 3, 3, 4, 2, 4, 4, 2, 4 }; // -1
	// { 3, 3, 4, 2, 4, 4, 2, 4, 4, 3 }; // -1
	{ 3, 3, 4, 2, 4, 4, 2, 4 }; // -1

	int majority = findMajorityElement(data, sizeof(data) / sizeof(data[0]));

	printf("Majority element = %d", majority);

	return EXIT_SUCCESS;
}
