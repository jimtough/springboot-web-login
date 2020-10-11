package com.example.securingweb.jpa.repository;

import com.example.securingweb.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
