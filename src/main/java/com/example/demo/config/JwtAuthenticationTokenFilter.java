package com.example.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{
	public JwtAuthenticationTokenFilter() {
		super("/v1/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String token=request.getHeader("Authorization");
		if(token == null || !token.startsWith("Bearer"))
		{
			throw new RuntimeException("Invalid Token or is missing");
		}
		JwtAuthenticationToken jtoken= new JwtAuthenticationToken(token.substring(6));
		return getAuthenticationManager().authenticate(jtoken);
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	    super.setAuthenticationManager(authenticationManager);
	}
@Override
protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
	super.successfulAuthentication(request, response, chain, authResult);
	chain.doFilter(request, response);//after this filter, proceed to other spring filters
}
}
