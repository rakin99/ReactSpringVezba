package com.example.bioskop.service;

import java.util.List;

import com.example.bioskop.model.User;

public interface UserServiceInterface {

	User save(User user);
	List<User> findAll();
	User findByUsername(String username);
}
