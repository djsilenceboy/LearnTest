
package com.djs.learn.interview;

/**
 * From BNP.
 * Convert integer to Roman numerals.
 */
public class ConvertIntegerToRomanNumerals
{
	static int[] levelNumbers = {1, 5, 10, 50, 100, 500, 1000};
	static String[] levelMarks = {"I", "V", "X", "L", "C", "D", "M"};

	public String repeatMarks(int level, int repeatTimes){
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < repeatTimes; i++)
			result.append(levelMarks[level]);

		return result.toString();
	}

	public String convert(int level, int number){
		StringBuilder result = new StringBuilder();

		if (number <= 3) {
			result.append(repeatMarks(level, number));
		} else if (level + 1 >= levelMarks.length) {
			result.append(repeatMarks(level, number));
		} else {
			result.append(repeatMarks(level, 5 - number)).append(levelMarks[level + 1]);
		}

		return result.toString();
	}

	public String convert(int number){

		StringBuilder result = new StringBuilder();
		int level = levelNumbers.length - 1;

		for (; level >= 0; level--) {
			result.append(convert(level, number / levelNumbers[level]));
			number %= levelNumbers[level];
		}

		return result.toString();
	}

	public static void main(String[] args){
		ConvertIntegerToRomanNumerals testMain = new ConvertIntegerToRomanNumerals();

		System.out.println("========================================");

		System.out.println(testMain.repeatMarks(3, 5));

		System.out.println("--------------------");

		System.out.println(testMain.convert(2, 3));
		System.out.println(testMain.convert(2, 4));
		System.out.println(testMain.convert(2, 5));

		System.out.println("--------------------");

		{
			int[] sampleData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
			int[] bases = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90};

			for (int base : bases) {
				for (int data : sampleData) {
					System.out.println((base + data) + " = " + testMain.convert(base + data));
				}
			}
		}

		System.out.println("--------------------");

		{
			int[] sampleData = {124, 333, 444, 478, 555, 3000, 3874, 3875, 3999, 4000, 4999};

			for (int data : sampleData) {
				System.out.println((data) + " = " + testMain.convert(data));
			}
		}

		System.out.println("========================================");
	}
}
