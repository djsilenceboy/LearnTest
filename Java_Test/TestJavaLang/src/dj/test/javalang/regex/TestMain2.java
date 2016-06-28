
package dj.test.javalang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain2
{
	public void test1(){
		String[] sources = {"", "a", "aaa", "ababaaab"};
		String[] patterns = {"a?", "a*", "a+", "a??", "a*?", "a+?", "a?+", "a*+", "a++"};

		try {
			System.out.printf("%15s :%15s\n", "Source", "Pattern");

			System.out.println("----------------------------------------");

			for (String source : sources) {
				for (String pattern : patterns) {
					System.out.printf("%15s :%15s\n", source, pattern);

					Pattern p = Pattern.compile(pattern);
					Matcher m = p.matcher(source);

					while (m.find()) {
						System.out.println("-> (" + m.start() + "," + m.end() + ") " + m.group());
					}

					System.out.println("----------------------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception = " + e);
		}
	}

	public void test2(){
		String[] sources = {"ababaaab"};
		String[] patterns = {".*b", ".*?b", ".*+b"};

		try {
			System.out.printf("%15s :%15s\n", "Source", "Pattern");

			System.out.println("----------------------------------------");

			for (String source : sources) {
				for (String pattern : patterns) {
					System.out.printf("%15s :%15s\n", source, pattern);

					Pattern p = Pattern.compile(pattern);
					Matcher m = p.matcher(source);

					while (m.find()) {
						System.out.println("-> (" + m.start() + "," + m.end() + ") " + m.group());
					}

					System.out.println("----------------------------------------");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestMain2 tr = new TestMain2();

		System.out.println("========================================");

		tr.test1();

		System.out.println("========================================");

		tr.test2();

		System.out.println("========================================");
	}
}
