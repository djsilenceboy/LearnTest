/*
 ============================================================================
 Name        : CSyntaxTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

/*
#ifndef HP_C
#error HP_C not defined
#endif
*/

static int k = 0;

void init()
{
	k = 1;

	printf("k = %d\n", k);
}

char* foo()
{
	char *p, c;

	c = 'a';

	p = &c;

	printf("*p = %c\n", *p);

	return p;
}

int main(void)
{
	puts("Hello World!!!"); /* prints Hello World!!! */

	int i, j = 3;

	/*
	 i = 0
	 i = 5
	 i = 1
	 i = 5
	 i = 2
	 i = 5
	 */
	for (i = 0; i < j; i++)
	{
		printf("i = %d\n", i);

		int i = 5;

		printf("i = %d\n", i);
	}

	char *p = foo();

	printf("*p = %c\n", *p);

	printf("file name = %s\n", __FILE__);

#line 1 "myfile"

	printf("file name = %s\n", __FILE__);

	printf("k = %d\n", k);

	return EXIT_SUCCESS;
}
