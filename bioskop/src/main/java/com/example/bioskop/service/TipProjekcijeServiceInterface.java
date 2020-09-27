package com.example.bioskop.service;

import com.example.bioskop.model.TipProjekcije;

public interface TipProjekcijeServiceInterface {

	TipProjekcije findByNaziv(String naziv);
}
