
package com.djs.learn;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

/*
 * http://localhost:8080/WsTomcatSample3/GetFibonacciList/{Numbers}
 */
@Path("/GetFibonacciList")
public class GetFibonacciList
{
	public static Logger log = Logger.getLogger(GetFibonacciList.class);

	@GET
	@Path("/{Numbers}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getFibonacciListJson(@PathParam("Numbers") int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new javax.ws.rs.BadRequestException("Input number should >= 1.");
		}

		Fibonacci f = new Fibonacci();
		List<Long> fib_result = f.generateNumber(number);
		log.info("Gen(" + number + ") = " + fib_result);

		return fib_result;
	}

	@GET
	@Path("/{Numbers}")
	@Produces(MediaType.TEXT_HTML)
	public String getFibonacciListHtml(@PathParam("Numbers") int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new javax.ws.rs.BadRequestException("Input number should >= 1.");
		}

		Fibonacci f = new Fibonacci();
		List<Long> fib_result = f.generateNumber(number);
		log.info("Gen(" + number + ") = " + fib_result);

		String html_resulot = "<html><title>Fibonacci List</title><body>" + fib_result + "</body></html>";

		return html_resulot;
	}
}
