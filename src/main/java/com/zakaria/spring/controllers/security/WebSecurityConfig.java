package com.zakaria.spring.controllers.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zakaria.spring.service.UserPrincipalDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserPrincipalDetailsService userPrincipalDetailsService;
	
	public WebSecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
//			.inMemoryAuthentication()
//			.withUser("admin").password(passwordEncoder().encode("admin123"))
//			.authorities("ACCESS1", "ACCESS2", "ROLE_ADMIN")
//			.and()
//			.withUser("user").password(passwordEncoder().encode("user123"))
//			.roles("USER")
//			.and()
//			.withUser("manager").password(passwordEncoder().encode("manager123"))
//			.authorities("ACCESS1", "ROLE_MANAGER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/profile/**").authenticated()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/management/**").hasAnyRole("MANAGER", "ADMIN")
			.antMatchers("/api/new1").hasAnyAuthority("ACCESS1")
			.antMatchers("/api/new2").hasAnyAuthority("ACCESS2")
			.antMatchers("/api/users").hasRole("ADMIN")
			.and()
			.httpBasic();
	}
	
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
		return daoAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
