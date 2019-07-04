
package com.djs.learn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/valid-parentheses/
public class ValidParentheses
{
	public boolean isValid(String s){
		boolean valid = true;
		char[] chars = s.toCharArray();
		Map<Character, Character> parentheses = new HashMap<>();
		parentheses.put('(', ')');
		parentheses.put('[', ']');
		parentheses.put('{', '}');
		Set<Character> left = parentheses.keySet();

		Deque<Character> stack = new ArrayDeque<>();
		for (char ch : chars) {
			if (left.contains(ch)) {
				stack.push(ch);
			} else if (stack.isEmpty()) {
				valid = false;
				break;
			} else if (parentheses.get(stack.peek()) == ch) {
				stack.pop();
			} else {
				valid = false;
				break;
			}
		}

		if (valid && !stack.isEmpty()) {
			valid = false;
		}

		return valid;
	}

	public void test_isValid(String s){
		System.out.println("Data = " + s);
		long startTime = System.currentTimeMillis();
		boolean result = isValid(s);
		long stopTime = System.currentTimeMillis();
		System.out.println("Result = " + result);
		System.out.println("Time (ms) = " + (stopTime - startTime + 1));
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args){
		ValidParentheses solution = new ValidParentheses();

		solution.test_isValid("()");
		solution.test_isValid("()[]{}");
		solution.test_isValid("(]");
		solution.test_isValid("([)]");
		solution.test_isValid("{[]}");
		solution.test_isValid("]");
		solution.test_isValid("(((");
	}
}
