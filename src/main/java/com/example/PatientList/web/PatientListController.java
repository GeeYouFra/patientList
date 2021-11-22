package com.example.PatientList.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.PatientList.domain.Message;

@Controller

public class PatientListController {
	
	@GetMapping("/form")
	public String formAccess(Model model) {
		model.addAttribute("message", new Message());
		return "form";
	}
	
	@GetMapping("/result")
	public String resultAccess(Model model) {
		return "result";
	}
	
	@PostMapping("/hello")
	public String greetingSubmit(@ModelAttribute Message msg, Model model) {
		model.addAttribute("message", msg);
		return "result";
	}

	@RequestMapping("/add")
	public String addHello(@RequestParam(name = "firstname") String firstName) {
		return "Hello " + firstName;
	}

	@RequestMapping("/greeting")
	public String addHelloAgain(@RequestParam(name = "name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

	@RequestMapping("/message")
	public String messages(Model model) {
		Message message1 = new Message();
		message1.setContent("Moon");
		Message message2 = new Message();
		message2.setContent("Uranus");
		Message message3 = new Message();
		message3.setContent("Mars");

		List<Message> messages = new ArrayList<Message>();
		messages.add(message1);
		messages.add(message2);
		messages.add(message3);

		model.addAttribute("messages", messages);
		return "messagelist";
	}
}
