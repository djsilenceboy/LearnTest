/*
 ============================================================================
 Name        : PointerTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define FORMAT "%-12s = %8d\n"

void printArray(int (*pdata)[3], int size)
{
	int i, j;

	for (i = 0; i < size; i++)
	{
		for (j = 0; j < size; j++)
		{
			printf("[%d][%d] = %d, %d\n", i, j, (*(pdata + i))[j], *(*(pdata + i) + j));
		}
	}
}

void test1()
{
	int a[3][3] =
	{
	{ 11, 12, 13 },
	  { 21, 22, 23 },
	  { 31, 32, 33 } };

	printf(FORMAT, "a", a);
	printf(FORMAT, "*a", *a);
	printf(FORMAT, "a[0]", a[0]);
	printf(FORMAT, "&a[0]", &a[0]);
	printf(FORMAT, "&a[0][0]", &a[0][0]);
	printf(FORMAT, "a+0", a + 0);
	printf(FORMAT, "*(a+0)", *(a + 0));
	printf(FORMAT, "*(a+0)+0", *(a + 0) + 0);
	printf(FORMAT, "a[0][0]", a[0][0]);
	printf(FORMAT, "*(*(a+0)+0)", *(*(a + 0) + 0));

	printf(FORMAT, "a+1", a + 1);
	printf(FORMAT, "*(a+1)", *(a + 1));
	printf(FORMAT, "*(a+1)+0", *(a + 1) + 0);

	printf(FORMAT, "a[1][0]", a[1][0]);
	printf(FORMAT, "*(*(a+1)+0)", *(*(a + 1) + 0));

	printArray(a, 3);

	int b[3] =
	{ 11, 12, 13 };

	printf(FORMAT, "b", b);
	printf(FORMAT, "*b", *b);
	printf(FORMAT, "b[0]", b[0]);
	printf(FORMAT, "&b[0]", &b[0]);
	printf(FORMAT, "b+0", b + 0);
	printf(FORMAT, "*(b+0)", *(b + 0));

	char * str = "Hello";

	printf("%s\n", str);
	printf("%d\n", str);
	printf("%c\n", *str);
	printf("%d\n", *str);

	/*
	 a            =  2686696
	 *a           =  2686696
	 a[0]         =  2686696
	 &a[0]        =  2686696
	 &a[0][0]     =  2686696
	 a+0          =  2686696
	 *(a+0)       =  2686696
	 *(a+0)+0     =  2686696
	 a[0][0]      =       11
	 *(*(a+0)+0)  =       11
	 a+1          =  2686708
	 *(a+1)       =  2686708
	 *(a+1)+0     =  2686708
	 a[1][0]      =       21
	 *(*(a+1)+0)  =       21

	 [0][0] = 11, 11
	 [0][1] = 12, 12
	 [0][2] = 13, 13
	 [1][0] = 21, 21
	 [1][1] = 22, 22
	 [1][2] = 23, 23
	 [2][0] = 31, 31
	 [2][1] = 32, 32
	 [2][2] = 33, 33

	 b            =  2686684
	 *b           =       11
	 b[0]         =       11
	 &b[0]        =  2686684
	 b+0          =  2686684
	 *(b+0)       =       11

	 Hello
	 4206831
	 H
	 72
	 */
}

void copyStr(char *pa, char *pb)
{
	while (*pa != '\0')
	{
		*pb++ = *pa++;
	}

	*pb = '\0';
}

void copyStr2(char **pa, char **pb)
{
	printf(FORMAT, "pa", pa);
	printf(FORMAT, "*pa", *pa);
	printf(FORMAT, "**pa", **pa);

	while (**pa != '\0')
	{
		printf("%c", **pa);
		*(*pb)++ = *(*pa)++;
	}
	puts("\n");

	**pb = '\0';
}

void test2()
{
	char *pa = "Hello,there!";
	char pb[20] = "\0";

	printf(FORMAT, "pa", pa);
	printf("pa = %s\n", pa);
	printf(FORMAT, "pb", pb);
	printf("pb = %s\n", pb);

	copyStr(pa, pb);

	printf(FORMAT, "pa", pa);
	printf(FORMAT, "pb", pb);
	printf("pb = %s\n", pb);

	/*
	 char *pc = malloc(sizeof(char) * 30);
	 *pc = '\0';

	 printf(FORMAT, "pc", pc);
	 printf("pc = %s\n", pc);

	 copyStr2(&pa, &pc);

	 printf(FORMAT, "pa", pa);
	 printf(FORMAT, "pc", pc);

	 printf("pc = %s\n", pc);
	 */

	/*
	 pa           =  4210980
	 pa = Hello,there!
	 pb           =  2686696
	 pb =
	 pa           =  4210980
	 pb           =  2686696
	 pb = Hello,there!
	 */
}

