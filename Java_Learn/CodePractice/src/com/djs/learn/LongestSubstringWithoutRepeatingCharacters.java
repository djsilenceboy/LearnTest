
package com.djs.learn;

import java.util.ArrayList;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LongestSubstringWithoutRepeatingCharacters
{
	public int lengthOfLongestSubstring_1(String s){
		ArrayList<Character> tempList = new ArrayList<Character>();
		int start_idx = 0, substr_length = 0;
		int max_idx = 0, max_length = 0;

		for (int i = 0; i < s.length() - max_length; i++) {
			tempList.clear();
			start_idx = i;
			substr_length = 0;

			for (int j = i; j < s.length(); j++) {
				if (!tempList.contains(s.charAt(j))) {
					tempList.add(s.charAt(j));
					substr_length++;
				} else break;
			}

			if (substr_length > max_length) {
				max_idx = start_idx;
				max_length = substr_length;
			}
		}

		return max_length;
	}

	public int lengthOfLongestSubstring_2(String s){
		ArrayList<Character> tempList = new ArrayList<Character>();
		int max_length = 0;

		for (int i = 0; i < s.length() - max_length; i++) {
			tempList.clear();

			int j = 0;
			for (j = i; j < s.length(); j++) {
				if (!tempList.contains(s.charAt(j))) tempList.add(s.charAt(j));
				else break;
			}

			max_length = Math.max(max_length, j - i);
		}

		return max_length;
	}

	public void test_lengthOfLongestSubstring_1(String s){
		System.out.println("Input = " + s);
		long startTime = System.currentTimeMillis();
		int result = lengthOfLongestSubstring_1(s);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public void test_lengthOfLongestSubstring_2(String s){
		System.out.println("Input = " + s);
		long startTime = System.currentTimeMillis();
		int result = lengthOfLongestSubstring_2(s);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();

		{
			// 3
			solution.test_lengthOfLongestSubstring_1("abcabcbb");
		}

		{
			// 1
			solution.test_lengthOfLongestSubstring_1("bbbbb");
		}

		{
			// 3
			solution.test_lengthOfLongestSubstring_1("pwwkew");
		}

		{
			// 3
			solution.test_lengthOfLongestSubstring_2("abcabcbb");
		}

		{
			// 1
			solution.test_lengthOfLongestSubstring_2("bbbbb");
		}

		{
			// 3
			solution.test_lengthOfLongestSubstring_2("pwwkew");
		}
	}
}
