package com.lawencon.assetsmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.assetsmanagement.security.jwt.JwtComponent;
import com.lawencon.assetsmanagement.service.UsersService;

//@Profile("kkkk")
@EnableWebSecurity
public class AppSercurity extends WebSecurityConfigurerAdapter{
	@Autowired
	private UsersService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private JwtComponent jwtComponent;
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
		http.addFilter(new AuthenticationFilter(objectMapper, super.authenticationManager(), jwtComponent, userService));
		http.addFilter(new AuthorizationFilter(super.authenticationManager(), jwtComponent, objectMapper));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(HttpMethod.POST, "/users")
		.antMatchers(HttpMethod.GET, "/employees/excel", "/assets/excel", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
						"/assets/pic/**", "/track-activities/pdf", "/companies/search/**", "/assets/pdf", 
						"/transactions-out/pdf", "/detail-transactions-out/pdf", "/transactions-in/pdf", "/files/img/**");
	}

}
