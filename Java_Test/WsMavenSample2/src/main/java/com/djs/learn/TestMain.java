
package com.djs.learn;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

public class TestMain
{
	public static Logger log = Logger.getLogger(TestMain.class);

	private String getFibonacciListUrl_Get = "http://localhost:8080/WsTomcatSample2/GetFibonacciList/j/Number";
	private String sortFibonacciListUrl_Get = "http://localhost:8080/WsTomcatSample3/SortFibonacciList/j/Number";

	private String getFibonacciListUrl_Post = "http://localhost:8080/WsTomcatSample2/GetFibonacciList/u";
	private String sortFibonacciListUrl_Post = "http://localhost:8080/WsTomcatSample3/SortFibonacciList/f";

	private List<Long> doGet(String url){
		List<Long> fibResult = null;
		Client client = null;

		try {
			client = ClientBuilder.newClient();
			fibResult = client.target(url).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Long>>() {});
		} catch (Exception e) {
			log.error("getFibonacciList failed. Exception = " + e);
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return fibResult;
	}

	private List<Long> doPost1(String url, int number){
		List<Long> fibResult = null;
		Client client = null;

		try {
			Form form = new Form();
			form.param("Number", Integer.toString(number));

			client = ClientBuilder.newClient();
			fibResult = client.target(url).request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED),
			                                                                        new GenericType<List<Long>>() {});
		} catch (Exception e) {
			log.error("getFibonacciList failed. Exception = " + e);
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return fibResult;
	}

	private List<Long> doPost2(String url, NumberList numberList){
		List<Long> fibResult = null;
		Client client = null;

		try {
			client = ClientBuilder.newClient();
			fibResult = client.target(url).request(MediaType.APPLICATION_JSON).post(Entity.json(numberList), new GenericType<List<Long>>() {});
		} catch (Exception e) {
			log.error("getFibonacciList failed. Exception = " + e);
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return fibResult;
	}

	public List<Long> getFibonacciList_Get_1(int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new BadRequestException("Input number should >= 1.");
		}

		String url = getFibonacciListUrl_Get.replaceFirst("Number", Integer.toString(number));

		List<Long> fibResult = doGet(url);
		log.info("Get(" + number + ") = " + fibResult);

		return fibResult;
	}

	public List<Long> getFibonacciList_Post_1(int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new BadRequestException("Input number should >= 1.");
		}

		List<Long> fibResult = doPost1(getFibonacciListUrl_Post, number);
		log.info("Post(" + number + ") = " + fibResult);

		return fibResult;
	}

	public List<Long> sortFibonacciList_Get_1(int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new BadRequestException("Input number should >= 1.");
		}

		String url = sortFibonacciListUrl_Get.replaceFirst("Number", Integer.toString(number));

		List<Long> fibResult = doGet(url);
		log.info("Get(" + number + ") = " + fibResult);

		return fibResult;
	}

	public List<Long> sortFibonacciList_Post_1(NumberList numberList){
		log.info("numberList = " + numberList.getNumbers());

		List<Long> fibResult = doPost2(sortFibonacciListUrl_Post, numberList);
		log.info("Post(" + numberList + ") = " + fibResult);

		return fibResult;
	}

	public static void main(String[] args){
		TestMain test = new TestMain();

		System.out.println("============================================================");
		test.getFibonacciList_Get_1(10);
		System.out.println("----------------------------------------");
		test.getFibonacciList_Post_1(10);
		System.out.println("----------------------------------------");
		test.sortFibonacciList_Get_1(10);
		System.out.println("----------------------------------------");
		NumberList numberList = new NumberList();
		numberList.setNumbers(new ArrayList<Long>());
		long[] numbers = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
		for (long n : numbers) {
			numberList.getNumbers().add(n);
		}
		test.sortFibonacciList_Post_1(numberList);
		System.out.println("============================================================");
	}
}
