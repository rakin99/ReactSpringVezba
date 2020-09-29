package com.example.bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bioskop.model.Karta;
import com.example.bioskop.repository.KartaRepository;

@Service
public class KartaService implements KartaServiceInterface{

	@Autowired
	KartaRepository kartaRepository;
	
	@Override
	public Karta save(Karta karta) {
		System.out.println("Save u KartaService");
		System.out.println("\n\nCuvam kartu za sediste: "+karta.getSediste().getRedniBroj()+"\n");
		return kartaRepository.save(karta);
	}

	@Override
	public List<Karta> findByProjekcija_id(Integer id) {
		return kartaRepository.findByProjekcija_id(id);
	}

	@Override
	public List<Karta> findByKupac_username(String username) {
		return kartaRepository.findByKupac_username(username);
	}

	@Override
	public List<Karta> findAll() {
		return kartaRepository.findAll();
	}

}
