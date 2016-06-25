/*
 ============================================================================
 Name        : UnionTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

typedef union
{
	int i;
	long l;
	char c;
	float f;
} objType;

void printData(objType *p)
{
	printf("U.i = %d\n", p->i);
	printf("U.l = %ld\n", p->l);
	printf("U.c = %c\n", p->c);
	printf("U.f = %f\n", p->f);
	puts("\n");
}

void test1()
{
	objType ua;

	ua.i = 123;

	printData(&ua);

	ua.l = 65535000l;

	printData(&ua);

	ua.c = 'A';

	printData(&ua);

	ua.f = 123.456;

	printData(&ua);

	/*
	 U.i = 123
	 U.l = 123
	 U.c = {
	 U.f = 0.000000

	 U.i = 65535000
	 U.l = 65535000
	 U.c = 
	 U.f = 0.000000

	 U.i = 65535041
	 U.l = 65535041
	 U.c = A
	 U.f = 0.000000

	 U.i = 1123477881
	 U.l = 1123477881
	 U.c = y
	 U.f = 123.456001
	 */
}

int main(void)
{
	test1();

	return EXIT_SUCCESS;
}
