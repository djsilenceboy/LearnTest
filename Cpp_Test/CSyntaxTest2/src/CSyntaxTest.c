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

/* add "-std=c99" in command line. */


char* foo( void )
{
	char *p, c;

	c = 'a';

	p = &c;

	printf("*p = %c\n", *p);

	return p;
}

int main()
{
	int j = 3;

	for (int i = 0; i < j; i++)
	{
		printf("i = %d\n", i);
	}

	char *p = foo();

	printf("*p = %c\n", *p);
}
