/*
 ============================================================================
 Name        : RandomTest.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

static unsigned long Seed = 1;
static unsigned long Seed2 = 1;

#define A 48271L
#define M 2147483647L
#define Q (M / A)
#define R (M % A)

double random1()
{
	long TmpSeed;

	TmpSeed = A * (Seed % Q) - R * (Seed / Q);

	if (TmpSeed >= 0)
	{
		Seed = TmpSeed;
	}
	else
	{
		Seed = TmpSeed + M;
	}

	double random = (double) Seed / M;

	printf("TmpSeed, Seed, random = %12ld, %12ld, %8f\n", TmpSeed, Seed, random);

	return random;
}

void init(unsigned long InitVal)
{
	Seed = InitVal;

	printf("Seed = %ld\n", Seed);
}

double random2()
{
	Seed2 = (A * Seed2) % M;

	double random = (double) Seed2 / M;

	printf("Seed2, random = %12ld, %8f\n", Seed2, random);

	return random;
}

void init2(unsigned long InitVal)
{
	Seed2 = InitVal;

	printf("Seed2 = %ld\n", Seed2);
}

int main(void)
{
	int i;

	printf("========================================\n");

	init(Seed);

	for (i = 0; i < 20; i++)
	{
		random1();
	}

	printf("========================================\n");

	init2(Seed2);

	for (i = 0; i < 20; i++)
	{
		random2();
	}

	printf("========================================\n");

	/*
	 ========================================
	 Seed = 1
	 TmpSeed, Seed, random =        48271,        48271, 0.000022
	 TmpSeed, Seed, random =    182605794,    182605794, 0.085032
	 TmpSeed, Seed, random =   1291394886,   1291394886, 0.601353
	 TmpSeed, Seed, random =   1914720637,   1914720637, 0.891611
	 TmpSeed, Seed, random =    -68814606,   2078669041, 0.967956
	 TmpSeed, Seed, random =    407355683,    407355683, 0.189690
	 TmpSeed, Seed, random =   1105902161,   1105902161, 0.514976
	 TmpSeed, Seed, random =    854716505,    854716505, 0.398008
	 TmpSeed, Seed, random =    564586691,    564586691, 0.262906
	 TmpSeed, Seed, random =   1596680831,   1596680831, 0.743512
	 TmpSeed, Seed, random =    192302371,    192302371, 0.089548
	 TmpSeed, Seed, random =   1203428207,   1203428207, 0.560390
	 TmpSeed, Seed, random =   1250328747,   1250328747, 0.582230
	 TmpSeed, Seed, random =   1738531149,   1738531149, 0.809567
	 TmpSeed, Seed, random =   1271135913,   1271135913, 0.591919
	 TmpSeed, Seed, random =   1098894339,   1098894339, 0.511713
	 TmpSeed, Seed, random =   1882556969,   1882556969, 0.876634
	 TmpSeed, Seed, random =    -10555853,   2136927794, 0.995085
	 TmpSeed, Seed, random =   1559527823,   1559527823, 0.726212
	 TmpSeed, Seed, random =    -71701552,   2075782095, 0.966611
	 ========================================
	 Seed2 = 1
	 Seed2, random =        48271, 0.000022
	 Seed2, random =    182605794, 0.085032
	 Seed2, random =   1291390782, 0.601351
	 Seed2, random =   1716587427, 0.799348
	 Seed2, random =    735130638, 0.342322
	 Seed2, random =    471227346, 0.219432
	 Seed2, random =    468419150, 0.218125
	 Seed2, random =    205459859, 0.095675
	 Seed2, random =    673367325, 0.313561
	 Seed2, random =   1949132596, 0.907636
	 Seed2, random =   1025955340, 0.477748
	 Seed2, random =    769810613, 0.358471
	 Seed2, random =   1618538780, 0.753691
	 Seed2, random =    882851493, 0.411110
	 Seed2, random =   1458907691, 0.679357
	 Seed2, random =    501883398, 0.233708
	 Seed2, random =    650471771, 0.302900
	 Seed2, random =    564440534, 0.262838
	 Seed2, random =    983974539, 0.458199
	 Seed2, random =   1539129254, 0.716713
	 ========================================
	 */

	return EXIT_SUCCESS;
}
