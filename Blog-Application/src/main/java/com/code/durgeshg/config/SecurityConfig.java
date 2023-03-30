package com.code.durgeshg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.code.durgeshg.security.CustomerUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	@Autowired
//	private CustomerUserDetailService customerUserDetailService;
	
	//basic auothication
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	      .csrf()
	      .disable()
	      .authorizeHttpRequests()
	      .anyRequest()
	      .authenticated()
	      .and()
	      .httpBasic();//type of request
	        
return http.build();
	    
	}
	
//	@Autowired
//  public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//      builder.userDetailsService(customerUserDetailService)
//              .passwordEncoder(passwordEncoder());
//		
//		
//  }
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
}
