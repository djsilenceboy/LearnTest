
package com.djs.learn;

import java.util.ArrayDeque;

// https://leetcode.com/problems/longest-valid-parentheses/description/
public class LongestValidParentheses
{
	private int longestValidParentheses_1x(String s){
		ArrayDeque<Character> queue = new ArrayDeque<Character>();

		int maxLen = 0;
		int currentLen = 0;

		if (s != null) {
			for (Character c : s.toCharArray()) {
				if (c == '(') {
					queue.push(c);
				} else {
					if ((queue.size() > 0) && (queue.peek() == '(')) {
						queue.poll();
						currentLen += 2;
					} else {
						queue.push(c);
						if (currentLen > maxLen) maxLen = currentLen;
						currentLen = 0;
					}
				}
			}

			if (currentLen > maxLen) maxLen = currentLen;
		}

		return maxLen;
	}

	private int checkLongestValidParentheses(String s, int start, int end){
		// System.out.println("Input = " + s.substring(start, end + 1));
		int maxLen = 0;
		int size = end - start + 1;

		if (size >= 2) {
			if (size % 2 == 1) { // odd
				int maxLenL = checkLongestValidParentheses(s, start, end - 1);
				int maxLenR = checkLongestValidParentheses(s, start + 1, end);

				maxLen = Math.max(maxLenL, maxLenR);
			} else { // even
				ArrayDeque<Character> queue = new ArrayDeque<Character>();
				int i = 0;

				for (i = start; i <= end; i++) {
					char c = s.charAt(i);
					if (c == '(') {
						queue.push(c);
					} else { // ')'
						if (!queue.isEmpty() && (queue.peek() == '(')) {
							queue.poll();
						} else {
							break;
						}
					}
				}

				// System.out.println("Process (even) = " + (i > end) + ", " + (queue.size() == 0));

				if ((i > end) && queue.isEmpty()) maxLen = size;
				else if (size > 2) {
					int maxLenL = checkLongestValidParentheses(s, start, end - 2);
					int maxLenR = checkLongestValidParentheses(s, start + 2, end);
					int maxLenM = checkLongestValidParentheses(s, start + 1, end - 1);

					maxLen = Math.max(maxLenL, maxLenR);
					maxLen = Math.max(maxLen, maxLenM);
				}
			}
		}

		// System.out.println("Result = " + maxLen);

		return maxLen;
	}

	private int longestValidParentheses_2(String s){
		return checkLongestValidParentheses(s, 0, s.length() - 1);
	}

	/*
	// 2
	(()
	11
	2
	
	// 4
	)()())
	3 2
	0 1 22
	
	// 2
	()(()
	2 11
	1  3
	
	// 2
	)())()
	3  1
	0 11 2
	
	// 6
	(()())
	33 2
	2 33
	*/
	private int longestValidParentheses_3(String s){
		int[] weight = new int[s.length()];

		for (int i = 0; i < weight.length; i++) {
			char c1 = s.charAt(i);
			if (c1 == ')') {
				for (int j = i - 1; j >= 0; j--) {
					char c2 = s.charAt(j);
					if (c2 == '(') weight[j]++;
				}
			} else { // '('
				for (int j = i + 1; j <= weight.length; j++) {
					char c2 = s.charAt(j);
					if (c2 == ')') weight[j]++;
				}
			}
		}

		return 0;
	}

	public void test_longestValidParentheses_1(String s){
		System.out.println("Input = " + s);
		long startTime = System.currentTimeMillis();
		int result = longestValidParentheses_2(s);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		LongestValidParentheses solution = new LongestValidParentheses();

		// 2
		solution.test_longestValidParentheses_1("(()");
		// 4
		solution.test_longestValidParentheses_1(")()())");
		// 2
		solution.test_longestValidParentheses_1("()(()");
		// 2
		solution.test_longestValidParentheses_1(")())()");
		// 6
		solution.test_longestValidParentheses_1("(()())");

		// ?
		solution.test_longestValidParentheses_1(")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())");
	}
}
