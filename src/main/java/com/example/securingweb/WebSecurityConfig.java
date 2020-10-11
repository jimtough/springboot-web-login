package com.example.securingweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

	private static final String TEST_USER_USERNAME = "user";
	private static final String TEST_USER_PASSWORD = "password";
	private static final String TEST_USER_ROLE = "USER";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		LOGGER.info("Defining simple test user '{}' with password '{}' whose role is '{}' | use this in login form",
				TEST_USER_USERNAME, TEST_USER_PASSWORD, TEST_USER_ROLE);
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username(TEST_USER_USERNAME)
				.password(TEST_USER_PASSWORD)
				.roles(TEST_USER_ROLE)
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
