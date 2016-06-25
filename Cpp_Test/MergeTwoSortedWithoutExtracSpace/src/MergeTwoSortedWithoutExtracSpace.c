/*
 ============================================================================
 Name        : MergeTwoSortedWithoutExtracSpace.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Given an array of n elements and an integer k where k < n.
               Elements {a0...ak} and {ak+1...an} are already sorted. Give an algorithm to sort in O(n) time and O(1) space.
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

/**
 * Traditional simple method for any size of two sorted arrays.
 * But not good enough.
 */
void mergeTwoSortedWithoutExtracSpace(int *pdata, int size, int secondPos)
{
	int i = 0;
	int j = secondPos;

	while (i < secondPos)
	{
		printf("i, j = %2d, %2d: ", i, j);
		printArray(pdata, size);

		if (pdata[j] <= pdata[i])
		{
			int temp = pdata[j];
			int k;

			for (k = j; k > i; k--)
			{
				pdata[k] = pdata[k - 1];
			}

			pdata[i] = temp;
			j++;
		}

		i++;
	}

	printf("i, j = %2d, %2d: ", i, j);
	printArray(pdata, size);
}

/**
 * The relative simple method for any size of two sorted arrays.
 */
void mergeTwoSortedWithoutExtracSpace2(int *pdata, int size, int secondPos)
{
	int k;

	// Scan the first half.
	for (k = 0; k < secondPos; k++)
	{
		printf("k = %2d: %7c", k, ' ');
		printArray(pdata, size);

		int i = k;
		int j = secondPos;

		// Scan either all of the first half or the second half, whichever is shorter.
		while ((i < secondPos) && (j < size))
		{
			// Swap if necessary.
			if (pdata[j] <= pdata[i])
			{
				int temp = pdata[j];
				pdata[j] = pdata[i];
				pdata[i] = temp;
			}

			printf("i, j = %2d, %2d: ", i, j);
			printArray(pdata, size);

			i++;
			j++;
		}

		// If the second half is longer than the first half.
		if (j < size)
		{
			// [j - 1] is the last and largest element from previous while().
			// Use bubble sort to move it to the proper place in the tail of second half.

			int temp = pdata[j - 1];

			while (j < size)
			{
				if (pdata[j] <= temp)
				{
					pdata[j - 1] = pdata[j];
				}
				else
				{
					// Find place.
					break;
				}

				printf("i, j = %2d, %2d: ", i, j);
				printArray(pdata, size);

				j++;
			}

			// Put the largest.
			pdata[j - 1] = temp;

			printf("i, j = %2d, %2d: ", i, j);
			printArray(pdata, size);
		}
	}
}

int main(void)
{
	int data[] =
	{ 1, 3, 6, 8, -5, -2, 3, 8 };
	int size = sizeof(data) / sizeof(data[0]);

	mergeTwoSortedWithoutExtracSpace(data, size, 4);

	/*
	 i, j =  0,  4:    1   3   6   8  -5  -2   3   8
	 i, j =  1,  5:   -5   1   3   6   8  -2   3   8
	 i, j =  2,  6:   -5  -2   1   3   6   8   3   8
	 i, j =  3,  6:   -5  -2   1   3   6   8   3   8
	 i, j =  4,  7:   -5  -2   1   3   3   6   8   8
	 */

	printf("----------\n");

	int data2[] =
	{ 1, 3, 8, 12, -5, -2, 4, 7, 9, 10 };
	int size2 = sizeof(data2) / sizeof(data2[0]);

	mergeTwoSortedWithoutExtracSpace2(data2, size2, 4);

	/*
	 k =  0:           1   3   8  12  -5  -2   4   7   9  10
	 i, j =  0,  4:   -5   3   8  12   1  -2   4   7   9  10
	 i, j =  1,  5:   -5  -2   8  12   1   3   4   7   9  10
	 i, j =  2,  6:   -5  -2   4  12   1   3   8   7   9  10
	 i, j =  3,  7:   -5  -2   4   7   1   3   8  12   9  10
	 i, j =  4,  8:   -5  -2   4   7   1   3   8   9   9  10
	 i, j =  4,  9:   -5  -2   4   7   1   3   8   9  10  10
	 i, j =  4, 10:   -5  -2   4   7   1   3   8   9  10  12
	 k =  1:          -5  -2   4   7   1   3   8   9  10  12
	 i, j =  1,  4:   -5  -2   4   7   1   3   8   9  10  12
	 i, j =  2,  5:   -5  -2   3   7   1   4   8   9  10  12
	 i, j =  3,  6:   -5  -2   3   7   1   4   8   9  10  12
	 i, j =  4,  7:   -5  -2   3   7   1   4   8   9  10  12
	 k =  2:          -5  -2   3   7   1   4   8   9  10  12
	 i, j =  2,  4:   -5  -2   1   7   3   4   8   9  10  12
	 i, j =  3,  5:   -5  -2   1   4   3   7   8   9  10  12
	 i, j =  4,  6:   -5  -2   1   4   3   7   8   9  10  12
	 k =  3:          -5  -2   1   4   3   7   8   9  10  12
	 i, j =  3,  4:   -5  -2   1   3   4   7   8   9  10  12
	 i, j =  4,  5:   -5  -2   1   3   4   7   8   9  10  12
	 */

	printf("----------\n");

	int data3[] =
	{ 1, 3, 8, 12, 16, 18, -5, -2, 4, 9 };
	int size3 = sizeof(data3) / sizeof(data3[0]);

	mergeTwoSortedWithoutExtracSpace2(data3, size3, 6);

	/*
	 k =  0:           1   3   8  12  16  18  -5  -2   4   9
	 i, j =  0,  6:   -5   3   8  12  16  18   1  -2   4   9
	 i, j =  1,  7:   -5  -2   8  12  16  18   1   3   4   9
	 i, j =  2,  8:   -5  -2   4  12  16  18   1   3   8   9
	 i, j =  3,  9:   -5  -2   4   9  16  18   1   3   8  12
	 k =  1:          -5  -2   4   9  16  18   1   3   8  12
	 i, j =  1,  6:   -5  -2   4   9  16  18   1   3   8  12
	 i, j =  2,  7:   -5  -2   3   9  16  18   1   4   8  12
	 i, j =  3,  8:   -5  -2   3   8  16  18   1   4   9  12
	 i, j =  4,  9:   -5  -2   3   8  12  18   1   4   9  16
	 k =  2:          -5  -2   3   8  12  18   1   4   9  16
	 i, j =  2,  6:   -5  -2   1   8  12  18   3   4   9  16
	 i, j =  3,  7:   -5  -2   1   4  12  18   3   8   9  16
	 i, j =  4,  8:   -5  -2   1   4   9  18   3   8  12  16
	 i, j =  5,  9:   -5  -2   1   4   9  16   3   8  12  18
	 k =  3:          -5  -2   1   4   9  16   3   8  12  18
	 i, j =  3,  6:   -5  -2   1   3   9  16   4   8  12  18
	 i, j =  4,  7:   -5  -2   1   3   8  16   4   9  12  18
	 i, j =  5,  8:   -5  -2   1   3   8  12   4   9  16  18
	 k =  4:          -5  -2   1   3   8  12   4   9  16  18
	 i, j =  4,  6:   -5  -2   1   3   4  12   8   9  16  18
	 i, j =  5,  7:   -5  -2   1   3   4   9   8  12  16  18
	 k =  5:          -5  -2   1   3   4   9   8  12  16  18
	 i, j =  5,  6:   -5  -2   1   3   4   8   9  12  16  18
	 */

	return EXIT_SUCCESS;
}

