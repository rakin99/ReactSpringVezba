package com.example.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bioskop.model.Sediste;

@Component
public interface SedisteRepository extends JpaRepository<Sediste, Long>{

	List<Sediste> findBySala_id(Integer id);
	Sediste findByRedniBroj(Integer id);
}
