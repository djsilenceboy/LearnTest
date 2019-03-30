
package com.djs.learn.se7test;

public class ZerTest
{
	String name;

	public static void main(String[] args){
		Vol player = new Vol();
	}

	// private ZerTest(){
	public ZerTest(){
		this.name = "Zer";
	}
}

class Vol extends ZerTest
{
	Vol(){
		System.out.println(name);
	}
}
