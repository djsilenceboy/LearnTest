
package com.djs.learn.javalang.classes;

public class TestOverride2
{
	public static void main(String[] args){
	}
}

class HouseA
{
	static void printA(){
		System.out.println("HouseA");
	}

	void printB(){
		System.out.println("HouseA");
	}
}

class HouseB extends HouseA
{
	// Cannot override or hide due to static/non-static.
	/*
	void printA(){
		System.out.println("HouseB");
	}

	static void printB(){
		System.out.println("HouseB");
	}
	*/
}

class HouseC extends HouseA
{
	static void printA(){
		System.out.println("HouseB");
	}

	@Override
	void printB(){
		System.out.println("HouseB");
	}
}
