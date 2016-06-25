/*
 ============================================================================
 Name        : ReverseList.c
 Author      : 
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
	int data;
	struct _NODE *next;
} NODE;

void showLinkedList(NODE *head)
{
	int i = 0;
	printf("List = ");

	while (head != NULL)
	{
		printf("%4d ", head->data);

		head = head->next;

		i++;

		if (i % 10 == 0)
		{
			printf("\n");
		}
	}

	printf("\n");
}

NODE* newHead = NULL;

NODE* revert(NODE *head)
{
	NODE *temp = NULL;

	if (head->next == NULL)
	{
		newHead = temp = head;
	}
	else
	{
		temp = revert(head->next);
		temp->next = head;
		head->next = NULL;
		temp = head;
	}

	// showLinkedList(newHead);

	return temp;
}

NODE* generate(int n)
{
	NODE* head = NULL;
	int i;

	for (i = n; i > 0; i--)
	{
		NODE* temp = malloc(sizeof(NODE));

		temp->data = i;
		temp->next = head;
		head = temp;
	}

	return head;
}

int main(void)
{
	NODE* testData = generate(10000);

	showLinkedList(testData);

	revert(testData);

	showLinkedList(newHead);

	return EXIT_SUCCESS;
}

