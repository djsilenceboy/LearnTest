
package com.djs.learn;

// https://leetcode.com/problems/longest-palindromic-substring/description/
public class LongestPalindromicSubstring
{
	public String longestPalindrome(String s){
		String result = null;

		for (int k = s.length(); k > 0; k--) {
			for (int p = 0; p < s.length() - k + 1; p++) {
				int i = p, j = p + k - 1;

				while (i < j) {
					if (s.charAt(i) == s.charAt(j)) {
						i++;
						j--;
					} else break;
				}

				if (i >= j) {
					result = s.substring(p, p + k);
					break;
				}
			}

			if (result != null) break;
		}

		return result;
	}

	public void test_longestPalindrome(String s){
		System.out.println("Input = " + s);
		long startTime = System.currentTimeMillis();
		String result = longestPalindrome(s);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		LongestPalindromicSubstring solution = new LongestPalindromicSubstring();

		{
			// "bab"
			solution.test_longestPalindrome("babad");
		}

		{
			// "bb"
			solution.test_longestPalindrome("cbbd");
		}
	}
}
