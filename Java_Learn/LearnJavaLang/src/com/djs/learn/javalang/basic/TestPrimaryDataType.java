
package com.djs.learn.javalang.basic;

public class TestPrimaryDataType
{
	// Test default value for class level.
	byte defaultByteVal;
	short defaultShortVal;
	char defaultCharVal;
	int defaultIntVal;
	long defaultLongVal;
	int[] defaultIntArrayVale;

	public void testDefaultValue(){
		System.out.println("Test: Default value (Class level)");

		System.out.println("byte = " + defaultByteVal);
		System.out.println("short = " + defaultShortVal);
		System.out.println("char = " + defaultCharVal);
		System.out.println("int = " + defaultIntVal);
		System.out.println("long = " + defaultLongVal);
		System.out.println("int [] = " + defaultIntArrayVale);
	}

	public void testCastValue(){
		System.out.println("Test: Cast");

		// byte/short/char are promoted to int for arithmetic operation.

		{
			byte b1 = 1, b2 = 2;
			byte b3 = (byte)(b1 + b2);
			short s1 = (short)(b1 + b2);
			int i1 = b1 + b2;
			float f1 = b1 + b2;
			double d1 = b1 + b2;
		}

		{
			short s1 = 1, s2 = 2;
			short s3 = (short)(s1 + s2);
			byte b1 = (byte)(s1 + s2);
			int i1 = s1 + s2;
			float f1 = s1 + s2;
			double d1 = s1 + s2;
		}

		{
			char c1 = 1, c2 = 2;
			char c3 = (char)(c1 + c2);
			byte b1 = (byte)(c1 + c2);
			int i1 = c1 + c2;
			float f1 = c1 + c2;
			double d1 = c1 + c2;
		}

		{
			int i1 = 1, i2 = 2;
			int i3 = i1 + i2;
			byte b1 = (byte)(i1 + i2);
			short s1 = (short)(i1 + i2);
			long l1 = i1 + i2;
			float f1 = i1 + i2;
			double d1 = i1 + i2;
		}

		{
			float f1 = 1, f2 = 2;
			float f3 = f1 + f2;
			byte b1 = (byte)(f1 + f2);
			short s1 = (short)(f1 + f2);
			int i1 = (int)(f1 + f2);
			long l1 = (long)(f1 + f2);
			double d1 = f1 + f2;
		}

		{
			// When using compound assignment, no need to cast int to byte/short.

			byte b1 = 1;
			byte b2 = 2;
			b2 += b1;

			short s1 = 1;
			short s2 = 2;
			s2 += s1;
		}

		{
			byte b1 = (byte)256;
			short s1 = (short)65536;
			int i1 = (int)9123456789L;
			long l1 = 1;
			long l2 = 9123456789L;

			System.out.println("Byte cast: 256 -> " + b1);
			System.out.println("Short cast: 65536 -> " + s1);
			System.out.println("Int cast: 9123456789 -> " + i1);
			System.out.println("Long: " + l2);
		}

	}

	public void testBoolean(){
		System.out.println("Test: Boolean");

		System.out.println("(true == true) = " + (true == true));
		// System.out.println( "(true > true) = " + (true > true) );
		// System.out.println( "(true > true) = " + (Boolean.TRUE > Boolean.TRUE) );
	}

	public void testByte(){
		System.out.println("Test: Byte");

		byte bmin = Byte.MIN_VALUE;
		byte bmax = Byte.MAX_VALUE;

		System.out.println("Byte: Min: " + bmin);
		System.out.println("Byte: Mix: " + bmax);
	}

	public void testAvoidFloat(){
		System.out.println("Test: Avoid float");

		// Not 0.61, but 0.6100000000000001!
		System.out.println(1.03 - .42);
		// Not 01, but 0.09999999999999998!
		System.out.println(1.00 - 9 * .10);
	}

