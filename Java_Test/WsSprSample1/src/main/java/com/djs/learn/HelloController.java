
package com.djs.learn;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * http://localhost:8088/WsSpringSample1/hello
 * http://localhost:8088/WsSpringSample1/hello?name=Jerry
 */
@RestController
public class HelloController
{
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/hello")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
