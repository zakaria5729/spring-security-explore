package com.zakaria.spring.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zakaria.spring.model.User;

@Service
public class DbInit implements CommandLineRunner {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		this.userRepository.deleteAll();
		
		User user = new User("user", passwordEncoder.encode("user123"), "USER", "");
		User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS1,ACCESS2");
		User manager = new User("manager", passwordEncoder.encode("manager123"), "MANAGER", "ACCESS1");
		
		//default user save to db
		List<User> users = Arrays.asList(user, admin, manager);
		userRepository.saveAll(users);
	}
}
