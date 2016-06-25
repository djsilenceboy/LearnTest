/*
 ============================================================================
 Name        : StructureTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

struct objType
{
	char *name;
	int count;
} typeA[] =
{
{ "Cat", 1 },
  { "Dog" },
  { "Bird", 5 } };

struct objType typeB[] =
{
{ "Cat", 11 },
  { "Dog", 13 },
  { "Bird", 15 } };

struct objType typeC[] =
{ "Cat", 21, "Dog", 23, "Bird", 25 };

void test1()
{
	int n = 3;
	int i;

	for (i = 0; i < n; i++)
	{
		printf("%d: %s, %d\n", i, typeA[i].name, typeA[i].count);
		printf("%d: %s, %d\n", i, typeB[i].name, typeB[i].count);
		printf("%d: %s, %d\n", i, typeC[i].name, typeC[i].count);
	}

	struct objType *p;

	p = (struct objType *) &typeB[1].count;

	printf("%d\n", *p);
	printf("%d\n", p->name);
	printf("%d\n", p->count);

	/*
	 0: Cat, 1
	 0: Cat, 11
	 0: Cat, 21
	 1: Dog, 0
	 1: Dog, 13
	 1: Dog, 23
	 2: Bird, 5
	 2: Bird, 15
	 2: Bird, 25
	 13
	 13
	 4206700
	 */
}

int main(void)
{
	test1();

	return EXIT_SUCCESS;
}
