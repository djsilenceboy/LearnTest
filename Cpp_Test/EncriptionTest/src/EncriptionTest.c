/*
 ============================================================================
 Name        : EncriptionTest.c
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
	struct _NODE *prev;
	struct _NODE *next;
} NODE;

void showLinkedList(NODE *head)
{
	NODE *pos = head;

	printf("LinkList = ");

	do
	{
		printf("%c", pos->ch);

		pos = pos->next;
	} while (pos != head);

	printf("\n");
}

char* encript(char *source, int keyInterval)
{
	int length = strlen(source);
	char *encoded = (char*) malloc(length + 1);
	NODE *head = NULL, *pos, *tempPos;
	int i, j;

	for (i = 0; i < length; i++)
	{
		NODE *temp = (NODE*) malloc(sizeof(NODE));

		temp->ch = source[i];

		if (head == NULL)
		{
			head = pos = temp;
			pos->next = head;
			pos->prev = head;
		}
		else
		{
			temp->next = pos->next;
			temp->prev = pos;
			pos->next = temp;
			pos = temp;
			head->prev = pos;
		}
	}

	showLinkedList(head);

	pos = head;

	for (i = 0; i < length; i++)
	{
		encoded[i] = pos->ch;

		if (i == length - 1)
		{
			free(pos);
			break;
		}

		pos->prev->next = pos->next;
		pos->next->prev = pos->prev;
		tempPos = pos;
		pos = pos->next;

		free(tempPos);

		for (j = 0; j < keyInterval; j++)
		{
			pos = pos->next;
		}

		showLinkedList(pos);
	}

	encoded[length] = '\0';

	return encoded;
}

char* decript(char *encoded, int keyInterval)
{
	int length = strlen(encoded);
	char *source = (char*) malloc(length + 1);
	NODE *pos = NULL, *tempPos;
	int i, j;

	for (i = length - 1; i >= 0; i--)
	{
		NODE *temp = (NODE*) malloc(sizeof(NODE));

		temp->ch = encoded[i];

		// If first char.
		if (pos == NULL)
		{
			pos = temp;
			pos->next = pos;
			pos->prev = pos;
		}
		else
		{
			temp->next = pos;
			temp->prev = pos->prev;

			pos->prev->next = temp;
			pos->prev = temp;

			pos = temp;

			if (i > 0)
			{
				for (j = 0; j < keyInterval; j++)
				{
					pos = pos->prev;
				}
			}
		}

		showLinkedList(pos);
	}

	for (i = 0; i < length; i++)
	{
		source[i] = pos->ch;

		tempPos = pos;
		pos = pos->next;
		free(tempPos);
	}

	source[length] = '\0';

	return source;
}

int main(void)
{
	char *source = "Hello world!";
	int keyInterval = 3;
	char *encoded = NULL;

	printf("Key interval = %d\n", keyInterval);
	printf("Source   = %s\n", source);

	encoded = encript(source, keyInterval);

	printf("Encoded  = %s\n", encoded);

	source = decript(encoded, keyInterval);

	printf("Source   = %s\n", source);

	return EXIT_SUCCESS;

	/*
	 Key interval = 3
	 Source   = Hello world!
	 LinkList = Hello world!
	 LinkList = o world!ell
	 LinkList = rld!ell wo
	 LinkList = ell wold!
	 LinkList = wold!ll
	 LinkList = !ll old
	 LinkList = oldll
	 LinkList = l ldl
	 LinkList = l ld
	 LinkList =  ld
	 LinkList = dl
	 LinkList = l
	 Encoded  = Horew!oll dl
	 LinkList = l
	 LinkList = ld
	 LinkList =  ld
	 LinkList =  ldl
	 LinkList = ldll
	 LinkList = ll old
	 LinkList = old!ll
	 LinkList = ll wold!
	 LinkList = ld!ell wo
	 LinkList =  world!ell
	 LinkList = ello world!
	 LinkList = Hello world!
	 Source   = Hello world!
	 */
}
