/*
 ============================================================================
 Name        : PrintTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

void fa(int a, int b)
{
	printf("a = %d, b = %d\n", a, b);
}

int main(void)
{
	printf(" ab c\t de\rf\tg\n");
	printf("h\ti\b\bj k\n");

	unsigned char ch = 130;
	printf("%d\n", ch);
	putchar('\n');

	int i = 3;
	int j = (i++) + (i++) + (i++);
	printf("%d, %d\n", i, j);
	putchar('\n');
	/*
	 * j = 3 + 3 + 3
	 * 6, 9
	 */

	i = 3;
	printf("%d, %d\n", i, i++);
	putchar('\n');
	/*
	 * i++ first.
	 * 4, 3
	 */

	char a, b, c;
	a = 'B';
	b = 'O';
	c = 'Y';
	putchar(a);
	putchar(b);
	putchar(c);
	putchar('\n');
	putchar('\n');

	/*
	 puts("Please input a char: ");
	 c = getchar();
	 putchar(c);
	 putchar('\n');
	 putchar('\n');
	 */

	float f = 123.468;
	printf("%f, %e, %g\n", f, f, f);
	putchar('\n');
	/*
	 123.468002, 1.234680e+002, 123.468
	 */

	char str1[20] = "Hello,";
	char str2[] = "World!";

	printf("size of str1 = %d\n", sizeof(str1));
	printf("length of str1 = %d\n", strlen(str1));
	printf("size of str2 = %d\n", sizeof(str2));
	printf("length of str2 = %d\n", strlen(str2));
	strcat(str1, str2);
	printf("%s\n", str1);
	putchar('\n');
	/*
	 size of str1 = 20
	 length of str1 = 6
	 size of str2 = 7
	 length of str2 = 6
	 */

	printf("str1 strlwr = %s\n", strlwr(str1));
	printf("str2 strlwr = %s\n", strlwr(str2));
	putchar('\n');
	/*
	 str1 strlwr = hello,world!
	 str2 strlwr = world!
	 */

	printf("str1 strupr = %s\n", strupr(str1));
	printf("str2 strupr = %s\n", strupr(str2));
	putchar('\n');
	/*
	 str1 strupr = HELLO,WORLD!
	 str2 strupr = WORLD!
	 */

	printf("i = %d\n", i);
	fa(i, ++i);
	printf("i = %d\n", i);
	fa(i, i--);
	putchar('\n');
	/*
	 i = 4
	 a = 5, b = 5
	 i = 5
	 a = 4, b = 5
	 */

	extern extA, extB;

	printf("extA = %d\n", extA);
	printf("extB = %d\n", extB);
	putchar('\n');

	return EXIT_SUCCESS;
}

int extA = 11;
int extB = 22;

