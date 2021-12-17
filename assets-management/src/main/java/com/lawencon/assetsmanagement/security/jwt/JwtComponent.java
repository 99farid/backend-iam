package com.lawencon.assetsmanagement.sercurity.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtComponent {
	private String secretKey = "ftghjklsufrcvbnjmsrtyuciaoadfghnkmzxcvbhnjmkertyhjksxdcfvgbhnmdewqwerytuiklnbdszsxdcvbjhgfavdwbahnjmksdcfvgbhnjmsds";
	private SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	
	
	
	
	public String generateToken (String idUser, String email) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("idUser", idUser);
		claims.put("email", email);
		
		String token = Jwts.builder()
				.signWith(key)
				.setClaims(claims)
				.setExpiration(new Date(new Date().getTime() + 8640000)).compact();
		
		return token;
	}
	
	public Claims parseClaims (String token) throws Exception{
		
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
