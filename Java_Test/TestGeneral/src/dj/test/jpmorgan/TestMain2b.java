
package dj.test.jpmorgan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestMain2b
{
	// Test input:
	// 3      ThreeDollars
	// 10     TenDollars
	// 21     TwentyOneDollars
	// 466    FourHundredSixtySixDollars
	// 1234   OneThousandTwoHundredThirtyFourDollars
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			// System.out.println(s);

			try {
				String results = process(s);
				System.out.println(results);
			} catch (Exception e) {
			}
		}
	}

	public static long getLong(String raw) throws Exception{
		long data;

		try {
			data = Long.parseLong(raw);
		} catch (Exception e) {
			throw e;
		}

		return data;
	}

	private static final String[] numNames = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
	                                          "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

	private static final String[] tensNames = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

	private static final String[] sectionNames = {"", "Thousand", "Million", "Billion"};

	private static String convertLessThanOneThousand(int number){
		String result;

		// Find 0 to 19.
		if ((number % 100) < 20) {
			result = numNames[number % 100];
			number /= 100;
		} else {
			// If >= 20, find unit digit.
			result = numNames[number % 10];
			number /= 10;

			// Then find tens digit.
			result = tensNames[number % 10] + result;
			number /= 10;
		}

		// Find hundred digit.
		if (number != 0) {
			return result = numNames[number] + "Hundred" + result;
		}

		return result;
	}

	public static String process(String input) throws Exception{
		StringBuilder results = new StringBuilder();

		long number = getLong(input);
		// System.out.println("Value: " + number);

		// Dollar(s).
		String dollar = (number <= 1) ? "Dollar" : "Dollars";

		if (number == 0) {
			results.append("Zero");
		} else {
			int[] threeDigitsSection = new int[]{0, 0, 0, 0};

			// Find sections: nnn,nnn,nnn,nnn = Billion,Million,Thousand,LessThanThousand
			for (int i = 0; i < threeDigitsSection.length; i++) {
				threeDigitsSection[i] = (int)(number % 1000);
				// System.out.println("Three digits section [" + i + "]: " + threeDigitsSection[i]);

				number /= 1000;
			}

			// Process each section as LessThanThousand.
			for (int i = threeDigitsSection.length - 1; i >= 0; i--) {
				if (threeDigitsSection[i] != 0) {
					String temp = convertLessThanOneThousand(threeDigitsSection[i]) + sectionNames[i];
					// System.out.println("Three digits section [" + i + "]: " + temp);

					results.append(temp);
				}
			}
		}

		results.append(dollar);

		return results.toString();
	}
}
