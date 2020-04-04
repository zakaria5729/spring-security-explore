package com.zakaria.spring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zakaria.spring.model.User;
import com.zakaria.spring.model.UserPrinciple;
import com.zakaria.spring.repository.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public UserPrincipalDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		UserPrinciple userPrinciple = new UserPrinciple(user);
		return userPrinciple;
	}
}
