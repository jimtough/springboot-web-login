package com.example.securingweb.jpa.entity;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true, length = 24)
	private String username;

	@Column(nullable = false, length = 24)
	private String password;

	private boolean enabled;

	//-------------------------------------------------------------------------

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
