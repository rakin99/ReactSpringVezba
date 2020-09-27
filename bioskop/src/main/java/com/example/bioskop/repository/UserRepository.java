package com.example.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bioskop.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
}
