/*
 ============================================================================
 Name        : LongCommonSubsequence.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : The longest common subsequence (LCS) problem is the problem of finding the longest subsequence common to all sequences in a set of sequences (often just two sequences).
               It differs from problems of finding common substrings: unlike substrings, subsequences are not required to occupy consecutive positions within the original sequences.
               The longest common subsequence problem is a classic computer science problem, the basis of data comparison programs such as the diff utility, and has applications in bioinformatics.
               It is also widely used by revision control systems such as Git for reconciling multiple changes made to a revision-controlled collection of files.
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void printData(char *data)
{
	int size = strlen(data);
	int i;

	for (i = 0; i < size; i++)
	{
		printf("%2c", data[i]);
	}
	printf("\n");
}

void printDataPart(char *data, int size)
{
	int i;

	for (i = 0; i < size; i++)
	{
		printf("%2c", data[i]);
	}
	printf("\n");
}

void process(char* dataA, char* dataB)
{
	int sizeA = strlen(dataA);
	int sizeB = strlen(dataB);
	int subLen, posB, posA, k;
	int count = 0;

	printf("Data A: ");
	printData(dataA);
	printf("Data B: ");
	printData(dataB);

	printf("Size A, B = %2d, %2d\n", sizeA, sizeB);

	for (subLen = 1; subLen <= sizeB; subLen++)
	{
		int foundAny = 0;

		for (posB = 0; posB <= sizeB - subLen; posB++)
		{
			for (posA = 0; posA <= sizeA - subLen; posA++)
			{
				for (k = 0; k < subLen; k++)
				{
					count++;

					printf("subLen, posB, posA, k = %2d, %2d, %2d, %2d\n", subLen, posB, posA, k);

					if (dataA[posA + k] != dataB[posB + k])
					{
						break;
					}
				}

				if (k == subLen)
				{
					printf("Find part = ");
					printDataPart(dataB + posB, subLen);
					printf("\n");

					foundAny++;
				}
			}
		}

		if (foundAny == 0)
		{
			printf("No more, no need to continue.\n");

			break;
		}
	}

	printf("Count = %d\n", count);
}

int main(void)
{
	char* dataA = "programming";
	char* dataB = "dynamic";

	process(dataA, dataB);

	return EXIT_SUCCESS;
}
