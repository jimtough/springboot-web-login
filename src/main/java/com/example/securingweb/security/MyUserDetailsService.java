package com.example.securingweb.security;

import javax.annotation.PostConstruct;

import java.util.List;

import com.example.securingweb.jpa.entity.User;
import com.example.securingweb.jpa.entity.UserRole;
import com.example.securingweb.jpa.repository.UserRepository;
import com.example.securingweb.jpa.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

	@PostConstruct
	void postConstruct() {
		LOGGER.info("postConstruct() | INVOKED");
		LOGGER.info("postConstruct() | >>> This is my implementation of the Spring Security '{}' interface <<<", UserDetailsService.class.getSimpleName());
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		LOGGER.info("loadUserByUsername() | [{}]", username);
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		LOGGER.info("Retrieved user | [{}]", username);
		List<UserRole> userRoles = userRoleRepository.findByIdUserId(user.getId());
		LOGGER.info("Retrieved {} roles for user [{}]", userRoles.size(), user.getUsername());
		return new MyUserPrincipal(user, userRoles);
	}

}
