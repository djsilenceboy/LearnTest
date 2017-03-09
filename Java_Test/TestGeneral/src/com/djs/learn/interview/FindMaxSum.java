
package com.djs.learn.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * From JPM.
 * Find max sum in a given list.
 * Max sum means sum of any continuous data.
 */
public class FindMaxSum
{
	// Test input: (The first number is the count of following numbers.)
	// 10 7 -3 -10 4 2 8 -2 4 -5 -6
	// Test output:
	// 16
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			// System.out.println(s);

			try {
				int maxGain = process(s);
				System.out.println(maxGain);
			} catch (Exception e) {
				System.err.println("Process input failed: " + e);
			}
		}
	}

	public static int process(String input) throws Exception{
		String[] rawData = input.split(" ");
		// System.out.println("Raw input data: " + Arrays.deepToString(rawData));

		int n = getInt(rawData[0]);
		// System.out.println("Total days = " + n);

		if (n < 1) {
			throw new Exception("Wrong days.");
		}

		if (rawData.length < (n + 1)) {
			throw new Exception("No enough data.");
		}

		int[] values = new int[n];
		for (int i = 0; i < n; i++) {
			values[i] = getInt(rawData[i + 1]);
		}

		// System.out.println("Total values = " + values.length);

		int maxGain = find(values, 0, values.length);

		// System.out.println("Max gain = " + maxGain);

		return maxGain;
	}

	public static int find(int[] values, int fromIdx, int length){
		int max = 0;

		if (length == 1) {
			max = values[fromIdx];
			return max;
		}

		for (int i = fromIdx; i < fromIdx + length; i++) {
			max += values[i];
		}

		// System.out.println("Value range [" + fromIdx + " / " + length + "] Max sum = " + max);

		for (int newlenght = length - 1; newlenght > 1; newlenght--) {
			for (int i = fromIdx; i <= length - newlenght; i++) {
				int tmp = find(values, i, newlenght);

				if (tmp > max) {
					max = tmp;
				}
			}
		}

		return max;
	}

	public static int getInt(String raw) throws Exception{
		int data;

		try {
			data = Integer.parseInt(raw);
		} catch (Exception e) {
			System.err.println("Process " + raw + " failed: " + e);

			throw e;
		}

		return data;
	}
}
