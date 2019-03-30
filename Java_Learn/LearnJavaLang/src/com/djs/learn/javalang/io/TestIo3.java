
package com.djs.learn.javalang.io;

import java.io.Console;

public class TestIo3
{
	public static void main(String[] args){
		Console console = System.console();

		System.out.println("Console = " + console);

		try {
			if (console != null) {
				String line = console.readLine();
				System.out.println("Input : " + line);
				console.writer().println("Output : " + line);
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}
}
