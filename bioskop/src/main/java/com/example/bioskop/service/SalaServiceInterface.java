package com.example.bioskop.service;

import java.util.List;

import com.example.bioskop.model.Sala;

public interface SalaServiceInterface {
	List<Sala> findAll();
	Sala findById(Integer id);
}
