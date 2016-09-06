
package com.djs.learn.se7test;

public class AbsJava
{
	public static void main(String[] args){
		AbsJava me = new AbsJava();
		me.doStuff();
	}

	public void doStuff(){
		Bat a = new Bat();
		Question b = new Blue();
		a.printName();
		b.printName();
	}

	abstract class Question
	{
		String name;

		public void printName(){
			System.out.println("Question: " + name);
		}
	}

	class Bat extends Question
	{
		String name = "Bruce";
	}

	class Blue extends Question
	{
		String name = "Clark";

		@Override
		public void printName(){
			System.out.println("Blue: " + name);
		}
	}
}
