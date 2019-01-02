
package com.djs.learn.ctc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConcurrentTaskController
{
	public void start(){

	}

	public void stop(){

	}

	@RequestMapping("/")
	public String mainEntry(){
		return "Hello World!";
	}
}
