
package com.djs.learn.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * From JPM.
 * Add two binary strings.
 */
public class BinaryAdd
{
	// Test input: 1100 0111
	// Test output: 10011

	// Test input: 1100 11
	// Test output: 10001

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			// System.out.println(s);

			try {
				String result = process(s);
				System.out.println(result);
			} catch (Exception e) {
				System.err.println("Process input failed: " + e);
			}
		}
	}

	public static String process(String input) throws Exception{
		StringBuilder result = new StringBuilder();

		// Reverse full string.
		String[] rawData = new StringBuilder(input).reverse().toString().split(" ");
		// Must be 2 number strings.
		if (rawData.length != 2) {
			throw new Exception("It should be two binary numbers.");
		}
		// Split number string into array.
		String[][] binaryData = {rawData[0].split(""), rawData[1].split("")};

		// System.out.println(Arrays.deepToString(binaryData[0]));
		// System.out.println(Arrays.deepToString(binaryData[1]));

		int counter = 0;
		int carrier = 0;
		while ((counter < binaryData[0].length) || (counter < binaryData[1].length)) {
			// Bit sum value range: [0,3].
			int bitSum = binaryAdd(counter < binaryData[0].length ? binaryData[0][counter] : "0", counter < binaryData[1].length ? binaryData[1][counter] : "0",
			                       carrier);

			// Get lower bit.
			int lowerBit = bitSum % 2;
			// Get higher bit as carrier.
			carrier = bitSum / 2;

			// System.out.println(lowerBit + ", " + carrier);

			result.append(lowerBit);
			counter++;
		}

		// Check last carrier.
		if (carrier > 0) {
			result.append(carrier);
		}

		// Reverse string back to normal order.
		return result.reverse().toString();
	}

	public static int binaryAdd(String bitStr1, String bitStr2, int carrier){
		int bit1 = bitStr1.equals("0") ? 0 : 1;
		int bit2 = bitStr2.equals("0") ? 0 : 1;
		int sum = bit1 + bit2 + carrier;
		// System.out.println(bit1 + ", " + bit2 + ", " + carrier + " = " + sum);
		return sum;
	}
}
