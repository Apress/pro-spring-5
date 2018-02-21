package com.apress.prospring5.ch16.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Created by iuliana.cosmina on 8/16/16.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		try {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			auth
					.inMemoryAuthentication()
					.passwordEncoder(passwordEncoder)
					.withUser("user").password(passwordEncoder.encode("user")).roles("USER");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/*").permitAll()
				.and()
				.formLogin()
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/login")
				.loginPage("/singers")
				.failureUrl("/security/loginfail")
				.defaultSuccessUrl("/singers")
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/singers")
				.and()
				.csrf().disable();
		//csrfTokenRepository(repo());
	}

	//@Bean
	public CsrfTokenRepository repo() {
		HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
		repo.setParameterName("_csrf");
		repo.setHeaderName("X-CSRF-TOKEN");
		return repo;
	}
}