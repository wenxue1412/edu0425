package edu0425.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Spring Boot";
	}
}
