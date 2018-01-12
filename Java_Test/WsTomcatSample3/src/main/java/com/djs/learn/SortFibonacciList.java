
package com.djs.learn;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

/*
 * GET: http://localhost:8080/WsTomcatSample3/SortFibonacciList/j/{Number}
 * GET: http://localhost:8080/WsTomcatSample3/SortFibonacciList/q?Numbers=1&Numbers=1&Numbers=2&Numbers=3&Numbers=5&Numbers=8&Numbers=13&Numbers=21
 * POST: http://localhost:8080/WsTomcatSample3/SortFibonacciList/f with Number={Number}
 */
@Path("/SortFibonacciList")
public class SortFibonacciList
{
	public static Logger log = Logger.getLogger(SortFibonacciList.class);

	private String getFibonacciListUrl = "http://localhost:8080/WsTomcatSample2/GetFibonacciList/j/Number";
	private Fibonacci fibonacci = new Fibonacci();

	private List<Long> getRemoteFibonacciList(String url){
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

	private List<Long> getFibonacciList(int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new javax.ws.rs.BadRequestException("Input number should >= 1.");
		}

		String url = getFibonacciListUrl.replaceFirst("Number", Integer.toString(number));

		List<Long> fibResult = getRemoteFibonacciList(url);
		log.info("Gen(" + number + ") = " + fibResult);

		return fibResult;
	}

	@GET
	@Path("/j/{Number}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> sortFibonacciList1_Json(@PathParam("Number") int number){
		log.info("Enter");
		List<Long> fibResult = getFibonacciList(number);

		List<Long> sortedResult = null;
		if (fibResult != null) {
			sortedResult = fibonacci.sort(fibResult);
		}

		log.info("Sort(" + number + ") = " + sortedResult);

		return sortedResult;
	}

	@GET
	@Path("/q")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> sortFibonacciList2_Json(@QueryParam("Numbers") List<Long> numberList){
		log.info("Enter");
		log.info("Original = " + numberList);

		List<Long> sortedResult = null;

		if (numberList != null) {
			sortedResult = fibonacci.sort(numberList);
		}

		log.info("Sorted = " + sortedResult);

		return sortedResult;
	}

	@POST
	@Path("/f")
	@Produces(MediaType.APPLICATION_JSON)
	// Must use a wrap class.
	public List<Long> sortFibonacciList3_Json(NumberList numberList){
		log.info("Enter");
		log.info("Original = " + numberList);

		List<Long> sortedResult = null;

		if (numberList != null) {
			log.info("Original = " + numberList.numbers);

			if (numberList.numbers != null) {
				sortedResult = fibonacci.sort(numberList.numbers);
			}
		}

		log.info("Sorted = " + sortedResult);

		return sortedResult;
	}
}
