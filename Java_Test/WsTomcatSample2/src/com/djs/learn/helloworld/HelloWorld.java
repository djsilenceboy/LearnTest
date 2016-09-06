
package com.djs.learn.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * http://localhost:8088/WsTomcatSample2/hello
 */
@Path("/hello")
public class HelloWorld
{
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String sayXMLHello(){
		return "<?xml version=\"1.0\"?><hello>Hello Jersey</hello>";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayJsonHello(){
		return "{title: \"Hello Jersey\"}";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(){
		return "<html><title>Hello Jersey</title><body><h1>Hello Jersey</h1></body></html>";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(){
		return "Hello Jersey";
	}
}
