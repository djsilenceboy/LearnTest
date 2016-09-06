
package com.djs.learn.javalang.locale;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class TestMain
{
	private Locale getLocale(String language, String country){
		System.out.println("Locale = " + language + ", " + country);

		Locale locale;

		if (language == null) {
			locale = new Locale("");
		} else if (country == null) {
			locale = new Locale(language);
		} else {
			locale = new Locale(language, country);
		}

		return locale;
	}

	public void testLocale(){
		{
			System.out.println("Locale.getDefault = " + Locale.getDefault()); // DISPLAY
			System.out.println("Locale.getDefault(DISPLAY) = " + Locale.getDefault(Locale.Category.DISPLAY));
			System.out.println("Locale.getDefault(FORMAT) = " + Locale.getDefault(Locale.Category.FORMAT));
		}

		System.out.println("--------------------");

		{
			Locale[] locales = {Locale.JAPANESE, new Locale.Builder().setLanguage("ja").build(), new Locale("ja"), Locale.JAPAN,
			                    new Locale.Builder().setLanguage("ja").setRegion("JP").build(), new Locale("ja", "JP"),
			                    Locale.forLanguageTag("ja-JP-u-ca-japanese")};

			for (Locale locale : locales) {
				System.out.println("-> " + locale);
			}
		}

		System.out.println("--------------------");

		{
			Locale[] locales = DateFormat.getAvailableLocales();
			Set<String> localeNames = new TreeSet<String>();

			for (Locale locale : locales) {
				localeNames.add(locale.toString() + " - " + locale.getDisplayName());
			}

			for (String localeName : localeNames) {
				System.out.println("-> " + localeName);
			}
		}
	}

	/* For JDK 1.8
	public void testLocaleRange(){
		List<Locale> locales = new ArrayList<>();
	
		locales.add(Locale.forLanguageTag("en-GB"));
		locales.add(Locale.forLanguageTag("ja"));
		locales.add(Locale.forLanguageTag("zh-cmn-Hans-CN"));
		locales.add(Locale.forLanguageTag("en-US"));
	
		// Express the user's preferences with a Language Priority List
		String ranges = "en-US;q=1.0,en-GB;q=0.5,fr-FR;q=0.0";
		List<Locale.LanguageRange> languageRanges = Locale.LanguageRange.parse(ranges);
	
		// Now filter the Locale objects, returning any matches
		List<Locale> results = Locale.filter(languageRanges, locales);
	
		// Print out the matches
		for (Locale l : results) {
			System.out.println(l.toString());
		}
	}
	*/

	public void testMessage(String language, String country){
		try {
			Locale locale = getLocale(language, country);

			MessageFormat formatter = new MessageFormat("");
			formatter.setLocale(locale);

			String template = "At {2,time,short} on {2,date,long}, we detected {1,number,integer} spaceships on the planet {0}.";

			Object[] messageArguments = {"Mars", new Integer(7), new Date()};
			formatter.applyPattern(template);

			System.out.println("Template = " + formatter.format(messageArguments));
		} catch (Exception e) {
		}
	}

	public static void main(String[] args){
		TestMain test = new TestMain();

		System.out.println("========================================");

		test.testLocale();

		System.out.println("========================================");

		test.testMessage("en", "US");

		System.out.println("--------------------");

		test.testMessage("fr", "FR");

		System.out.println("========================================");
	}
}
