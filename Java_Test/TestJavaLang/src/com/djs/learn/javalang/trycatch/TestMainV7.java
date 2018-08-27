
package com.djs.learn.javalang.trycatch;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;

public class TestMainV7
{
	public void testMultiple(){
		try {

		} catch (NullPointerException | IllegalArgumentException e) {
		}

		// Compiling error: "The exception FileNotFoundException is already caught by the alternative IOException"
		/*
		try {
		
		} catch (FileNotFoundException | IOException e) {
		}
		*/
	}

	public void testRethrow() throws IOException, SQLException{
		// Compiling error: "Unreachable catch block for IOException. This exception is never thrown from the try statement body"
		/*
		try {

		} catch (IOException e) {
			throw e;
		}
		*/

		// Throw the common super class.
		try {

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * Try-Resource supports AutoCloseable and Closeable interfaces.
	 */
	public void testResource(){
		try (ResourceA res1 = new ResourceA("1"); ResourceA res2 = new ResourceA("2")) {
			throw new Exception("Self-Defined");
		} catch (Exception e) {
			System.err.println("ResourceA::catch = " + e);
		} finally {
			System.out.println("ResourceA::finally");
		}

		System.out.println("----------------------------------------");

		try (ResourceA res1 = new ResourceA("1"); ResourceA res2 = new ResourceA("2")) {

		} catch (Exception e) {
			System.err.println("ResourceA::catch = " + e);
		}

		System.out.println("----------------------------------------");

		try (ResourceB res1 = new ResourceB("1"); ResourceB res2 = new ResourceB("2")) {

		} finally {
			System.out.println("ResourceB::finally");
		}

		System.out.println("----------------------------------------");

		try (ResourceB res1 = new ResourceB("1"); ResourceB res2 = new ResourceB("2")) {

		}

		System.out.println("----------------------------------------");

		try (ResourceC res1 = new ResourceC("1"); ResourceC res2 = new ResourceC("2")) {
			throw new Exception("Self-Defined");
		} catch (Exception e) {
			System.err.println("ResourceC::catch = " + e);
			if (e.getSuppressed() != null) {
				System.err.println("ResourceC::Suppressed = " + e.getSuppressed()[0].getMessage());
				System.err.println("ResourceC::Suppressed = " + e.getSuppressed()[1].getMessage());
			}
		}

		System.out.println("----------------------------------------");

		try (ResourceD res1 = new ResourceD("1"); ResourceE res2 = new ResourceE("2")) {

		} catch (Exception e) {
			System.err.println("ResourceD::catch = " + e);
		}
	}

	public static void main(String[] args){
		TestMainV7 test = new TestMainV7();

		System.out.println("============================================================");

		test.testMultiple();

		System.out.println("============================================================");

		try {
			test.testRethrow();
		} catch (Exception e) {
		}

		System.out.println("============================================================");

		test.testResource();

		System.out.println("============================================================");
	}
}

class ResourceA implements AutoCloseable
{
	String id;

	public ResourceA(String id){
		this.id = id;
	}

	@Override
	public void close() throws Exception{
		System.out.println("ResourceA::Close(" + id + ")");
	}
}

class ResourceB implements AutoCloseable
{
	String id;

	public ResourceB(String id){
		this.id = id;
	}

	// "throws Exception" can be omitted.
	@Override
	public void close(){
		System.out.println("ResourceB::Close(" + id + ")");
	}
}

class ResourceC implements AutoCloseable
{
	String id;

	public ResourceC(String id){
		this.id = id;
	}

	@Override
	public void close() throws Exception{
		throw new Exception("ResourceC(" + id + ")");
	}
}

class ResourceD implements Closeable
{
	String id;

	public ResourceD(String id){
		this.id = id;
	}

	@Override
	public void close() throws IOException{
		throw new IOException("ResourceC(" + id + ")");
	}
}

class ResourceE implements Closeable
{
	String id;

	public ResourceE(String id){
		this.id = id;
	}

	// "throws Exception" can be omitted.
	@Override
	public void close(){
		System.out.println("ResourceE::Close(" + id + ")");
	}
}
