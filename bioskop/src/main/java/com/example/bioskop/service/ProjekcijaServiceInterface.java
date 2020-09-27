package com.example.bioskop.service;

import java.util.List;

import com.example.bioskop.model.Projekcija;

public interface ProjekcijaServiceInterface {

	Projekcija save(Projekcija projekcija);
	List<Projekcija> findAll();
	Projekcija findById(Integer id);
}
