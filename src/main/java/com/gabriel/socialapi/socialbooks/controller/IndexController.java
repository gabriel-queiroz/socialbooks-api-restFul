package com.gabriel.socialapi.socialbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	
	@GetMapping("/site")
	public String index() {
		
		
		
		return "index";
	}
}