int swap1(int a, int b)
{
	if (a >= b)
	{
		return a;
	}
	else
	{
		return b;
	}
}

int swap2(int a, int b)
{
	if (a < b)
	{
		return a;
	}
	else
	{
		return b;
	}
}

int swapx(int a, int b, int (*pf)(int, int))
{
	return (*pf)(a, b);
}

void test3()
{
	int a = 5;
	int b = 3;
	int (*pf)(int, int);

	printf("a, b = %d, %d\n", a, b);

	pf = swap1;

	printf("swap1 = %d\n", (*pf)(a, b));

	pf = swap2;

	printf("swap2 = %d\n", (*pf)(a, b));

	printf("swap1 = %d\n", swapx(a, b, swap1));
	printf("swap2 = %d\n", swapx(a, b, swap2));

	/*
	 a, b = 5, 3
	 swap1 = 5
	 swap2 = 3
	 swap1 = 5
	 swap2 = 3
	 */
}

void test4()
{
	int n = 5;
	int data[] =
	{ 1, 3, 5, 7, 9 };
	int* num[n];
	int* *p;
	int i;

	for (i = 0; i < n; i++)
	{
		num[i] = &data[i];
	}

	p = num;

	for (i = 0; i < n; i++)
	{
		printf("%d: data = %d; num = %6X\n", i, data[i], num[i]);
		printf("%d: *p = %6X; **p = %d\n", i, *p, **p);
		p++;
	}

	/*
	 0: data = 1; num = 28FED8
	 0: *p = 28FED8; **p = 1
	 1: data = 3; num = 28FEDC
	 1: *p = 28FEDC; **p = 3
	 2: data = 5; num = 28FEE0
	 2: *p = 28FEE0; **p = 5
	 3: data = 7; num = 28FEE4
	 3: *p = 28FEE4; **p = 7
	 4: data = 9; num = 28FEE8
	 4: *p = 28FEE8; **p = 9
	 */
}

void test5()
{
	int a = 5;
	int b = 8;

	int *pb = &a;
	int *pa = pb;

	printf(FORMAT, "*pa", *pa);
	printf(FORMAT, "pa", pa);
	printf(FORMAT, "&pa", &pa);
	printf(FORMAT, "*pb", *pb);
	printf(FORMAT, "pb", pb);
	printf(FORMAT, "&pb", &pb);

	pb = &b;

	printf("--------------------\n");
	printf(FORMAT, "*pa", *pa);
	printf(FORMAT, "pa", pa);
	printf(FORMAT, "&pa", &pa);
	printf(FORMAT, "*pb", *pb);
	printf(FORMAT, "pb", pb);
	printf(FORMAT, "&pb", &pb);

	printf("--------------------\n");

	int *pc = &a;
	pb = pc;
	pa = pb;

	printf(FORMAT, "*pa", *pa);
	printf(FORMAT, "pa", pa);
	printf(FORMAT, "&pa", &pa);
	printf(FORMAT, "*pb", *pb);
	printf(FORMAT, "pb", pb);
	printf(FORMAT, "&pb", &pb);
	printf(FORMAT, "*pc", *pc);
	printf(FORMAT, "pc", pc);
	printf(FORMAT, "&pc", &pc);

	/*
	 *pa          =        5
	 pa           =  2686716
	 &pa          =  2686704
	 *pb          =        5
	 pb           =  2686716
	 &pb          =  2686708
	 --------------------
	 *pa          =        5
	 pa           =  2686716
	 &pa          =  2686704
	 *pb          =        8
	 pb           =  2686712
	 &pb          =  2686708
	 --------------------
	 *pa          =        5
	 pa           =  2686716
	 &pa          =  2686704
	 *pb          =        5
	 pb           =  2686716
	 &pb          =  2686708
	 *pc          =        5
	 pc           =  2686716
	 &pc          =  2686700
	 */
}

int main(void)
{
	test1();
	printf("========================================\n");
	test2();
	printf("========================================\n");
	test3();
	printf("========================================\n");
	test4();
	printf("========================================\n");
	test5();

	return EXIT_SUCCESS;
}
