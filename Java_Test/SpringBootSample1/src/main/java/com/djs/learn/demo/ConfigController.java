
package com.djs.learn.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController
{
	private final Greeting greeting;

	public ConfigController(Greeting greeting){
		this.greeting = greeting;
	}

	@GetMapping
	String getMessage(){
		return greeting.getMessage();
	}

	@GetMapping("/names")
	String getNames(){
		return String.format("MyName[%s], FriendName[%s]", greeting.getMyName(), greeting.getFriendName());
	}
}
