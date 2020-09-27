package com.example.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.bioskop.model.Film;
import com.example.bioskop.model.Sala;

@Component
public interface SalaRepository extends JpaRepository<Sala, Long>{

	Sala findById(Integer id);
}
