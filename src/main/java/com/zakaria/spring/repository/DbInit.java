package com.zakaria.spring.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.zakaria.spring.model.User;

@Service
public class DbInit implements CommandLineRunner {

	private UserRepository userRepository;
	
	public DbInit(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("user", "user123", "USER", "");
		User admin = new User("admin", "admin123", "ADMIN", "ACCESS1,ACCESS2");
		User manager = new User("manager", "manager123", "MANAGER", "ACCESS1");
		
		//default user save to db
		List<User> users = Arrays.asList(user, admin, manager);
		userRepository.saveAll(users);
	}
}
