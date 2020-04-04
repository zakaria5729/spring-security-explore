package com.zakaria.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/user/profile")
	public String getProfile() {
		return "profile";
	}
	
	@GetMapping("/admin/profile")
	public String getAdmin() {
		return "admin";
	}
	

	@GetMapping("/management/profile")
	public String getManagement() {
		return "management";
	}
}
