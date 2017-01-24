
package com.djs.learn.mvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// These two RequestMapping means "/", related to main context root.
// @RequestMapping
// @RequestMapping("/")

@Controller
@RequestMapping("/")
public class HomeController
{
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping
	public String mainroot(Model model){
		logger.info("[mainroot]");

		model.addAttribute("greeting", "Welcome to Web Store!");
		model.addAttribute("tagline", "The one and only amazing web store");

		return "welcome";
	}

	@RequestMapping("welcome_f")
	public String welcome_forward(Model model){
		logger.info("[welcome_forward]");

		// These attributes will be available after forward.
		model.addAttribute("greeting", "Welcome to Web Store! (forward)");
		model.addAttribute("tagline", "The one and only amazing web store (forward)");

		return "forward:/welcome";
	}

	@RequestMapping("welcome_r")
	public String welcome_redirect(Model model, RedirectAttributes redirectAttributes){
		logger.info("[welcome_redirect]");

		// These attributes will be not available after redirect.
		model.addAttribute("greeting", "Welcome to Web Store! (redirect 1)");
		model.addAttribute("tagline", "The one and only amazing web store (redirect 1)");

		// These flash attributes will be available after redirect.
		redirectAttributes.addFlashAttribute("greeting2", "Welcome to Web Store! (redirect 2)");
		redirectAttributes.addFlashAttribute("tagline2", "The one and only amazing web store (redirect 2)");

		return "redirect:/welcome";
	}

	// If call this url directly, there is no valid attributes.

	@RequestMapping("/welcome")
	// public String welcome(){
	public String welcome(Model model){
		logger.info("[welcome]");

		// The input parameter model can be omitted.
		// The model will not capture forward attributes.
		// The model will capture redirect flash attributes.

		logger.info("[welcome" + ": greeting = " + model.containsAttribute("greeting"));
		logger.info("[welcome" + ": tagline = " + model.containsAttribute("tagline"));
		logger.info("[welcome" + ": greeting2 = " + model.containsAttribute("greeting2"));
		logger.info("[welcome" + ": tagline2 = " + model.containsAttribute("tagline2"));

		return "welcome";
	}
}
