
package dj.test.javalang.basic;

import dj.test.common.Utils;

public class TestOperator
{
	public void test1(){
		byte a = 0x3C;
		byte b = 0x0D;

		System.out.println("a     = " + Utils.byteToBinString(a));
		System.out.println("b     = " + Utils.byteToBinString(b));
		System.out.println("a & b = " + Utils.byteToBinString((byte)(a & b)));
		System.out.println("a | b = " + Utils.byteToBinString((byte)(a | b)));
		System.out.println("a ^ b = " + Utils.byteToBinString((byte)(a ^ b)));
		System.out.println("~a    = " + Utils.byteToBinString((byte)~a));
	}

	public void test2(){
		byte a = (byte)0xC3;

		System.out.println("a       = " + Utils.byteToBinString(a));
		System.out.println("a << 2  = " + Utils.byteToBinString((byte)(a << 2)));
		System.out.println("a >> 2  = " + Utils.byteToBinString((byte)(a >> 2)));
		System.out.println("a >>> 2 = " + Utils.byteToBinString((byte)(a >>> 2)));

		byte b = (byte)0x42;

		System.out.println("b       = " + Utils.byteToBinString(b));
		System.out.println("b << 2  = " + Utils.byteToBinString((byte)(b << 2)));
		System.out.println("b >> 2  = " + Utils.byteToBinString((byte)(b >> 2)));
		System.out.println("b >>> 2 = " + Utils.byteToBinString((byte)(b >>> 2)));
	}

	public void test3(){
		int a = 0xC0000003;

		System.out.println("a       = " + Utils.intToBinString(a));
		System.out.println("a << 2  = " + Utils.intToBinString(a << 2));
		System.out.println("a >> 2  = " + Utils.intToBinString(a >> 2));
		System.out.println("a >>> 2 = " + Utils.intToBinString(a >>> 2));

		int b = 0x40000002;

		System.out.println("b       = " + Utils.intToBinString(b));
		System.out.println("b << 2  = " + Utils.intToBinString(b << 2));
		System.out.println("b >> 2  = " + Utils.intToBinString(b >> 2));
		System.out.println("b >>> 2 = " + Utils.intToBinString(b >>> 2));
	}

	public void test4(){
		int a = 10;

		System.out.println("a = " + a);
		System.out.println("(a < 0) && (++a > 0) = " + ((a < 0) && (++a > 0)));
		System.out.println("a = " + a);
		System.out.println("(a < 0) & (++a > 0) = " + ((a < 0) & (++a > 0)));
		System.out.println("a = " + a);

		System.out.println("--------------------");

		System.out.println("(a > 0) || (++a > 0) = " + ((a > 0) || (++a > 0)));
		System.out.println("a = " + a);
		System.out.println("(a > 0) | (++a > 0) = " + ((a > 0) | (++a > 0)));
		System.out.println("a = " + a);
	}

	public void test5(){
		boolean a = false;
		boolean b = true;

		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("!a = " + !a);
		System.out.println("a & b = " + (a & b));
		System.out.println("a && b = " + (a && b));
		System.out.println("a | b = " + (a | b));
		System.out.println("a || b = " + (a || b));
	}

	public static void main(String[] args){
		TestOperator test = new TestOperator();

		System.out.println("============================================================");

		test.test1();

		System.out.println("----------------------------------------");

		test.test2();

		System.out.println("----------------------------------------");

		test.test3();

		System.out.println("----------------------------------------");

		test.test4();

		System.out.println("----------------------------------------");

		test.test5();

		System.out.println("============================================================");
	}
}
