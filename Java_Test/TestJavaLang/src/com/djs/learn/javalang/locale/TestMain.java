
package com.djs.learn.javalang.locale;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

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

	public void testLocale1(){
		{
			Locale[] locales = {Locale.JAPANESE, new Locale.Builder().setLanguage("ja").build(), new Locale("ja"), Locale.JAPAN,
			                    new Locale.Builder().setLanguage("ja").setRegion("JP").build(), new Locale("ja", "JP"),
			                    Locale.forLanguageTag("ja-JP-u-ca-japanese")};

			Stream.of(locales).forEach(locale -> System.out.println(locale.toString() + " - " + locale.getDisplayName() + " -> " + locale.getLanguage() + " - "
			        + locale.getCountry() + " - " + locale.getVariant() + " - " + locale.getScript() + " - " + locale.getExtensionKeys()));
		}

		System.out.println("--------------------");

		{
			Locale[] locales = DateFormat.getAvailableLocales();
			Stream.of(locales).limit(5).forEach(locale -> System.out.println(locale.toString() + " - " + locale.getDisplayName() + " -> " + locale.getLanguage()
			        + " - " + locale.getCountry() + " - " + locale.getVariant() + " - " + locale.getScript() + " - " + locale.getExtensionKeys()));
		}

		System.out.println("--------------------");

		{
			Locale[] locales = Locale.getAvailableLocales();
			Stream.of(locales).limit(20)
			        .forEach(locale -> System.out.println(locale.toString() + " - " + locale.getDisplayName() + " -> " + locale.getLanguage() + " - "
			                + locale.getCountry() + " - " + locale.getVariant() + " - " + locale.getScript() + " - " + locale.getExtensionKeys()));
		}

		System.out.println("--------------------");

		{
			Locale[] locales = Locale.getAvailableLocales();
			Stream.of(locales).filter(locale -> locale.getLanguage().equals("ja") || locale.getLanguage().equals("en"))
			        .forEach(locale -> System.out.println(locale.toString() + " - " + locale.getDisplayName() + " -> " + locale.getLanguage() + " - "
			                + locale.getCountry() + " - " + locale.getVariant() + " - " + locale.getScript() + " - " + locale.getExtensionKeys()));
		}
	}

	public void testLocale2(){
		{
			System.out.println("Locale.getDefault = " + Locale.getDefault()); // DISPLAY
			System.out.println("Locale.getDefault(DISPLAY) = " + Locale.getDefault(Locale.Category.DISPLAY));
			System.out.println("Locale.getDefault(FORMAT) = " + Locale.getDefault(Locale.Category.FORMAT));
		}

		System.out.println("--------------------");

		{
			Locale.setDefault(Locale.JAPANESE);

			System.out.println("Locale.getDefault = " + Locale.getDefault()); // DISPLAY
			System.out.println("Locale.getDefault(DISPLAY) = " + Locale.getDefault(Locale.Category.DISPLAY));
			System.out.println("Locale.getDefault(FORMAT) = " + Locale.getDefault(Locale.Category.FORMAT));
		}

	}

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

		test.testLocale1();

		System.out.println("========================================");

		test.testLocale2();

		System.out.println("========================================");

		test.testMessage("en", "US");

		System.out.println("--------------------");

		test.testMessage("fr", "FR");

		System.out.println("========================================");
	}
}
