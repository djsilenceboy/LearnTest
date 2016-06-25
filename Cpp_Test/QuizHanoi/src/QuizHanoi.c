/*
 ============================================================================
 Name        : QuizHanoi.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int steps;

void hanoi(int n, char towerA, char towerB, char towerC)
{
	if (n == 1)
	{
		printf("%4d: %4d: %c -> %c\n", steps++, n, towerA, towerC);
	}
	else
	{
		hanoi(n - 1, towerA, towerC, towerB);

		printf("%4d: %4d: %c -> %c\n", steps++, n, towerA, towerC);

		hanoi(n - 1, towerB, towerA, towerC);
	}
}

int main(void)
{
	printf("--------------------\n");
	printf("Step: Disk: %4d\n", 3);
	steps = 1;
	hanoi(3, 'A', 'B', 'C');

	printf("--------------------\n");
	printf("Step: Disk: %4d\n", 4);
	steps = 1;
	hanoi(4, 'A', 'B', 'C');

	printf("--------------------\n");
	printf("Step: Disk: %4d\n", 5);
	steps = 1;
	hanoi(5, 'A', 'B', 'C');

	printf("--------------------\n");
	printf("Step: Disk: %2d\n", 6);
	steps = 1;
	hanoi(6, 'A', 'B', 'C');

	/*
	 --------------------
	 Step: Disk:    3
	 1:    1: A -> C
	 2:    2: A -> B
	 3:    1: C -> B
	 4:    3: A -> C
	 5:    1: B -> A
	 6:    2: B -> C
	 7:    1: A -> C
	 --------------------
	 Step: Disk:    4
	 1:    1: A -> B
	 2:    2: A -> C
	 3:    1: B -> C
	 4:    3: A -> B
	 5:    1: C -> A
	 6:    2: C -> B
	 7:    1: A -> B
	 8:    4: A -> C
	 9:    1: B -> C
	 10:    2: B -> A
	 11:    1: C -> A
	 12:    3: B -> C
	 13:    1: A -> B
	 14:    2: A -> C
	 15:    1: B -> C
	 --------------------
	 Step: Disk:    5
	 1:    1: A -> C
	 2:    2: A -> B
	 3:    1: C -> B
	 4:    3: A -> C
	 5:    1: B -> A
	 6:    2: B -> C
	 7:    1: A -> C
	 8:    4: A -> B
	 9:    1: C -> B
	 10:    2: C -> A
	 11:    1: B -> A
	 12:    3: C -> B
	 13:    1: A -> C
	 14:    2: A -> B
	 15:    1: C -> B
	 16:    5: A -> C
	 17:    1: B -> A
	 18:    2: B -> C
	 19:    1: A -> C
	 20:    3: B -> A
	 21:    1: C -> B
	 22:    2: C -> A
	 23:    1: B -> A
	 24:    4: B -> C
	 25:    1: A -> C
	 26:    2: A -> B
	 27:    1: C -> B
	 28:    3: A -> C
	 29:    1: B -> A
	 30:    2: B -> C
	 31:    1: A -> C
	 --------------------
	 Step: Disk:  6
	 1:    1: A -> B
	 2:    2: A -> C
	 3:    1: B -> C
	 4:    3: A -> B
	 5:    1: C -> A
	 6:    2: C -> B
	 7:    1: A -> B
	 8:    4: A -> C
	 9:    1: B -> C
	 10:    2: B -> A
	 11:    1: C -> A
	 12:    3: B -> C
	 13:    1: A -> B
	 14:    2: A -> C
	 15:    1: B -> C
	 16:    5: A -> B
	 17:    1: C -> A
	 18:    2: C -> B
	 19:    1: A -> B
	 20:    3: C -> A
	 21:    1: B -> C
	 22:    2: B -> A
	 23:    1: C -> A
	 24:    4: C -> B
	 25:    1: A -> B
	 26:    2: A -> C
	 27:    1: B -> C
	 28:    3: A -> B
	 29:    1: C -> A
	 30:    2: C -> B
	 31:    1: A -> B
	 32:    6: A -> C
	 33:    1: B -> C
	 34:    2: B -> A
	 35:    1: C -> A
	 36:    3: B -> C
	 37:    1: A -> B
	 38:    2: A -> C
	 39:    1: B -> C
	 40:    4: B -> A
	 41:    1: C -> A
	 42:    2: C -> B
	 43:    1: A -> B
	 44:    3: C -> A
	 45:    1: B -> C
	 46:    2: B -> A
	 47:    1: C -> A
	 48:    5: B -> C
	 49:    1: A -> B
	 50:    2: A -> C
	 51:    1: B -> C
	 52:    3: A -> B
	 53:    1: C -> A
	 54:    2: C -> B
	 55:    1: A -> B
	 56:    4: A -> C
	 57:    1: B -> C
	 58:    2: B -> A
	 59:    1: C -> A
	 60:    3: B -> C
	 61:    1: A -> B
	 62:    2: A -> C
	 63:    1: B -> C
	 */

	return EXIT_SUCCESS;
}

