package com.lawencon.assetsmanagement.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.assetsmanagement.security.jwt.JwtComponent;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

public class AuthorizationFilter extends BasicAuthenticationFilter{
	private JwtComponent jwtComponent;
	private ObjectMapper objectMapper;
	
	public AuthorizationFilter(AuthenticationManager authenticationManager, JwtComponent jwtComponent, ObjectMapper objectMapper) {
		super(authenticationManager);
		this.jwtComponent = jwtComponent;
		this.objectMapper = objectMapper;
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		if(header == null || header.isEmpty() || !header.startsWith("Bearer")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		String idUser = null;
		try {
			String bodyToken = header.replaceFirst("Bearer ", "");
			Claims claims = jwtComponent.parseClaims(bodyToken);
			
			idUser = claims.get("idUser").toString();
			
			
		
		}catch (ExpiredJwtException e) {
			Map<String, Object> errMap = new HashMap<>();
			errMap.put("msg", "Expired Session");
			response.getWriter().append(objectMapper.writeValueAsString(errMap));
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			
			e.printStackTrace();
			return;
		} catch (ClaimJwtException e) {
			Map<String, Object> errMap = new HashMap<>();
			errMap.put("msg", "Invalid Session");
			response.getWriter().append(objectMapper.writeValueAsString(errMap));
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			e.printStackTrace();
			return;
		} catch (Exception e) {
			
			Map<String, Object> errMap = new HashMap<>();
			errMap.put("msg", NestedExceptionUtils.getRootCause(e).getMessage());
			response.getWriter().append(objectMapper.writeValueAsString(errMap));
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			e.printStackTrace();
			return;
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(idUser, null, new ArrayList<>());
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}		
}
