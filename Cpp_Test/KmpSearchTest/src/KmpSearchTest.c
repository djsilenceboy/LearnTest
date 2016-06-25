/*
 ============================================================================
 Name        : KmpSearchTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Knuth-Morris-Pratt string search
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void printCharArray(char *pdata)
{
	int i, length = strlen(pdata);

	for (i = 0; i < length; i++)
	{
		printf("%3d", i);
	}

	printf("\n");

	for (i = 0; i < length; i++)
	{
		printf("%3c", *(pdata + i));
	}

	printf("\n");
}

void printIntArray(int *pdata, int length)
{
	int i;

	for (i = 0; i < length; i++)
	{
		printf("%3d", *(pdata + i));
	}

	printf("\n");
}

void preProcess(char *key, int *next, int length)
{
	int pos = 1;
	int k = 0;

	next[0] = -1;

	while (pos < length)
	{
		if (key[pos] == key[k])
		{
			next[pos] = 0;
			k++;
		}
		else if (k > 0)
		{
			next[pos] = k;
			k = next[k];
		}
		else
		{
			next[pos] = 0;
		}

		pos++;
	}
}

void searchOne(char *source, char *key, int *next)
{
	int sourceLength = strlen(source);
	int keyLength = strlen(key);
	int i = 0;
	int j = 0;
	int found = 0;

	printf("   i   j\n");
	printf("----------\n");

	while ((i + keyLength - j) <= sourceLength)
	{
		// If match.
		if (source[i] == key[j])
		{
			i++;
			j++;

			if (j >= keyLength)
			{
				found = 1;

				break;
			}
		}
		// If not match, but not first key char, just back key char.
		else if (j > 0)
		{
			j = next[j];
		}
		// If not match, but first key char, move to next source char.
		else
		{
			i++;
		}

		printf(" %3d %3d\n", i, j);
	}

	if (found == 1)
	{
		printf("Position = %d - %d\n", i - keyLength, i - 1);
	}
	else
	{
		printf("Not found.\n");
	}
}

void searchAll(char *source, char *key, int *next)
{
	int sourceLength = strlen(source);
	int keyLength = strlen(key);
	int i = 0;
	int j = 0;

	printf("   i   j\n");
	printf("----------\n");

	while ((i + keyLength - j) <= sourceLength)
	{
		// If match.
		if (source[i] == key[j])
		{
			i++;
			j++;

			if (j >= keyLength)
			{
				printf("Position = %d - %d\n", i - keyLength, i - 1);

				j = 0;
			}
		}
		// If not match, but not first key char, just back key char.
		else if (j > 0)
		{
			j = next[j];
		}
		// If not match, but first key char, move to next source char.
		else
		{
			i++;
		}

		printf(" %3d %3d\n", i, j);
	}
}

void kmp(char *source, char *key)
{
	int keyLength = strlen(key);
	int next[keyLength];

	memset(next, 0, keyLength * sizeof(int));

	printCharArray(source);
	printf("\n");
	printCharArray(key);
	preProcess(key, next, keyLength);
	printIntArray(next, keyLength);
	printf("\n");
	searchAll(source, key, next);
}

int main(void)
{
	kmp("abcabcabdabcdabd", "abcabdabcd");

	printf("====================\n");

	kmp("abcabbabcabd", "abcabc");

	printf("====================\n");

	kmp("ababcabcababcabd", "abcabc");

	printf("====================\n");

	kmp("abaacabaadabaae", "abaa");

	/*
	 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
	 a  b  c  a  b  c  a  b  d  a  b  c  d  a  b  d

	 0  1  2  3  4  5  6  7  8  9
	 a  b  c  a  b  d  a  b  c  d
	 -1  0  0  0  0  2  0  0  0  3

	 i   j
	 ----------
	 1   1
	 2   2
	 3   3
	 4   4
	 5   5
	 5   2
	 6   3
	 7   4
	 8   5
	 9   6
	 10   7
	 11   8
	 12   9
	 Position = 3 - 12
	 13   0
	 ====================
	 0  1  2  3  4  5  6  7  8  9 10 11
	 a  b  c  a  b  b  a  b  c  a  b  d

	 0  1  2  3  4  5
	 a  b  c  a  b  c
	 -1  0  0  0  0  0

	 i   j
	 ----------
	 1   1
	 2   2
	 3   3
	 4   4
	 5   5
	 5   0
	 6   0
	 7   1
	 8   2
	 9   3
	 10   4
	 11   5
	 11   0
	 ====================
	 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
	 a  b  a  b  c  a  b  c  a  b  a  b  c  a  b  d

	 0  1  2  3  4  5
	 a  b  c  a  b  c
	 -1  0  0  0  0  0

	 i   j
	 ----------
	 1   1
	 2   2
	 2   0
	 3   1
	 4   2
	 5   3
	 6   4
	 7   5
	 Position = 2 - 7
	 8   0
	 9   1
	 10   2
	 10   0
	 11   1
	 12   2
	 13   3
	 14   4
	 15   5
	 15   0
	 ====================
	 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
	 a  b  a  a  c  a  b  a  a  d  a  b  a  a  e

	 0  1  2  3
	 a  b  a  a
	 -1  0  0  1

	 i   j
	 ----------
	 1   1
	 2   2
	 3   3
	 Position = 0 - 3
	 4   0
	 5   0
	 6   1
	 7   2
	 8   3
	 Position = 5 - 8
	 9   0
	 10   0
	 11   1
	 12   2
	 13   3
	 Position = 10 - 13
	 14   0
	 */

	return EXIT_SUCCESS;
}
