
package com.djs.learn.mvc.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private static final Logger logger = Logger.getLogger(SecurityConfig.class);

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		logger.info("[configureGlobalSecurity]");

		auth.inMemoryAuthentication().withUser("john").password("password").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER", "ADMIN");
	}

	// Test: http://localhost:8080/SprSample4/market/products/add
	// Only allow admin user.

	// Test: http://localhost:8080/SprSample4/market/products
	// Allow admin and john user.

	// Test: http://localhost:8080/SprSample4/login
	// Test: http://localhost:8080/SprSample4/login?error
	// Test: http://localhost:8080/SprSample4/login?logout
	// Test: http://localhost:8080/SprSample4/login?accessDenied

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		logger.info("[configure]");

		httpSecurity.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password").defaultSuccessUrl("/market/products/add")
		        .failureUrl("/login?error");

		httpSecurity.logout().logoutSuccessUrl("/login?logout");

		httpSecurity.exceptionHandling().accessDeniedPage("/login?accessDenied");

		httpSecurity.authorizeRequests().antMatchers("/").permitAll().antMatchers("/**/add").access("hasRole('ADMIN')").antMatchers("/**/market/**")
		        .access("hasRole('USER')");

		httpSecurity.csrf().disable();
	}
}
