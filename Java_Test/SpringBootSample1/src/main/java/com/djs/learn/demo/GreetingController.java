
package com.djs.learn.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController
{
	@Value("${greeting-myName: TomCat}")
	private String myName;
	@Value("${greeting-friendName: SupperMan}")
	private String friendName;
	@Value("${greeting-message: ${greeting-myName} say hello!}")
	private String message;

	@GetMapping
	String getGreeting(){
		return message;
	}

	@GetMapping("/names")
	String getNames(){
		return String.format("MyName[%s], FriendName[%s]", myName, friendName);
	}
}
