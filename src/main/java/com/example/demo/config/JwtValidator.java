package com.example.demo.config;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	private String secret= "secretKey";
	public JwtUser validate(String sToken) {
		JwtUser user= null;
		
		try{Claims body=(Claims) Jwts.parser().
					setSigningKey(secret).parse(sToken).getBody();
		user= new JwtUser();
		user.setUsername(body.getSubject());
		user.setId(Long.parseLong((String) body.get("userId")));
		user.setRole((String) body.get("role"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	

}
