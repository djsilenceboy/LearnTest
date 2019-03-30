
package com.djs.learn.javalang.classes;

/**
 * <pre>
============================================================
com.djs.learn.javalang.basic.Inner:Static initializer: news = Good Day!
com.djs.learn.javalang.basic.Outer:Static initializer: newsEx = Good Day! Ex!
com.djs.learn.javalang.basic.Inner:Instance initializer: msg = Hello
com.djs.learn.javalang.basic.Inner:Inner()
com.djs.learn.javalang.basic.Inner:Inner(): msg = Hello
com.djs.learn.javalang.basic.Inner:Inner(): news = Good Day!
com.djs.learn.javalang.basic.Outer:Instance initializer: msgEx = Hello Ex
com.djs.learn.javalang.basic.Outer:Outer()
com.djs.learn.javalang.basic.Outer:Outer(): msgEx = Hello Ex
com.djs.learn.javalang.basic.Outer:Outer(): newsEx = Good Day! Ex!
--------------------
com.djs.learn.javalang.basic.Inner:Instance initializer: msg = Hello
com.djs.learn.javalang.basic.Inner:Inner()
com.djs.learn.javalang.basic.Inner:Inner(): msg = Hello
com.djs.learn.javalang.basic.Inner:Inner(): news = Good Day!
com.djs.learn.javalang.basic.Outer:Instance initializer: msgEx = Hello Ex
com.djs.learn.javalang.basic.Outer:Outer()
com.djs.learn.javalang.basic.Outer:Outer(): msgEx = Hello Ex
com.djs.learn.javalang.basic.Outer:Outer(): newsEx = Good Day! Ex!
--------------------
com.djs.learn.javalang.basic.Sample:setCounter: param:counter = 10
com.djs.learn.javalang.basic.Sample:setCounter: counter = 0
com.djs.learn.javalang.basic.Sample:setCounter: counter = 5
com.djs.learn.javalang.basic.Sample:class initializer: counter = 10
com.djs.learn.javalang.basic.Sample:constructor: param:counter = 20
com.djs.learn.javalang.basic.Sample:constructor: counter = 10
com.djs.learn.javalang.basic.Sample:constructor: counter = 20
============================================================
 * </pre>
 */
public class TestInitSequence
{
	public void testInit(){
		new Outer();

		System.out.println("--------------------");

		new Outer();

		System.out.println("--------------------");

		new Sample(20);
	}

	public static void main(String[] args){
		TestInitSequence testMain = new TestInitSequence();

		System.out.println("============================================================");

		testMain.testInit();

		System.out.println("============================================================");
	}
}

class Inner
{
	private String msg;

	{
		msg = "Hello";

		System.out.println(Inner.class.getName() + ":Instance initializer: msg = " + msg);
	}

	private static String news;

	static {
		news = "Good Day!";

		System.out.println(Inner.class.getName() + ":Static initializer: news = " + news);
	}

	public Inner(){
		System.out.println(Inner.class.getName() + ":Inner()");
		System.out.println(Inner.class.getName() + ":Inner(): msg = " + msg);
		System.out.println(Inner.class.getName() + ":Inner(): news = " + news);
	}
}

class Outer extends Inner
{
	private String msgEx;

	{
		msgEx = "Hello Ex";

		System.out.println(Outer.class.getName() + ":Instance initializer: msgEx = " + msgEx);
	}

	private static String newsEx;

	static {
		newsEx = "Good Day! Ex!";

		System.out.println(Outer.class.getName() + ":Static initializer: newsEx = " + newsEx);
	}

	public Outer(){
		System.out.println(Outer.class.getName() + ":Outer()");
		System.out.println(Outer.class.getName() + ":Outer(): msgEx = " + msgEx);
		System.out.println(Outer.class.getName() + ":Outer(): newsEx = " + newsEx);
	}
}

class Sample
{
	private int counter = setCounter(10);

	{
		System.out.println(Sample.class.getName() + ":class initializer: counter = " + counter);
	}

	public Sample(int counter){
		System.out.println(Sample.class.getName() + ":constructor: param:counter = " + counter);
		System.out.println(Sample.class.getName() + ":constructor: counter = " + this.counter);
		this.counter = counter;
		System.out.println(Sample.class.getName() + ":constructor: counter = " + this.counter);
	}

	public int setCounter(int counter){
		System.out.println(Sample.class.getName() + ":setCounter: param:counter = " + counter);
		System.out.println(Sample.class.getName() + ":setCounter: counter = " + this.counter);
		this.counter = 5;
		System.out.println(Sample.class.getName() + ":setCounter: counter = " + this.counter);

		return counter;
	}
}
