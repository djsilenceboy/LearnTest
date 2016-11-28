
package com.djs.learn.classtag.mix;

abstract class AbstractWithMain
{
	public AbstractWithMain(){
		System.out.println("AbstractWithMain:AbstractWithMain");
	}

	// Abstract class can have main.
	public static void main(String[] args){
		System.out.println("AbstractWithMain:main");
	}
}
