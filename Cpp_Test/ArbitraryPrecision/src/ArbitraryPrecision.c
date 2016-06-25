/*
 ============================================================================
 Name        : ArbitraryPrecision.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description :  In computer science, arbitrary-precision arithmetic, also called bignum arithmetic, multiple precision arithmetic, or sometimes infinite-precision arithmetic, indicates that calculations are performed on numbers whose digits of precision are limited only by the available memory of the host system.
                This contrasts with the faster fixed-precision arithmetic found in most arithmetic logic unit (ALU) hardware, which typically offers between 8 and 64 bits of precision.
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DATA_SIZE 1205

void printArray(unsigned char data[], int n, int maxPos)
{
	int i;

	printf(", %4d: ", maxPos);

	for (i = n - 1; i >= 0; i--)
	{
		if (i <= maxPos)
		{
			printf("%d", data[i]);
		}
		else
		{
			printf(" ");
		}
	}

	printf("\n");
}

void ArbitraryPrecision_n(int base, int n)
{
	unsigned char data[DATA_SIZE];
	int i, j, maxPos;

	memset(data, 0, sizeof(data));

	data[0] = 1;
	maxPos = 0;

	for (i = 0; i < n; i++)
	{
		printf("%4d", i);

		int increase = 0;

		for (j = 0; j <= maxPos; j++)
		{
			int temp = 0;

			if (data[j] > 0)
			{
				temp = data[j] * base;
			}

			temp += increase;

			increase = (temp >= 10) ? 1 : 0;

			data[j] = temp % 10;
		}

		if (increase == 1)
		{
			data[++maxPos] = 1;
		}

		printArray(data, sizeof(data), maxPos);

		if ((maxPos + 1) == DATA_SIZE)
		{
			break;
		}
	}

}

int main(void)
{
	ArbitraryPrecision_n(2, 4000);

	return EXIT_SUCCESS;
}
