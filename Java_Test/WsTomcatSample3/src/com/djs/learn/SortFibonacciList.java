
package com.djs.learn;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

/*
 * http://localhost:8080/WsTomcatSample3/SortFibonacciList/j/{Numbers}
 * http://localhost:8080/WsTomcatSample3/SortFibonacciList/h/{Numbers}
 */
@Path("/SortFibonacciList")
public class SortFibonacciList
{
	public static Logger log = Logger.getLogger(SortFibonacciList.class);

	private String getFibonacciListUrl = "http://localhost:8080/WsTomcatSample2/GetFibonacciList/j/Numbers";
	private Fibonacci fibonacci = new Fibonacci();

	private List<Long> getFibonacciList(String url){
		List<Long> fib_result = null;
		Client client = null;

		try {
			client = ClientBuilder.newClient();
			fib_result = client.target(url).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Long>>() {});
		} catch (Exception e) {
			log.error("getFibonacciList failed. Exception = " + e);
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return fib_result;
	}

	@GET
	@Path("/j/{Numbers}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> sortFibonacciListJson(@PathParam("Numbers") int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new javax.ws.rs.BadRequestException("Input number should >= 1.");
		}

		String url = getFibonacciListUrl.replaceFirst("Numbers", Integer.toString(number));

		List<Long> fib_result = getFibonacciList(url);
		log.info("Gen(" + number + ") = " + fib_result);

		List<Long> sorted_result = null;
		if (fib_result != null) {
			sorted_result = fibonacci.sort(fib_result);
		}

		log.info("Sort(" + number + ") = " + sorted_result);

		return sorted_result;
	}

	@GET
	@Path("/h/{Numbers}")
	@Produces(MediaType.TEXT_HTML)
	public String sortFibonacciListHtml(@PathParam("Numbers") int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new javax.ws.rs.BadRequestException("Input number should >= 1.");
		}

		String url = getFibonacciListUrl.replaceFirst("Numbers", Integer.toString(number));

		List<Long> fib_result = getFibonacciList(url);
		log.info("Gen(" + number + ") = " + fib_result);

		List<Long> sorted_result = null;
		if (fib_result != null) {
			sorted_result = fibonacci.sort(fib_result);
		}

		log.info("Sort(" + number + ") = " + sorted_result);

		String html_resulot = "<html><title>Fibonacci List</title><body>" + sorted_result + "</body></html>";

		return html_resulot;
	}
}
