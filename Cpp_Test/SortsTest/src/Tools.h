#ifndef TOOLS_H
#define TOOLS_H

// a and b are int address.
#define swap(a, b) {int _temp = *(a); *(a) = *(b); *(b) = _temp;}
// a and b are address, and a != b. Otherwise, cannot use this macro!
#define swap2(a, b) {*(a) = *(a) + *(b); *(b) = *(a) - *(b); *(a) = *(a) - *(b);}

void printData(int data[], int size);

#endif
