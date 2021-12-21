package com.lawencon.assetsmanagement.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.assetsmanagement.dto.LoginReqDto;
import com.lawencon.assetsmanagement.dto.LoginResDto;
import com.lawencon.assetsmanagement.model.Users;
import com.lawencon.assetsmanagement.security.jwt.JwtComponent;
import com.lawencon.assetsmanagement.service.UsersService;

import io.jsonwebtoken.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authManager;
	private ObjectMapper objectMapper;
	private JwtComponent jwtComponent;
	private UsersService userService;
	
	public AuthenticationFilter (ObjectMapper objectMapper, AuthenticationManager authManager, JwtComponent jwtComponent,  UsersService userService) {
		this.objectMapper = objectMapper;
		this.authManager = authManager;
		this.jwtComponent = jwtComponent;
		this.userService = userService;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		LoginReqDto login = new LoginReqDto();
		try {
			login = objectMapper.readValue(request.getInputStream(), LoginReqDto.class);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		try {
			Users user = userService.findByEmail(authResult.getName()).getData();
			
			LoginResDto loginRes = new LoginResDto();
			
			loginRes.setToken(jwtComponent.generateToken(user.getId(), user.getEmail()));
			loginRes.setRoleCode(user.getRole().getCode());
			
			response.setContentType("application/json");
			
			response.getWriter().append(objectMapper.writeValueAsString(loginRes));
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws java.io.IOException, ServletException {
		Map<String, Object> errMap = new HashMap<>();
		errMap.put("msg", "Username atau password salah");
		
		response.getWriter().append(objectMapper.writeValueAsString(errMap));
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}
}
