/*
 ============================================================================
 Name        : DefineTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define AA 123
#define BB CC * 10
#define CC 321

int main(void)
{
	printf("AA = %d\n", AA);
	printf("BB = %d\n", BB);
	printf("CC = %d\n", CC);

	return EXIT_SUCCESS;
}
