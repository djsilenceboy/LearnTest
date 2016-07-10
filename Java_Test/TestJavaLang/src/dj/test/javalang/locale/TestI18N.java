
package dj.test.javalang.locale;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestI18N
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

	private ResourceBundle getMessageResourceBundle(Locale locale) throws Exception{
		Path path = Paths.get("etc/resource");
		URL[] urls = {path.toUri().toURL()};
		ClassLoader loader = new URLClassLoader(urls);

		// MessagesBundle.properties
		// MessagesBundle*.properties
		return ResourceBundle.getBundle("MessagesBundle", locale, loader);
	}

	public void test1(String language, String country){
		try {
			Locale locale = getLocale(language, country);
			ResourceBundle messages = getMessageResourceBundle(locale);

			Enumeration bundleKeys = messages.getKeys();

			while (bundleKeys.hasMoreElements()) {
				String key = (String)bundleKeys.nextElement();
				String value = messages.getString(key);
				System.out.println(key + " = " + value);
			}
		} catch (Exception e) {
		}
	}

	public void test2(String language, String country){
		try {
			Locale locale = getLocale(language, country);

			// NOT WORKING!!!
			// NumberBundle.properties
			// NumberBundle*.properties
			ResourceBundle numbers = ResourceBundle.getBundle("NumberBundle", locale);

			Enumeration bundleKeys = numbers.getKeys();

			while (bundleKeys.hasMoreElements()) {
				String key = (String)bundleKeys.nextElement();
				Object value = numbers.getObject(key);
				System.out.println(key + " = " + value);
			}
		} catch (Exception e) {
		}
	}

	public void test3(String language, String country){
		try {
			Locale locale = getLocale(language, country);

			NumberFormat numberFormatter = NumberFormat.getNumberInstance(locale);
			Integer quantity = new Integer(123456);
			Double amount = new Double(345987.246);

			System.out.println("Quantity = " + numberFormatter.format(quantity));
			System.out.println("Amount = " + numberFormatter.format(amount));
		} catch (Exception e) {
		}
	}

	public static void main(String[] args){
		TestI18N test = new TestI18N();

		System.out.println("========================================");

		test.test1(null, null);

		System.out.println("--------------------");

		test.test1("aa", null);

		System.out.println("--------------------");

		test.test1("en", null);

		System.out.println("--------------------");

		test.test1("en", "US");

		System.out.println("--------------------");

		test.test1("de", "DE");

		System.out.println("--------------------");

		test.test1("fr", "FR");

		System.out.println("========================================");

		test.test1(null, null);

		System.out.println("--------------------");

		test.test2("aa", null);

		System.out.println("--------------------");

		test.test2("en", null);

		System.out.println("--------------------");

		test.test2("en", "US");

		System.out.println("--------------------");

		test.test2("de", "DE");

		System.out.println("--------------------");

		test.test2("fr", "FR");
		System.out.println("========================================");

		test.test3(null, null);

		System.out.println("--------------------");

		test.test3("aa", null);

		System.out.println("--------------------");

		test.test3("en", null);

		System.out.println("--------------------");

		test.test3("en", "US");

		System.out.println("--------------------");

		test.test3("de", "DE");

		System.out.println("--------------------");

		test.test3("fr", "FR");

		System.out.println("========================================");
	}
}
