
package com.djs.learn.mvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController
{
	private static final Logger logger = Logger.getLogger(LoginController.class);

	// Test: http://localhost:8080/SprSample4/login

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		logger.info("[login]");

		return "login";
	}
}
