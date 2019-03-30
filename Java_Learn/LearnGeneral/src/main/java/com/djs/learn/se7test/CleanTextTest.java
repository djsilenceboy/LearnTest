
package com.djs.learn.se7test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanTextTest
{
	public static void main(String[] args){
		String input = "THEre A<re maN!Y spulling mistak3s in this " + "this suntunce";
		System.out.println("Input = " + input);

		String fixed = input.replace("u", "e");
		System.out.println("Fixed = " + fixed);

		Pattern p = Pattern.compile("(\\w)(.*?)(\\d)(.*?)(\\w)");
		Matcher leet = p.matcher(fixed);

		fixed = leet.replaceAll("$1$2e$4$5");
		System.out.println("Fixed = " + fixed);

		Matcher syntax = Pattern.compile("(^.)(.+)").matcher(fixed);
		syntax.find();
		fixed = syntax.group(1).toUpperCase() + syntax.group(2).toLowerCase();
		System.out.println("Fixed = " + fixed);

		syntax = Pattern.compile("\\w+|\\s").matcher(fixed);
		fixed = "";
		while (syntax.find())
			fixed += syntax.group();
		System.out.println("Fixed = " + fixed);

		syntax = Pattern.compile("\\b(\\w+) \\1\\b").matcher(fixed);
		syntax.replaceAll("$1");
		System.out.println("Fixed = " + fixed);
	}
}
