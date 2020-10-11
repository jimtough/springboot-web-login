package com.example.securingweb.jpa.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable {

	private final int userId;
	private final String rolename;

	// According to the JPA specification, every @Embeddable class must have a no-arg constructor
	public UserRoleId() {
		userId = -1;
		rolename = "not initialized";
	};

	public UserRoleId(int userId, String rolename) {
		this.userId = userId;
		this.rolename = Objects.requireNonNull(rolename, "rolename cannot be null");
	}

	public int getUserId() {
		return userId;
	}

	public String getRolename() {
		return rolename;
	}

	@Override public boolean equals(final Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }
		final UserRoleId that = (UserRoleId) o;
		return userId == that.userId &&
				rolename.equals(that.rolename);
	}

	@Override public int hashCode() {
		return Objects.hash(userId, rolename);
	}

}
