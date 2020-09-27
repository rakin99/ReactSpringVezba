package com.example.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bioskop.model.Karta;

@Component
public interface KartaRepository extends JpaRepository<Karta, Integer>{
	List<Karta> findByProjekcija_id(Integer id);
	List<Karta> findByKupac_username(String username);
}
