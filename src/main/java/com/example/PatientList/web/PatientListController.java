package com.example.PatientList.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class PatientListController {
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring";
	}
	
	@RequestMapping("/add")
	public String addHello(@RequestParam(name="firstname") String firstName) {
		return "Hello " + firstName;
	}
	
}
