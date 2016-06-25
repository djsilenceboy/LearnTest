/*
 ============================================================================
 Name        : BinaryTreeTest.c
 Author      : djs
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#include <string.h>

typedef struct _NODE
{
	char ch;
	struct _NODE *llink;
	struct _NODE *rlink;
} NODE;

void showTree(NODE *head, int level)
{
	if (head == NULL)
	{
		return;
	}

	printf("%d%c\n", level, head->ch);

	showTree(head->llink, level + 1);
	showTree(head->rlink, level + 1);
}

NODE* constructTree(char *preOrderData, char *inOrderData, int length)
{
	NODE *head = (NODE*) malloc(sizeof(NODE));
	int i;

	printf("%s\n", preOrderData);
	printf("%s\n", inOrderData);
	printf("%d\n", length);
	printf("\n");

	head->ch = preOrderData[0];

	if (length == 1)
	{
		head->llink = NULL;
		head->rlink = NULL;
	}
	else
	{
		for (i = 0; i < length; i++)
		{
			if (inOrderData[i] == head->ch)
			{
				break;
			}
		}

		head->llink = constructTree(preOrderData + 1, inOrderData, i);
		head->rlink = constructTree(preOrderData + 1 + i, inOrderData + 1 + i, length - 1 - i);
	}

	return head;
}

int main(void)
{
	char *preOrderData = "ABDECFG";
	char *inOrderData = "DBEAFCG";

	NODE *head = constructTree(preOrderData, inOrderData, strlen(preOrderData));

	showTree(head, 0);

	/*
	 ABDECFG
	 DBEAFCG
	 7

	 BDECFG
	 DBEAFCG
	 3

	 DECFG
	 DBEAFCG
	 1

	 ECFG
	 EAFCG
	 1

	 CFG
	 FCG
	 3

	 FG
	 FCG
	 1

	 G
	 G
	 1

	 0A
	 1B
	 2D
	 2E
	 1C
	 2F
	 2G
	 */

	return EXIT_SUCCESS;
}
