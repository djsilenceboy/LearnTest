/*
 ============================================================================
 Name        : HashTest.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct _NODE
{
	int *pdata;
	int size;
	double lamda;
	int count;
} HASH_TABLE;

void printData(int data[], int size)
{
	int i;

	for (i = 0; i < size; i++)
	{
		printf("%4d", data[i]);
	}
	printf("\n");
}

void printHashTableInfo(HASH_TABLE *pHashTable)
{
	printf("HashTable: size %2d, lamda %4.2f, count %2d\n", pHashTable->size, pHashTable->lamda, pHashTable->count);
}

HASH_TABLE* generateHashTable(int size, double lamda)
{
	HASH_TABLE *pHashTable = malloc(sizeof(HASH_TABLE));

	pHashTable->pdata = malloc(sizeof(int) * size);
	pHashTable->size = size;
	pHashTable->lamda = lamda;
	pHashTable->count = 0;

	memset(pHashTable->pdata, 0, sizeof(int) * size);

	return pHashTable;
}

int hash(int data, int divident)
{
	return data % divident;
}

int hash2(int data, int divident)
{
	return divident - data % divident;
}

void testSeparateChaining(int data[], int size, double lamda)
{
	printf("---------- SeparateChaining ----------\n");

	int tableSize = size / lamda;
	HASH_TABLE *pHashTable[tableSize];
	int i;

	printData(data, size);

	for (i = 0; i < tableSize; i++)
	{
		pHashTable[i] = generateHashTable(tableSize, lamda);

		printHashTableInfo(pHashTable[i]);
	}

	for (i = 0; i < size; i++)
	{
		int pos = hash(data[i], tableSize);

		pHashTable[pos]->pdata[pHashTable[pos]->count++] = data[i];
	}

	for (i = 0; i < tableSize; i++)
	{
		printf("i=%2d : ", i);
		printData(pHashTable[i]->pdata, pHashTable[i]->count);
	}
}

HASH_TABLE* testOpenAddressingLinear(int data[], int size, double lamda)
{
	printf("---------- OpenAddressing Linear ----------\n");

	int tableSize = size / lamda;
	HASH_TABLE *pHashTable = generateHashTable(tableSize, lamda);
	int i;

	printHashTableInfo(pHashTable);
	printData(data, size);

	for (i = 0; i < size; i++)
	{
		int j = 0;

		do
		{
			int pos = (hash(data[i], tableSize) + j) % tableSize;

			if (pHashTable->pdata[pos] == 0)
			{
				pHashTable->pdata[pos] = data[i];
				pHashTable->count++;

				printf("HashTable[%2d], %2d = %2d : ", pos, j, pHashTable->pdata[pos]);
				printData(pHashTable->pdata, pHashTable->size);

				break;
			}
			else
			{
				j++;

				if (j >= tableSize)
				{
					printf("Linear probing issue.\n");

					break;
				}
			}
		} while (1);
	}

	printHashTableInfo(pHashTable);

	return pHashTable;
}

HASH_TABLE* testOpenAddressingQuadratic(int data[], int size, double lamda)
{
	printf("---------- OpenAddressing Quadratic ----------\n");

	int tableSize = size / lamda;
	HASH_TABLE *pHashTable = generateHashTable(tableSize, lamda);
	int i;

	printHashTableInfo(pHashTable);
	printData(data, size);

	for (i = 0; i < size; i++)
	{
		int j = 0;

		do
		{
			int pos = (hash(data[i], tableSize) + j * j) % tableSize;

			if (pHashTable->pdata[pos] == 0)
			{
				pHashTable->pdata[pos] = data[i];
				pHashTable->count++;

				printf("HashTable[%2d], %2d = %2d : ", pos, j, pHashTable->pdata[pos]);
				printData(pHashTable->pdata, pHashTable->size);

				break;
			}
			else
			{
				j++;

				if (j >= tableSize)
				{
					printf("Quadratic probing issue.\n");

					break;
				}
			}
		} while (1);
	}

	printHashTableInfo(pHashTable);

	return pHashTable;
}

HASH_TABLE* testOpenAddressingDoubleHashing(int data[], int size, double lamda)
{
	printf("---------- OpenAddressing DoubleHashing ----------\n");

	int tableSize = size / lamda;
	HASH_TABLE *pHashTable = generateHashTable(tableSize, lamda);
	int i;

	printHashTableInfo(pHashTable);
	printData(data, size);

	for (i = 0; i < size; i++)
	{
		int j = 0;

		do
		{
			int pos = (hash(data[i], tableSize) + j * hash2(data[i], 7)) % tableSize;

			if (pHashTable->pdata[pos] == 0)
			{
				pHashTable->pdata[pos] = data[i];
				pHashTable->count++;

				printf("HashTable[%2d], %2d = %2d : ", pos, j, pHashTable->pdata[pos]);
				printData(pHashTable->pdata, pHashTable->size);

				break;
			}
			else
			{
				j++;

				if (j >= tableSize)
				{
					printf("DoubleHashing probing issue.\n");

					break;
				}
			}
		} while (1);
	}

	printHashTableInfo(pHashTable);

	return pHashTable;
}

void testRehash(int data[], int size, double lamda)
{
	printf("---------- OpenAddressing Quadratic Rehash ----------\n");

	HASH_TABLE *pHashTable = testOpenAddressingQuadratic(data, size, lamda);

	testOpenAddressingQuadratic(pHashTable->pdata, pHashTable->size, lamda);
}

int main(void)
{
	int data[] =
	{ 11, 4, 99, 33, 8, 55, 9, 77, 2, 3, 66, 5, 22, 6, 1, 88, 7, 44 };
	int size = sizeof(data) / sizeof(int);

	testSeparateChaining(data, size, 1);

	/*
	 ---------- SeparateChaining ----------
	 11   4  99  33   8  55   9  77   2   3  66   5  22   6   1  88   7  44
	 i= 0 :
	 i= 1 :   55   1
	 i= 2 :    2
	 i= 3 :    3
	 i= 4 :    4  22
	 i= 5 :   77   5
	 i= 6 :    6
	 i= 7 :    7
	 i= 8 :    8  44
	 i= 9 :   99   9
	 i=10 :
	 i=11 :   11
	 i=12 :   66
	 i=13 :
	 i=14 :
	 i=15 :   33
	 i=16 :   88
	 i=17 :
	 */

	testOpenAddressingLinear(data, size, 1);
	/*
	 ---------- OpenAddressing Linear ----------
	 HashTable: size 18, lamda 1.00, count  0
	 11   4  99  33   8  55   9  77   2   3  66   5  22   6   1  88   7  44
	 HashTable[11],  0 = 11 :    0   0   0   0   0   0   0   0   0   0   0  11   0   0   0   0   0   0
	 HashTable[ 4],  0 =  4 :    0   0   0   0   4   0   0   0   0   0   0  11   0   0   0   0   0   0
	 HashTable[ 9],  0 = 99 :    0   0   0   0   4   0   0   0   0  99   0  11   0   0   0   0   0   0
	 HashTable[15],  0 = 33 :    0   0   0   0   4   0   0   0   0  99   0  11   0   0   0  33   0   0
	 HashTable[ 8],  0 =  8 :    0   0   0   0   4   0   0   0   8  99   0  11   0   0   0  33   0   0
	 HashTable[ 1],  0 = 55 :    0  55   0   0   4   0   0   0   8  99   0  11   0   0   0  33   0   0
	 HashTable[10],  1 =  9 :    0  55   0   0   4   0   0   0   8  99   9  11   0   0   0  33   0   0
	 HashTable[ 5],  0 = 77 :    0  55   0   0   4  77   0   0   8  99   9  11   0   0   0  33   0   0
	 HashTable[ 2],  0 =  2 :    0  55   2   0   4  77   0   0   8  99   9  11   0   0   0  33   0   0
	 HashTable[ 3],  0 =  3 :    0  55   2   3   4  77   0   0   8  99   9  11   0   0   0  33   0   0
	 HashTable[12],  0 = 66 :    0  55   2   3   4  77   0   0   8  99   9  11  66   0   0  33   0   0
	 HashTable[ 6],  1 =  5 :    0  55   2   3   4  77   5   0   8  99   9  11  66   0   0  33   0   0
	 HashTable[ 7],  3 = 22 :    0  55   2   3   4  77   5  22   8  99   9  11  66   0   0  33   0   0
	 HashTable[13],  7 =  6 :    0  55   2   3   4  77   5  22   8  99   9  11  66   6   0  33   0   0
	 HashTable[14], 13 =  1 :    0  55   2   3   4  77   5  22   8  99   9  11  66   6   1  33   0   0
	 HashTable[16],  0 = 88 :    0  55   2   3   4  77   5  22   8  99   9  11  66   6   1  33  88   0
	 HashTable[17], 10 =  7 :    0  55   2   3   4  77   5  22   8  99   9  11  66   6   1  33  88   7
	 HashTable[ 0], 10 = 44 :   44  55   2   3   4  77   5  22   8  99   9  11  66   6   1  33  88   7
	 HashTable: size 18, lamda 1.00, count 18
	 */

	testOpenAddressingQuadratic(data, size, 1);
	/*
	 ---------- OpenAddressing Quadratic ----------
	 HashTable: size 18, lamda 1.00, count  0
	 11   4  99  33   8  55   9  77   2   3  66   5  22   6   1  88   7  44
	 HashTable[11],  0 = 11 :    0   0   0   0   0   0   0   0   0   0   0  11   0   0   0   0   0   0
	 HashTable[ 4],  0 =  4 :    0   0   0   0   4   0   0   0   0   0   0  11   0   0   0   0   0   0
	 HashTable[ 9],  0 = 99 :    0   0   0   0   4   0   0   0   0  99   0  11   0   0   0   0   0   0
	 HashTable[15],  0 = 33 :    0   0   0   0   4   0   0   0   0  99   0  11   0   0   0  33   0   0
	 HashTable[ 8],  0 =  8 :    0   0   0   0   4   0   0   0   8  99   0  11   0   0   0  33   0   0
	 HashTable[ 1],  0 = 55 :    0  55   0   0   4   0   0   0   8  99   0  11   0   0   0  33   0   0
	 HashTable[10],  1 =  9 :    0  55   0   0   4   0   0   0   8  99   9  11   0   0   0  33   0   0
	 HashTable[ 5],  0 = 77 :    0  55   0   0   4  77   0   0   8  99   9  11   0   0   0  33   0   0
	 HashTable[ 2],  0 =  2 :    0  55   2   0   4  77   0   0   8  99   9  11   0   0   0  33   0   0
	 HashTable[ 3],  0 =  3 :    0  55   2   3   4  77   0   0   8  99   9  11   0   0   0  33   0   0
	 HashTable[12],  0 = 66 :    0  55   2   3   4  77   0   0   8  99   9  11  66   0   0  33   0   0
	 HashTable[ 6],  1 =  5 :    0  55   2   3   4  77   5   0   8  99   9  11  66   0   0  33   0   0
	 HashTable[13],  3 = 22 :    0  55   2   3   4  77   5   0   8  99   9  11  66  22   0  33   0   0
	 HashTable[ 7],  1 =  6 :    0  55   2   3   4  77   5   6   8  99   9  11  66  22   0  33   0   0
	 HashTable[17],  4 =  1 :    0  55   2   3   4  77   5   6   8  99   9  11  66  22   0  33   0   1
	 HashTable[16],  0 = 88 :    0  55   2   3   4  77   5   6   8  99   9  11  66  22   0  33  88   1
	 HashTable[14],  5 =  7 :    0  55   2   3   4  77   5   6   8  99   9  11  66  22   7  33  88   1
	 HashTable[ 0],  8 = 44 :   44  55   2   3   4  77   5   6   8  99   9  11  66  22   7  33  88   1
	 HashTable: size 18, lamda 1.00, count 18
	 */

	testOpenAddressingDoubleHashing(data, size, 1);

	/*
	 ---------- OpenAddressing DoubleHashing ----------
	 HashTable: size 18, lamda 1.00, count  0
	 11   4  99  33   8  55   9  77   2   3  66   5  22   6   1  88   7  44
	 HashTable[11],  0 = 11 :    0   0   0   0   0   0   0   0   0   0   0  11   0   0   0   0   0   0
	 HashTable[ 4],  0 =  4 :    0   0   0   0   4   0   0   0   0   0   0  11   0   0   0   0   0   0
	 HashTable[ 9],  0 = 99 :    0   0   0   0   4   0   0   0   0  99   0  11   0   0   0   0   0   0
	 HashTable[15],  0 = 33 :    0   0   0   0   4   0   0   0   0  99   0  11   0   0   0  33   0   0
	 HashTable[ 8],  0 =  8 :    0   0   0   0   4   0   0   0   8  99   0  11   0   0   0  33   0   0
	 HashTable[ 1],  0 = 55 :    0  55   0   0   4   0   0   0   8  99   0  11   0   0   0  33   0   0
	 HashTable[14],  1 =  9 :    0  55   0   0   4   0   0   0   8  99   0  11   0   0   9  33   0   0
	 HashTable[ 5],  0 = 77 :    0  55   0   0   4  77   0   0   8  99   0  11   0   0   9  33   0   0
	 HashTable[ 2],  0 =  2 :    0  55   2   0   4  77   0   0   8  99   0  11   0   0   9  33   0   0
	 HashTable[ 3],  0 =  3 :    0  55   2   3   4  77   0   0   8  99   0  11   0   0   9  33   0   0
	 HashTable[12],  0 = 66 :    0  55   2   3   4  77   0   0   8  99   0  11  66   0   9  33   0   0
	 HashTable[ 7],  1 =  5 :    0  55   2   3   4  77   0   5   8  99   0  11  66   0   9  33   0   0
	 HashTable[10],  1 = 22 :    0  55   2   3   4  77   0   5   8  99  22  11  66   0   9  33   0   0
	 HashTable[ 6],  0 =  6 :    0  55   2   3   4  77   6   5   8  99  22  11  66   0   9  33   0   0
	 HashTable[13],  2 =  1 :    0  55   2   3   4  77   6   5   8  99  22  11  66   1   9  33   0   0
	 HashTable[16],  0 = 88 :    0  55   2   3   4  77   6   5   8  99  22  11  66   1   9  33  88   0
	 HashTable[17],  4 =  7 :    0  55   2   3   4  77   6   5   8  99  22  11  66   1   9  33  88   7
	 HashTable[ 0],  2 = 44 :   44  55   2   3   4  77   6   5   8  99  22  11  66   1   9  33  88   7
	 HashTable: size 18, lamda 1.00, count 18
	 */

	testRehash(data, size, 0.8);

	/*
	 ---------- OpenAddressing Quadratic Rehash ----------
	 ---------- OpenAddressing Quadratic ----------
	 HashTable: size 22, lamda 0.80, count  0
	 11   4  99  33   8  55   9  77   2   3  66   5  22   6   1  88   7  44
	 HashTable[11],  0 = 11 :    0   0   0   0   0   0   0   0   0   0   0  11   0   0   0   0   0   0   0   0   0   0
	 HashTable[ 4],  0 =  4 :    0   0   0   0   4   0   0   0   0   0   0  11   0   0   0   0   0   0   0   0   0   0
	 HashTable[12],  1 = 99 :    0   0   0   0   4   0   0   0   0   0   0  11  99   0   0   0   0   0   0   0   0   0
	 HashTable[15],  2 = 33 :    0   0   0   0   4   0   0   0   0   0   0  11  99   0   0  33   0   0   0   0   0   0
	 HashTable[ 8],  0 =  8 :    0   0   0   0   4   0   0   0   8   0   0  11  99   0   0  33   0   0   0   0   0   0
	 HashTable[20],  3 = 55 :    0   0   0   0   4   0   0   0   8   0   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[ 9],  0 =  9 :    0   0   0   0   4   0   0   0   8   9   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[ 5],  4 = 77 :    0   0   0   0   4  77   0   0   8   9   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[ 2],  0 =  2 :    0   0   2   0   4  77   0   0   8   9   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[ 3],  0 =  3 :    0   0   2   3   4  77   0   0   8   9   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[ 0],  0 = 66 :   66   0   2   3   4  77   0   0   8   9   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[ 6],  1 =  5 :   66   0   2   3   4  77   5   0   8   9   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[ 1],  1 = 22 :   66  22   2   3   4  77   5   0   8   9   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[ 7],  1 =  6 :   66  22   2   3   4  77   5   6   8   9   0  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[10],  3 =  1 :   66  22   2   3   4  77   5   6   8   9   1  11  99   0   0  33   0   0   0   0  55   0
	 HashTable[16],  4 = 88 :   66  22   2   3   4  77   5   6   8   9   1  11  99   0   0  33  88   0   0   0  55   0
	 HashTable[21],  6 =  7 :   66  22   2   3   4  77   5   6   8   9   1  11  99   0   0  33  88   0   0   0  55   7
	 HashTable[14],  6 = 44 :   66  22   2   3   4  77   5   6   8   9   1  11  99   0  44  33  88   0   0   0  55   7
	 HashTable: size 22, lamda 0.80, count 18
	 ---------- OpenAddressing Quadratic ----------
	 HashTable: size 27, lamda 0.80, count  0
	 66  22   2   3   4  77   5   6   8   9   1  11  99   0  44  33  88   0   0   0  55   7
	 HashTable[12],  0 = 66 :    0   0   0   0   0   0   0   0   0   0   0   0  66   0   0   0   0   0   0   0   0   0   0   0   0   0   0
	 HashTable[22],  0 = 22 :    0   0   0   0   0   0   0   0   0   0   0   0  66   0   0   0   0   0   0   0   0   0  22   0   0   0   0
	 HashTable[ 2],  0 =  2 :    0   0   2   0   0   0   0   0   0   0   0   0  66   0   0   0   0   0   0   0   0   0  22   0   0   0   0
	 HashTable[ 3],  0 =  3 :    0   0   2   3   0   0   0   0   0   0   0   0  66   0   0   0   0   0   0   0   0   0  22   0   0   0   0
	 HashTable[ 4],  0 =  4 :    0   0   2   3   4   0   0   0   0   0   0   0  66   0   0   0   0   0   0   0   0   0  22   0   0   0   0
	 HashTable[23],  0 = 77 :    0   0   2   3   4   0   0   0   0   0   0   0  66   0   0   0   0   0   0   0   0   0  22  77   0   0   0
	 HashTable[ 5],  0 =  5 :    0   0   2   3   4   5   0   0   0   0   0   0  66   0   0   0   0   0   0   0   0   0  22  77   0   0   0
	 HashTable[ 6],  0 =  6 :    0   0   2   3   4   5   6   0   0   0   0   0  66   0   0   0   0   0   0   0   0   0  22  77   0   0   0
	 HashTable[ 8],  0 =  8 :    0   0   2   3   4   5   6   0   8   0   0   0  66   0   0   0   0   0   0   0   0   0  22  77   0   0   0
	 HashTable[ 9],  0 =  9 :    0   0   2   3   4   5   6   0   8   9   0   0  66   0   0   0   0   0   0   0   0   0  22  77   0   0   0
	 HashTable[ 1],  0 =  1 :    0   1   2   3   4   5   6   0   8   9   0   0  66   0   0   0   0   0   0   0   0   0  22  77   0   0   0
	 HashTable[11],  0 = 11 :    0   1   2   3   4   5   6   0   8   9   0  11  66   0   0   0   0   0   0   0   0   0  22  77   0   0   0
	 HashTable[18],  0 = 99 :    0   1   2   3   4   5   6   0   8   9   0  11  66   0   0   0   0   0  99   0   0   0  22  77   0   0   0
	 HashTable[ 0],  0 =  0 :    0   1   2   3   4   5   6   0   8   9   0  11  66   0   0   0   0   0  99   0   0   0  22  77   0   0   0
	 HashTable[17],  0 = 44 :    0   1   2   3   4   5   6   0   8   9   0  11  66   0   0   0   0  44  99   0   0   0  22  77   0   0   0
	 HashTable[ 7],  1 = 33 :    0   1   2   3   4   5   6  33   8   9   0  11  66   0   0   0   0  44  99   0   0   0  22  77   0   0   0
	 HashTable[16],  3 = 88 :    0   1   2   3   4   5   6  33   8   9   0  11  66   0   0   0  88  44  99   0   0   0  22  77   0   0   0
	 HashTable[ 0],  0 =  0 :    0   1   2   3   4   5   6  33   8   9   0  11  66   0   0   0  88  44  99   0   0   0  22  77   0   0   0
	 HashTable[ 0],  0 =  0 :    0   1   2   3   4   5   6  33   8   9   0  11  66   0   0   0  88  44  99   0   0   0  22  77   0   0   0
	 HashTable[ 0],  0 =  0 :    0   1   2   3   4   5   6  33   8   9   0  11  66   0   0   0  88  44  99   0   0   0  22  77   0   0   0
	 HashTable[10],  3 = 55 :    0   1   2   3   4   5   6  33   8   9  55  11  66   0   0   0  88  44  99   0   0   0  22  77   0   0   0
	 HashTable[26], 10 =  7 :    0   1   2   3   4   5   6  33   8   9  55  11  66   0   0   0  88  44  99   0   0   0  22  77   0   0   7
	 HashTable: size 27, lamda 0.80, count 22
	 */

	return EXIT_SUCCESS;
}
