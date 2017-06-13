
package com.djs.learn.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * From JPM.
 * Given two rectangle. Detect whether they are overlapped.
 * Input format: X Y Width Height X Y Width Height
 * Width and Height could be negtive.
 * Output: ture or false.
 */
public class DetectRectangleOverlap
{
	// Test input: 1 1 4 3 4 3 3 3
	// Test output: true

	// Test input: 1 1 4 3 2 2 2 3
	// Test output: true

	// Test input: 1 1 4 4 3 2 4 2
	// Test output: true

	// Test input: 1 1 4 4 2 2 2 2
	// Test output: true

	// Test input: 1 1 4 3 6 2 2 2
	// Test output: false

	// Test input: 1 1 4 3 1 4 2 2
	// Test output: false

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			// System.out.println(s);

			try {
				boolean overlapped = process(s);
				System.out.println(overlapped);
			} catch (Exception e) {
				System.err.println("Process input failed: " + e);
			}
		}
	}

	public static boolean process(String input) throws Exception{
		boolean overlapped = false;
		String[] rawDataStr = input.split(" ");
		// Must be 8 numbers.
		if (rawDataStr.length != 8) {
			throw new Exception("It should be 8 numbers.");
		}

		int[] rects = new int[rawDataStr.length];
		for (int i = 0; i < rawDataStr.length; i++) {
			rects[i] = getInt(rawDataStr[i]);
		}

		// System.out.println(Arrays.toString(rects));

		double deltaX = Math.abs(rects[0] - rects[4]);
		double deltaY = Math.abs(rects[1] - rects[5]);
		double widthHalfSum = (Math.abs(rects[2]) + Math.abs(rects[6])) / 2.0;
		double heightHalfSum = (Math.abs(rects[3]) + Math.abs(rects[7])) / 2.0;

		// System.out.println("X : " + deltaX + ", " + widthHalfSum);
		// System.out.println("Y : " + deltaY + ", " + heightHalfSum);

		overlapped = (deltaX < widthHalfSum) && (deltaY < heightHalfSum);

		return overlapped;
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
