package com.apress.prospring5.ch12.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by iuliana.cosmina on 8/16/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		try {
			auth.inMemoryAuthentication().withUser("prospring5").password("prospring5").roles("REMOTE");
		} catch (Exception e) {
			logger.error("Could not configure authentication!", e);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/rest/**").hasRole("REMOTE").anyRequest().authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic()
				.and()
				.csrf().disable();
	}
}