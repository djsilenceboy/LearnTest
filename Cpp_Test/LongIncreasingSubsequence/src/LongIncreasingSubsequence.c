/*
 ============================================================================
 Name        : LongIncreasingSubsequence.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : In computer science, the longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible.
               This subsequence is not necessarily contiguous, or unique.
               Longest increasing subsequences are studied in the context of various disciplines related to mathematics, including algorithmics, random matrix theory, representation theory, and physics.
               The longest increasing subsequence problem is solvable in time O(n log n), where n denotes the length of the input sequence.
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define MAX_INT 32768

void printData(int data[], int size)
{
	int i;

	for (i = 0; i < size; i++)
	{
		printf("%4d", data[i]);
	}
	printf("\n");
}

void process(int data[], int size)
{
	int i = 0;
	int j = size - 1;
	int min = MAX_INT;
	int max = -MAX_INT;
	int currentL = min;
	int currentR = max;
	int *seqDataL = malloc(sizeof(int) * size);
	int seqDataLIdx = 0;
	int *seqDataR = malloc(sizeof(int) * size);
	int seqDataRIdx = 0;

	memset(seqDataL, 0, sizeof(int) * size);
	memset(seqDataR, 0, sizeof(int) * size);

	printf("========================================\n");

	printf("Original data: ");
	printData(data, size);

	printf("------------------------------\n");

	while (i <= j)
	{
		if (data[i] < min)
		{
			seqDataLIdx = 0;
			min = currentL = seqDataL[seqDataLIdx] = data[i];
		}
		else if (data[i] > currentL)
		{
			seqDataLIdx++;
			currentL = seqDataL[seqDataLIdx] = data[i];
		}

		printf("i, min, currentL, seqDataLIdx: %2d, %2d, %2d, %2d\n", i, min, currentL, seqDataLIdx);
		printf("Seq Data (Left): ");
		printData(seqDataL, seqDataLIdx + 1);

		i++;

		printf("------------------------------\n");

		if (i < j)
		{
			if (data[j] > max)
			{
				seqDataRIdx = 0;
				max = currentR = seqDataR[seqDataRIdx] = data[j];
			}
			else if (data[j] < currentR)
			{
				seqDataRIdx++;
				currentR = seqDataR[seqDataRIdx] = data[j];
			}

			j--;

			printf("j, max, currentR, seqDataRIdx: %2d, %2d, %2d, %2d\n", j, max, currentR, seqDataRIdx);
			printf("Seq Data (Right): ");
			printData(seqDataR, seqDataRIdx + 1);

			printf("------------------------------\n");
		}
	}

	printf("------------------------------\n");

	printf("Seq Data (Left): ");
	printData(seqDataL, seqDataLIdx + 1);
	printf("Seq Data (Right): ");
	printData(seqDataR, seqDataRIdx + 1);

	printf("========================================\n");
}

int main(void)
{
	int data[] =
	{ 3, 1, 4, 1, 5, 9, 2, 6, 5 };
	int size = sizeof(data) / sizeof(data[0]);

	process(data, size);

	/*
	 ========================================
	 Original data:    3   1   4   1   5   9   2   6   5
	 ------------------------------
	 i, min, currentL, seqDataLIdx:  0,  3,  3,  0
	 Seq Data (Left):    3
	 ------------------------------
	 j, max, currentR, seqDataRIdx:  7,  5,  5,  0
	 Seq Data (Right):    5
	 ------------------------------
	 i, min, currentL, seqDataLIdx:  1,  1,  1,  0
	 Seq Data (Left):    1
	 ------------------------------
	 j, max, currentR, seqDataRIdx:  6,  6,  6,  0
	 Seq Data (Right):    6
	 ------------------------------
	 i, min, currentL, seqDataLIdx:  2,  1,  4,  1
	 Seq Data (Left):    1   4
	 ------------------------------
	 j, max, currentR, seqDataRIdx:  5,  6,  2,  1
	 Seq Data (Right):    6   2
	 ------------------------------
	 i, min, currentL, seqDataLIdx:  3,  1,  4,  1
	 Seq Data (Left):    1   4
	 ------------------------------
	 j, max, currentR, seqDataRIdx:  4,  9,  9,  0
	 Seq Data (Right):    9
	 ------------------------------
	 i, min, currentL, seqDataLIdx:  4,  1,  5,  2
	 Seq Data (Left):    1   4   5
	 ------------------------------
	 ------------------------------
	 Seq Data (Left):    1   4   5
	 Seq Data (Right):    9
	 ========================================
	 */

	return EXIT_SUCCESS;
}
