package com.example.demo.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired private JwtAuthenticationEntryPoint entryPoint;
	@Autowired JwtAuthenticationTokenFilter filter;
	@Autowired JwtSuccessHandler handler;
	@Autowired JwtAuthenticationProvider provider;

		//use jwt filters
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
						.authorizeRequests()
						.antMatchers("**/v1/**")
						.authenticated()
						.and().exceptionHandling().
						authenticationEntryPoint(entryPoint)
						.and().sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					;
			
			http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
			
			http.headers().cacheControl();
		}
		@Bean 
		public JwtAuthenticationProvider provider() {
			return provider;
		}
		@Bean
		public AuthenticationManager authenticationManager() {
			return new ProviderManager(Collections.singletonList(provider));
		}
		
		@Bean
		public JwtAuthenticationTokenFilter authTokenFilter() {
			filter.setAuthenticationManager(authenticationManager());
			filter.setAuthenticationSuccessHandler(handler);
			return filter;
		}
		
		
}
