package com.example.bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bioskop.model.Film;
import com.example.bioskop.repository.FilmRepository;

@Service
public class FilmService implements FilmServiceInterface{

	@Autowired
	FilmRepository filmRepository;
	
	@Override
	public Film save(Film film) {
		System.out.println("Save u FilmService");
		return filmRepository.save(film);
	}

	@Override
	public List<Film> findAll() {
		return filmRepository.findAll();
	}

	@Override
	public Film findById(Integer id) {
		return filmRepository.findById(id);
	}
	
}