	public void testWideningPrimitiveConversion(){
		/*
		5.1.2. Widening Primitive Conversion

		19 specific conversions on primitive types are called the widening primitive conversions:

		byte to short, int, long, float, or double

		short to int, long, float, or double

		char to int, long, float, or double

		int to long, float, or double

		long to float or double

		float to double
		 */
		System.out.println("Test: Widening Primitive Conversion");

		{
			int big = 1234567890;
			float approx = big;
			System.out.println("int -> float diff = " + (big - (int)approx));
		}

		{
			long big = 1234567890123L;
			float approx = big;
			System.out.println("long -> float diff = " + (big - (long)approx));
		}
	}

	public void testNarrowingPrimitiveConversion(){
		/*
		5.1.3. Narrowing Primitive Conversion

		22 specific conversions on primitive types are called the narrowing primitive conversions:

		short to byte or char

		char to byte or short

		int to byte, short, or char

		long to byte, short, char, or int

		float to byte, short, char, int, or long

		double to byte, short, char, int, long, or float
		*/
		System.out.println("Test: Narrowing Primitive Conversion");

		float fmin = Float.NEGATIVE_INFINITY;
		float fmax = Float.POSITIVE_INFINITY;
		System.out.println("float: " + fmin + " / " + fmax);
		System.out.println("float -> long: " + (long)fmin + " / " + (long)fmax);
		System.out.println("float -> int: " + (int)fmin + " / " + (int)fmax);
		System.out.println("float -> short: " + (short)fmin + " / " + (short)fmax);
		System.out.println("float -> char: " + (int)(char)fmin + " / " + (int)(char)fmax);
		System.out.println("float -> byte: " + (byte)fmin + " / " + (byte)fmax);

		// A narrowing of int to short loses high bits:
		System.out.println("(short)0x12345678==0x" + Integer.toHexString((short)0x12345678));
		// An int value too big for byte changes sign and magnitude:
		System.out.println("(byte)255==" + (byte)255);
		// A float value too big to fit gives largest int value:
		System.out.println("(int)1e20f==" + (int)1e20f);
		// A NaN converted to int yields zero:
		System.out.println("(int)NaN==" + (int)Float.NaN);
		// A double value too large for float yields infinity:
		System.out.println("(float)-1e100==" + (float)-1e100);
		// A double value too small for float underflows to zero:
		System.out.println("(float)1e-50==" + (float)1e-50);
	}

	public void testAbsMin(){
		System.out.println("Test: Abs min");

		// Integer.MIN_VALUE:
		//   int java.lang.Integer.MIN_VALUE = -2147483648 [0x80000000]
		//   A constant holding the minimum value an int can have, power( -2, 31 ).

		// -2147483648
		System.out.println(Integer.MIN_VALUE);

		// After abs, it is still negative!
		// Because of two's complement arithmetic, it will be negative and then plus 1!
		// -2147483648
		System.out.println(Math.abs(Integer.MIN_VALUE));

		// For operand which is not power of 2, it gets negative!
		// -2
		System.out.println(Math.abs(Integer.MIN_VALUE) % 3);

		// For operand which is power of 2, it gets 0.
		// 0
		System.out.println(Math.abs(Integer.MIN_VALUE) % 8);

		// Now, let's add L post-fix.

		// It gets positive.
		// 2147483648
		System.out.println(0x80000000L);

		// After abs, it is still positive.
		// 2147483648
		System.out.println(Math.abs(0x80000000L));

		// For operand which is not power of 2, it gets positive.
		// 2
		System.out.println(Math.abs(0x80000000L) % 3);

		// For operand which is power of 2, it gets 0.
		System.out.println(Math.abs(0x80000000L) % 8);
	}

	public static void main(String[] args){
		TestPrimaryDataType test = new TestPrimaryDataType();

		System.out.println("============================================================");
		test.testDefaultValue();

		System.out.println("============================================================");
		test.testCastValue();

		System.out.println("============================================================");
		test.testBoolean();

		System.out.println("============================================================");
		test.testByte();

		System.out.println("============================================================");
		test.testWideningPrimitiveConversion();

		System.out.println("============================================================");
		test.testNarrowingPrimitiveConversion();

		System.out.println("============================================================");
		test.testAvoidFloat();

		System.out.println("============================================================");
		test.testAbsMin();

		System.out.println("============================================================");
	}
}
