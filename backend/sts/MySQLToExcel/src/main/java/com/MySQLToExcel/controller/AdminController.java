package com.MySQLToExcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}

	@GetMapping("/booking")
	public String showBooking() {
		return "booking";
	}

	@GetMapping("/contactus")
	public String showContactUs() {
		return "contactus";
	}

}
