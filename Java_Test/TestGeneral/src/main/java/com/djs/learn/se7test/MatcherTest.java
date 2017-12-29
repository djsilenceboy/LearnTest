
package com.djs.learn.se7test;

import java.util.regex.Pattern;

public class MatcherTest
{
	public static void main(String[] args){
		Animal tiger = new Tiger();
		Animal giraffe = new Giraffe();

		String s = "The Giraffe runs and the Tiger eats trees.";
		Pattern p1 = Pattern.compile("Tiger");
		Pattern p2 = Pattern.compile("Giraffe");
		String result = "";

		result = p1.matcher(s).replaceAll(giraffe.getClass().getSimpleName());
		result = p2.matcher(result).replaceFirst(tiger.getClass().getSimpleName());

		System.out.println(result);
	}

}

class Animal
{}

class Tiger extends Animal
{}

class Giraffe extends Animal
{}
