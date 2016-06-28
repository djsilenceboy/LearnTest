
package dj.test.se7test;

import java.util.regex.Pattern;

public class PatternTest
{
	public static void main(String[] args){
		String a = "BaRbarFoo barfoo barFoobar";
		Pattern pattern = Pattern.compile("\\s*bar\\s*", Pattern.CASE_INSENSITIVE);
		String b = "";

		for (String s : pattern.split(a)) {
			b += s + ",";
		}
		System.out.println(b);
	}
}
