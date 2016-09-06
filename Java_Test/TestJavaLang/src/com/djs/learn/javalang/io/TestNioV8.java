
package com.djs.learn.javalang.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestNioV8
{
	String directoryName = "etc";
	String fileName = "etc/data/SampleWords_ANSI_Eng.txt";

	public void testWalk(){
		System.out.println("Test = Walk");

		Path path = Paths.get(directoryName);
		System.out.println("Path = " + path);
		System.out.println("--------------------");

		try {
			Files.walk(path).forEach(System.out::println);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testFind(){
		System.out.println("Test = Find");

		Path path = Paths.get(directoryName);
		System.out.println("Path = " + path);
		System.out.println("--------------------");

		try {
			Files.find(path, 10, (p, attr) -> p.toString().endsWith(".txt") && attr.size() > 20).forEach(System.out::println);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testList(){
		System.out.println("Test = List");

		Path path = Paths.get(directoryName);
		System.out.println("Path = " + path);
		System.out.println("--------------------");

		try {
			// Only deep 1.
			Files.list(path).filter(p -> Files.isDirectory(path)).forEach(System.out::println);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testLines(){
		System.out.println("Test = Lines");

		Path path = Paths.get(fileName);
		System.out.println("Path = " + path);
		System.out.println("--------------------");

		try {
			Files.lines(path).forEach(System.out::println);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestNioV8 test = new TestNioV8();

		System.out.println("========================================");

		test.testWalk();

		System.out.println("========================================");

		test.testFind();

		System.out.println("========================================");

		test.testList();

		System.out.println("========================================");

		test.testLines();

		System.out.println("========================================");
	}
}
