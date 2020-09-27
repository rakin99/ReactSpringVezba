package com.example.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bioskop.model.TipProjekcije;


@Component
public interface TipProjekcijeRepository extends JpaRepository<TipProjekcije, Long>{

	TipProjekcije findByNaziv(String naziv);
}
