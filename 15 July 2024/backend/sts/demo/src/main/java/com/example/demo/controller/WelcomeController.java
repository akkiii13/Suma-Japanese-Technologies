package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WelcomeController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}

	@GetMapping("/booking")
	public String showBooking() {
		return "booking";
	}

	@GetMapping("/contactUs")
	public String showContactUs() {
		return "contactUs";
	}

}
