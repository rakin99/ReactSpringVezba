package com.example.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bioskop.model.Projekcija;

@Component
public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long>{

	Projekcija findById(Integer id);
	List<Projekcija> findBySala_id(Integer id);
	
}
