package com.cowin.GetVaccine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomePageController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showWelcomePage() {
		
		return "index";
	}

}
