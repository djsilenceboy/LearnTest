
package com.djs.learn.se7test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YodishTest
{
	public static void main(String[] args){
		YodishTest test = new YodishTest();

		test.yodish();
	}

	public void yodish(){
		String original = "This sentence *is**out of* order";
		Pattern pattern = Pattern.compile("(\\*)(.*?)(\\*)");
		Matcher m = pattern.matcher(original);
		String order = "";
		String possessive = "";

		if (m.find()) {
			possessive = m.group(2);
			System.out.println("Possessive = " + possessive);

			if (m.find()) {
				order = m.group(2);
				System.out.println("Order = " + order);
			}
		}

		String[] pieces = original.split(" ");

		System.out.println("Pieces = " + Arrays.deepToString(pieces));

		System.out.printf("%4$s %3$s %1$s %5$s.", pieces[0], pieces[1], pieces[4], order, possessive);

		// out of order This is.
	}
}
