/*
 ============================================================================
 Name        : MixCalculationTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void find1()
{
	/*
	 153846 -> 615384
	 */
	int number = 16;

	do
	{
		if ((number % 10) == 6)
		{
			int newNumber = (number / 10) + (int) pow(10, (int) log10(number)) * 6;

			printf("%d -> %d\n", number, newNumber);

			if ((number * 4) == newNumber)
			{
				printf("%d !!!\n", number);

				break;
			}
		}

		number++;
	} while (1);
}

void find2()
{
	/*
	 121,    11, reverse
	 484,    22, reverse
	 676,    26, reverse
	 10201,   101, reverse
	 12321,   111, reverse
	 14641,   121, reverse
	 40804,   202, reverse
	 44944,   212, reverse
	 69696,   264, reverse
	 94249,   307, reverse
	 698896,   836, reverse
	 1002001,  1001, reverse
	 1234321,  1111, reverse
	 4008004,  2002, reverse
	 5221225,  2285, reverse
	 6948496,  2636, reverse
	 */
	int number;

	for (number = 11; number <= 9999999; number++)
	{
		double root = pow(number, 0.5);

		if (fabs(root - (double) (int) root) < 0.00001)
		{
			int tempNumber = number;
			int length = (int) log10(number) + 1;
			int digits[length];
			int i;

			for (i = 0; i < length; i++)
			{
				digits[i] = tempNumber % 10;
				tempNumber /= 10;
			}

			int match = 1;

			for (i = 0; i < length / 2; i++)
			{
				if (digits[i] != digits[length - i - 1])
				{
					match = 0;

					break;
				}
			}

			if (match == 1)
			{
				printf("%7d,  %4d, reverse\n", number, (int) root);
			}
		}
	}
}

int main(void)
{

	// find1();
	find2();

	return EXIT_SUCCESS;
}
