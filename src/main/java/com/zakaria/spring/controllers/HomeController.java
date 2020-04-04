package com.zakaria.spring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zakaria.spring.model.User;
import com.zakaria.spring.repository.UserRepository;

@RestController
public class HomeController {
	
	private UserRepository userRepository;

	public HomeController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
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
	
	@GetMapping("/api/new1")
	public String getNewAPi1() {
		return "new api1";
	}
	
	@GetMapping("/api/new2")
	public String getNewAPi2() {
		return "new api2";
	}
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
