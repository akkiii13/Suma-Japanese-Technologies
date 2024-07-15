package com.sjtxev.sjt_spring_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjtxev.sjt_spring_security.services.User1Service;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private User1Service user1Service;

//	@GetMapping("/user")
//	public List<CompanyUsers> getUser() {
//		return user1Service.;
//	}

	@GetMapping("/user1")
	public String getUser() {
		return "User1";
	}

}
