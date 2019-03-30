
package com.djs.learn.javalang.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestIo2
{
	public static void main(String[] args){
		try {
			InputStreamReader isReader = new InputStreamReader(System.in);
			BufferedReader bufReaer = new BufferedReader(isReader);
			System.out.print("Input (then enter): ");
			String line = bufReaer.readLine();
			System.out.println("That is : " + line);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("==========");

		try {
			InputStreamReader isReader = new InputStreamReader(System.in);
			BufferedReader bufReaer = new BufferedReader(isReader);

			while (true) {
				System.out.print("Input (then enter): ");

				Thread.sleep(100);

				if (bufReaer.ready()) {
					String line = bufReaer.readLine();
					System.out.println("That is : " + line);

					if (line.equalsIgnoreCase("q")) {
						System.out.println("Let's break!");

						break;
					}
				} else {
					System.out.println("");
				}
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("==========");

		try {
			while (true) {
				InputStreamReader isReader = new InputStreamReader(System.in);
				BufferedReader bufReaer = new BufferedReader(isReader);

				System.out.print("Input (then enter): ");

				Thread.sleep(100);

				if (bufReaer.ready()) {
					char ch = (char)bufReaer.read();

					System.out.println("That is : " + ch);

					if (Character.toString(ch).equalsIgnoreCase("q")) {
						System.out.println("Let's break!");

						break;
					}
				} else {
					System.out.println("");
				}
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("==========");
	}
}
