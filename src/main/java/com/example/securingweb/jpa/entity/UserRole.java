package com.example.securingweb.jpa.entity;

import javax.persistence.*;

@Entity
public class UserRole {

	@EmbeddedId
	private UserRoleId id;

	public UserRoleId getId() {
		return id;
	}

}
