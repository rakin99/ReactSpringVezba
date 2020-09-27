package com.example.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bioskop.model.Authority;

@Component
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(String name);
}
