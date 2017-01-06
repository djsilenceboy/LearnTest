
package com.djs.learn.javalang.locale;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestLocaleV8
{
	public void testLocaleRange(){
		List<Locale> locales = new ArrayList<>();

		locales.add(Locale.forLanguageTag("en-GB"));
		locales.add(Locale.forLanguageTag("ja"));
		locales.add(Locale.forLanguageTag("zh-cmn-Hans-CN"));
		locales.add(Locale.forLanguageTag("en-US"));

		// Express the user's preferences with a Language Priority List.
		String ranges = "en-US;q=1.0,en-GB;q=0.5,fr-FR;q=0.0";
		List<Locale.LanguageRange> languageRanges = Locale.LanguageRange.parse(ranges);

		languageRanges.stream().forEach(languageRange -> System.out.println(languageRange.getRange()));

		System.out.println("--------------------");

		// Now filter the Locale objects, returning any matches.
		List<Locale> results = Locale.filter(languageRanges, locales);

		// Print out the matches.
		results.stream().forEach(System.out::println);
	}

	public static void main(String[] args){
		TestLocaleV8 test = new TestLocaleV8();

		System.out.println("========================================");

		test.testLocaleRange();

		System.out.println("========================================");
	}
}
