package com.example.bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bioskop.model.TipProjekcije;
import com.example.bioskop.repository.TipProjekcijeRepository;

@Service
public class TipProjekcijeService implements TipProjekcijeServiceInterface{

	@Autowired
	TipProjekcijeRepository tipProjekcijeRepository;

	@Override
	public TipProjekcije findByNaziv(String naziv) {
		return tipProjekcijeRepository.findByNaziv(naziv);
	}
	
}
