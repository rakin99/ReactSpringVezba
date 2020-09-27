package com.example.bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bioskop.model.Sala;
import com.example.bioskop.repository.SalaRepository;

@Service
public class SalaService implements SalaServiceInterface{

	@Autowired
	SalaRepository salaRepository;
	

	@Override
	public List<Sala> findAll() {
		return salaRepository.findAll();
	}


	@Override
	public Sala findById(Integer id) {
		return salaRepository.findById(id);
	}
	
}
