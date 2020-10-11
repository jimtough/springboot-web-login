package com.example.securingweb.jpa.repository;

import java.util.List;

import com.example.securingweb.jpa.entity.UserRole;
import com.example.securingweb.jpa.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

	List<UserRole> findByIdUserId(int userId);

}
