
package com.djs.learn.javalang.basic;

/**
 * For Overloading methods,
 * If input parameter is Boxing type (Such as Byte), it will:
 * 1. find method with same Boxing type (Such as Byte);
 * 2. find method with same Unboxing type (Such as byte);
 * 3. find method with larger Unboxing type (Such as int).
 * <br>
 * If input parameter is Unboxing type (Such as byte), it will:
 * 1. find method with same Unboxing type (Such as byte);
 * 2. find method with larger Unboxing type (Such as int);
 * 3. find method with same Boxing type (Such as Byte).
 */
public class TestAutoboxing
{
	public void test1(){
		byte a1 = 123;
		Byte b1 = a1;
		Byte b2 = 123; // It will use cache value used by "a1".
		Byte b3 = new Byte((byte)123);
		Byte b4 = new Byte(a1);
		Byte b5 = b4;

		System.out.println("a1 == b1 : " + (a1 == b1));
		System.out.println("b1 == b2 : " + (b1 == b2));
		System.out.println("b1 == b3 : " + (b1 == b3));
		System.out.println("b1.byteValue() == b3 : " + (b1.byteValue() == b3));
		System.out.println("b1 == b4 : " + (b1 == b4));
		System.out.println("b3 == b4 : " + (b3 == b4));
		System.out.println("b4 == b5 : " + (b4 == b5));
	}

	public static void main(String[] args){
		TestAutoboxing test = new TestAutoboxing();

		System.out.println("============================================================");

		test.test1();

		System.out.println("============================================================");

		Autobox1 autobox1 = new Autobox1();
		Autobox2 autobox2 = new Autobox2();
		Autobox3 autobox3 = new Autobox3();
		Autobox4 autobox4 = new Autobox4();
		Autobox5 autobox5 = new Autobox5();
		Autobox6 autobox6 = new Autobox6();
		Autobox7 autobox7 = new Autobox7();
		Autobox8 autobox8 = new Autobox8();

		Autobox9 autobox9 = new Autobox9();

		{
			Byte data = 123;

			System.out.println("Byte = 123");

			autobox1.support();
			autobox1.testParam(data);
			autobox2.support();
			autobox2.testParam(data);
			autobox3.support();
			autobox3.testParam(data);
			autobox4.support();
			autobox4.testParam(data);
			autobox5.support();
			autobox5.testParam(data);
			autobox6.support();
			autobox6.testParam(data);
			autobox7.support();
			autobox7.testParam(data);
			// Compile error.
			// autobox8.support(); autobox8.testParam(data);
		}

		System.out.println("============================================================");

		{
			byte data = 123;

			System.out.println("byte = 123");

			autobox1.support();
			autobox1.testParam(data);
			autobox2.support();
			autobox2.testParam(data);
			autobox3.support();
			autobox3.testParam(data);
			autobox4.support();
			autobox4.testParam(data);
			autobox5.support();
			autobox5.testParam(data);
			autobox6.support();
			autobox6.testParam(data);
			autobox7.support();
			autobox7.testParam(data);
			// Compile error.
			// autobox8.support(); autobox8.testParam(data);

		}

		System.out.println("============================================================");

		{
			Integer data = 123;

			System.out.println("Integer = 123");

			autobox1.support();
			autobox1.testParam(data);
			autobox2.support();
			autobox2.testParam(data);
			autobox3.support();
			autobox3.testParam(data);
			// Compile error.
			// autobox4.support();	autobox4.testParam(data);
			// Compile error.
			// autobox5.support();	autobox5.testParam(data);
			autobox6.support();
			autobox6.testParam(data);
			autobox7.support();
			autobox7.testParam(data);
			autobox8.support();
			autobox8.testParam(data);
		}

		System.out.println("============================================================");

		{
			byte data = 123;

			System.out.println("byte = 123");

			autobox9.support();
			autobox9.testParam(data);
		}

		System.out.println("--------------------");

		{
			double data = 123;

			System.out.println("double = 123");

			autobox9.support();
			autobox9.testParam(data);
		}

		System.out.println("============================================================");
	}
}

class Autobox1
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "byte " + "Byte " + "int " + "Integer";
	}

	public void testParam(byte data){
		System.out.println(support() + " : byte");
	}

	public void testParam(Byte data){
		System.out.println(support() + " : Byte");
	}

	public void testParam(int data){
		System.out.println(support() + " : int");
	}

	public void testParam(Integer data){
		System.out.println(support() + " : Integer");
	}
}

class Autobox2
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "byte " + "int " + "Integer";
	}

	public void testParam(byte data){
		System.out.println(support() + " : byte");
	}

	public void testParam(int data){
		System.out.println(support() + " : int");
	}

	public void testParam(Integer data){
		System.out.println(support() + " : Integer");
	}
}

class Autobox3
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "Byte " + "int " + "Integer";
	}

	public void testParam(Byte data){
		System.out.println(support() + " : Byte");
	}

	public void testParam(int data){
		System.out.println(support() + " : int");
	}

	public void testParam(Integer data){
		System.out.println(support() + " : Integer");
	}
}

class Autobox4
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "byte";
	}

	public void testParam(byte data){
		System.out.println(support() + " : byte");
	}
}

class Autobox5
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "Byte";
	}

	public void testParam(Byte data){
		System.out.println(support() + " : Byte");
	}
}

class Autobox6
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "int " + "Integer";
	}

	public void testParam(int data){
		System.out.println(support() + " : int");
	}

	public void testParam(Integer data){
		System.out.println(support() + " : Integer");
	}
}

class Autobox7
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "int";
	}

	public void testParam(int data){
		System.out.println(support() + " : int");
	}
}

class Autobox8
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "Integer";
	}

	public void testParam(Integer data){
		System.out.println(support() + " : Integer");
	}
}

class Autobox9
{
	String id = "[" + this.getClass().getName() + "] ";

	public String support(){
		return id + "short " + "int " + "Object";
	}

	public void testParam(int data){
		System.out.println(support() + " : int");
	}

	public void testParam(short data){
		System.out.println(support() + " : short");
	}

	public void testParam(Object data){
		System.out.println(support() + " : Object");
	}
}
