#include "Tools.h"

void printData(int data[], int size)
{
	int i;

	for (i = 0; i < size; i++)
	{
		printf("%4d", data[i]);
	}
	printf("\n");
}
