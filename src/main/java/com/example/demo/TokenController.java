package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtGenerator;
import com.example.demo.config.JwtUser;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired JwtGenerator generator;
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public String getToken(@RequestBody JwtUser  user) {
		return generator.generate(user);
	}

}
