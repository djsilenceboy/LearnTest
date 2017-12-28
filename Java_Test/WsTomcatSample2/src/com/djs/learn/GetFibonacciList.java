
package com.djs.learn;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

/*
 * GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/u/{Number}
 * POST: http://localhost:8080/WsTomcatSample2/GetFibonacciList/u with Number={Number}
 * GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/j/{Number}
 * GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/h/{Number}
 * GET: http://localhost:8080/WsTomcatSample2/GetFibonacciList/q?Number={Number}
 */
@Path("/GetFibonacciList")
public class GetFibonacciList
{
	public static Logger log = Logger.getLogger(GetFibonacciList.class);

	private List<Long> getFibonacciList(int number){
		log.info("number = " + number);

		if (number <= 0) {
			throw new javax.ws.rs.BadRequestException("Input number should >= 1.");
		}

		Fibonacci f = new Fibonacci();
		List<Long> fibResult = f.generateNumber(number);
		log.info("Gen(" + number + ") = " + fibResult);

		return fibResult;
	}

	@GET
	@Path("/u/{Number}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getFibonacciList1_Json(@PathParam("Number") int number){
		log.info("Enter");
		return getFibonacciList(number);
	}

	@POST
	@Path("/u")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getFibonacciList1_Json2(@FormParam("Number") int number){
		log.info("Enter");
		return getFibonacciList(number);
	}

	@GET
	@Path("/u/{Number}")
	@Produces(MediaType.TEXT_HTML)
	public String getFibonacciList1_Html(@PathParam("Number") int number){
		log.info("Enter");
		List<Long> fibResult = getFibonacciList(number);
		String htmlResult = "<html><title>Fibonacci List</title><body>" + fibResult + "</body></html>";

		return htmlResult;
	}

	@GET
	@Path("/j/{Number}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getFibonacciList2_Json(@PathParam("Number") int number){
		log.info("Enter");
		return getFibonacciList(number);
	}

	@GET
	@Path("/h/{Number}")
	@Produces(MediaType.TEXT_HTML)
	public String getFibonacciList2_Html(@PathParam("Number") int number){
		log.info("Enter");
		List<Long> fibResult = getFibonacciList(number);
		String htmlResult = "<html><title>Fibonacci List</title><body>" + fibResult + "</body></html>";

		return htmlResult;
	}

	@GET
	@Path("/q")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getFibonacciList3_Json(@QueryParam("Number") int number){
		log.info("Enter");
		return getFibonacciList(number);
	}
}
