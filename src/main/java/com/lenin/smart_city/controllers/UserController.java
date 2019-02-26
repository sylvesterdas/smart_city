package com.lenin.smart_city.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView result = new ModelAndView();
		result.setViewName("index");
		return result;
	}
	

}
