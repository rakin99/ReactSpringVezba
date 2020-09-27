package com.example.bioskop.service;

import java.util.List;

import com.example.bioskop.model.Karta;

public interface KartaServiceInterface {

	Karta save(Karta karta);
	List<Karta> findByProjekcija_id(Integer id);
	List<Karta> findByKupac_username(String username);
}
