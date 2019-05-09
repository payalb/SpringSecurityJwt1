package com.example.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {
	
	private String username;
	private long id;
	private List<GrantedAuthority> authorities= new ArrayList<>();
	private String token;

	public JwtUserDetails(String username, long id,String token, List<GrantedAuthority> authorities) {
		this.username= username;
		this.id= id;
		this.authorities= authorities;
		this.token= token;
	}

	public String getUsername() {
		return username;
	}

	public long getId() {
		return id;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getToken() {
		return token;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
