package com.example.bioskop.service;

import java.util.List;

import com.example.bioskop.model.Film;

public interface FilmServiceInterface {

	Film save(Film film);
	List<Film> findAll();
	Film findById(Integer id);
}
