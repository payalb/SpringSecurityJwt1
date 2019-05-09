package com.example.demo.config;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtGenerator {

	public String generate(JwtUser user) {
		Claims claims= Jwts.claims().setSubject(user.getUsername());
				claims.put("userId", user.getId());
				claims.put("role", user.getRole());
				
				return Jwts.builder().setClaims(claims).
				signWith(SignatureAlgorithm.HS512, "secretKey").compact();
	}
}
