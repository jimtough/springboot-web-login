package com.example.securingweb.security;

import java.util.*;
import java.util.stream.Collectors;

import com.example.securingweb.jpa.entity.User;
import com.example.securingweb.jpa.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MyUserPrincipal implements UserDetails {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyUserPrincipal.class);

	private final static String ROLE_PREFIX = "ROLE_";

	private final User user;
	private final List<UserRole> userRoles;

	MyUserPrincipal(User user, List<UserRole> userRoles) {
		this.user = Objects.requireNonNull(user, "user cannot be null");
		Objects.requireNonNull(userRoles, "userRoles cannot be null");
		this.userRoles = new ArrayList<>(userRoles);
		LOGGER.info("constructor | New instance of {} created | user: {} | roles: {}",
				UserDetails.class.getSimpleName(),
				user.getUsername(),
				userRoles.stream().map((ur)->ur.getId().getRolename()).collect(Collectors.joining(",")));
	}

	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		for (UserRole userRole : userRoles) {
			list.add(new SimpleGrantedAuthority(ROLE_PREFIX + userRole.getId().getRolename()));
		}
		return list;
	}

	@Override public String getPassword() {
		return "{noop}" + user.getPassword();
	}

	@Override public String getUsername() {
		return user.getUsername();
	}

	@Override public boolean isAccountNonExpired() {
		return true;
	}

	@Override public boolean isAccountNonLocked() {
		return user.isEnabled();
	}

	@Override public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override public boolean isEnabled() {
		return user.isEnabled();
	}

}
