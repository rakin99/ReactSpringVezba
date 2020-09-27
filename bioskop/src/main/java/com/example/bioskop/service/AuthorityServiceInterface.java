package com.example.bioskop.service;

import java.util.List;

import com.example.bioskop.model.Authority;

public interface AuthorityServiceInterface {
	List<Authority> findById(Long id);
	List<Authority> findByName(String name);
}
