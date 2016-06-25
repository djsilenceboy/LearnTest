/*
 ============================================================================
 Name        : MatrixMulplicationTest.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
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
		if (data[i] == MAX_INT)
		{
			printf("%6s", "MAX");
		}
		else
		{
			printf("%6d", data[i]);
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
		printData(pdata + i * size, size);
	}
}

void printMatrixList(int *pdata, int size, int left, int right)
{
	if (left == right)
	{
		printf("M%d", left);
	}
	else if ((left + 1) == right)
	{
		printf("(M%d x M%d)", left, right);
	}
	else
	{
		int intermediate = *(pdata + left * size + right);

		printf("(");
		printMatrixList(pdata, size, left, intermediate);
		printf(" x ");
		printMatrixList(pdata, size, intermediate + 1, right);
		printf(")");
	}
}

void optimize(int c[], int N)
{
	int i, k, left, right;
	long minM;
	int *M = malloc(sizeof(int) * N * N);
	int *LastChange = malloc(sizeof(int) * N * N);

	printf("N: %2d\n", N);

	printf("c[]: ");
	printData(c, N);

	memset(M, 0, sizeof(int) * N * N);
	memset(LastChange, -1, sizeof(int) * N * N);

	for (k = 1; k < (N - 1); k++)
	{
		printf("========================================\n");
		printf("k = %d\n", k);

		for (left = 1; left <= N - 1 - k; left++)
		{
			right = left + k;
			*(M + left * N + right) = MAX_INT;

			printf("------------------------------\n");
			printf("(left, right) = (%d, %d)\n", left, right);

			for (i = left; i < right; i++)
			{
				printf("--------------------\n");
				printf("(left,i)(i+1, right) = (%d, %d)(%d, %d)\n", left, i, i + 1, right);
				printf("M(left,i), M(i+1, right), c = %d, %d, %d\n", *(M + left * N + i), *(M + (i + 1) * N + right), c[left - 1] * c[i] * c[right]);

				minM = *(M + left * N + i) + *(M + (i + 1) * N + right) + c[left - 1] * c[i] * c[right];

				printf("minM = %d\n", minM);

				if (minM < *(M + left * N + right))
				{
					*(M + left * N + right) = minM;
					*(LastChange + left * N + right) = i;
				}
			}

			printf("--------------------\n");
			printf("M[][]: ");
			printArrayData(M, N);

			printf("LastChange[][]: ");
			printArrayData(LastChange, N);
		}
	}

	printf("------------------------------\n");
	printMatrixList(LastChange, N, 1, N - 1);
	printf("\n");

	printf("========================================\n");
}

int main(void)
{
	{
		int c[] =
		{ 50, 10, 40, 30, 5 };
		int N = sizeof(c) / sizeof(c[0]);

		optimize(c, N);

		/*
		 M[][]: Array data:
		 0     0     0     0     0
		 0     0 20000 27000 10500
		 0     0     0 12000  8000
		 0     0     0     0  6000
		 0     0     0     0     0
		 LastChange[][]: Array data:
		 -1    -1    -1    -1    -1
		 -1    -1     1     1     1
		 -1    -1    -1     2     2
		 -1    -1    -1    -1     3
		 -1    -1    -1    -1    -1
		 ------------------------------
		 (M1 x (M2 x (M3 x M4)))
		 */
	}

	{
		int c[] =
		{ 10, 20, 1, 40, 5, 30, 15 };
		int N = sizeof(c) / sizeof(c[0]);

		optimize(c, N);

		/*
		 M[][]: Array data:
		 0     0     0     0     0     0     0
		 0     0   200   600   450   850  1150
		 0     0     0   800   300   950  1100
		 0     0     0     0   200   350   800
		 0     0     0     0     0  6000  5250
		 0     0     0     0     0     0  2250
		 0     0     0     0     0     0     0
		 LastChange[][]: Array data:
		 -1    -1    -1    -1    -1    -1    -1
		 -1    -1     1     2     2     2     2
		 -1    -1    -1     2     2     2     2
		 -1    -1    -1    -1     3     4     5
		 -1    -1    -1    -1    -1     4     4
		 -1    -1    -1    -1    -1    -1     5
		 -1    -1    -1    -1    -1    -1    -1
		 ------------------------------
		 ((M1 x M2) x (((M3 x M4) x M5) x M6))
		 */
	}

	return EXIT_SUCCESS;
}
