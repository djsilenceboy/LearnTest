/*
 ============================================================================
 Name        : BitCalcTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void test1()
{
	int i = 0xFFFFFFFF;
	unsigned int ui = 0xFFFFFFFF;

	printf("int = %08X\n", i);
	printf("unsigned int = %08X\n", ui);

	i = i >> 4;
	ui = ui >> 4;

	printf("int >> 4 = %08X\n", i);
	printf("unsigned int >> 4 = %08X\n", ui);

	/*
	 int = FFFFFFFF
	 unsigned int = FFFFFFFF
	 int >> 4 = FFFFFFFF
	 unsigned int >> 4 = 0FFFFFFF
	 */
}

int main(void)
{
	test1();

	return EXIT_SUCCESS;
}
