
package com.djs.learn.javalang.reference;

public class TestMain
{
	public static void main(String[] args){
		SimpleFunc sf = new SimpleFunc();
		SimpleObject so = new SimpleObject();

		System.out.println("Before = " + so.getInfo());
		sf.setInfo("Hello", so);
		System.out.println("After = " + so.getInfo());

		System.out.println("==========");

		SimpleObject so2 = null;

		sf.getInfo("Hello", so2);
		System.out.println("After = " + so2);
		System.out.println("After = " + ((so2 != null) ? so2.getInfo() : null));

		/*
		 * Before = null
		 * After = Hello
		 * ==========
		 * After = null
		 * After = null
		 */
	}
}
