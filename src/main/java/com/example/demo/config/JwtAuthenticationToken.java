package com.example.demo.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken{

	//store token in thie file
	
	private String token;
	public JwtAuthenticationToken( String token) {
		super(null, null);
		this.token= token;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}
}
