
package com.djs.learn.jpmorgan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestMainTemplate
{
	// Test input:

	// Test output:

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			// System.out.println(s);

			try {
				process(s);
			} catch (Exception e) {
				System.err.println("Process input failed: " + e);
			}
		}
	}

	public static void process(String input) throws Exception{

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
