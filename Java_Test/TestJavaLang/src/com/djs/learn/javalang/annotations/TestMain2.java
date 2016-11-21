
package com.djs.learn.javalang.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.sun.istack.internal.NotNull;

public class TestMain2
{
	@PostConstruct
	public void setup(){
		System.out.println("setup()");
	}

	@PreDestroy
	public void teardown(){
		System.out.println("teardown()");
	}

	public void test1(){
		@NotNull
		String name = null;

		@NotNull
		String[][] wordsA = null;

		// String @NotNull [][] wordsB;
		// String[] @NotNull [] wordsC;
		// String [][] @NotNull wordsD;

		System.out.println("name = " + name);
		System.out.println("wordsA = " + wordsA);
	}

	public static void main(String[] args){
		TestMain2 testMain = new TestMain2();

		System.out.println("================================================================================");

		testMain.test1();

		System.out.println("================================================================================");
	}
}
