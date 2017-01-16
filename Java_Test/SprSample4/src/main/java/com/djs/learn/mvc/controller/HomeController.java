
package com.djs.learn.mvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	private static final Logger logger = Logger.getLogger(HomeController.class);

	// These two means "/", related to main context root.
	// @RequestMapping
	// @RequestMapping("/")

	@RequestMapping("/welcome")
	public String welcome(Model model){
		logger.info(this.getClass().getName() + ":welcome");
		model.addAttribute("greeting", "Welcome to Web Store!");
		model.addAttribute("tagline", "The one and only amazing web store");

		return "welcome";
	}
}
