package com.example.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bioskop.model.Film;

@Component
public interface FilmRepository extends JpaRepository<Film, Long>{

	Film findById(Integer id);
}
