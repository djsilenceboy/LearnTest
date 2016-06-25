/*
 ============================================================================
 Name        : FunctionTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "MyTools.h"

void printVar()
{
	int ia;
	static int ib;

	printf("ia = %d\n", ia);
	printf("ib = %d\n", ib);

	char cha;
	static char chb;

	printf("cha = %c, %d\n", cha, cha);
	printf("chb = %c, %d\n", chb, chb);

	char stra[2];
	static char strb[2];

	printf("stra = %s, %d\n", stra, strlen(stra));
	printf("strb = %s, %d\n", strb, strlen(strb));

	/*
	 ia = 1983469327
	 ib = 0
	 cha = v, 118
	 chb =
	 */
}

int main(void)
{
	int i = 3;

	printf("i = %d\n", i);
	fa(i, ++i);
	printf("i = %d\n", i);
	fa(i, i--);
	putchar('\n');
	/*
	 i = 3
	 a = 4, b = 4
	 i = 4
	 a = 3, b = 4
	 */

	printVar();
	putchar('\n');

	extern extA;

	printf("extA = %d\n", extA);
	putchar('\n');

	return EXIT_SUCCESS;
}

int extA = 321;
