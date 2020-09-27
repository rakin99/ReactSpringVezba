package com.example.bioskop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bioskop.ModelDTO.SalaDTO;
import com.example.bioskop.model.Sala;
import com.example.bioskop.service.SalaService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/bioskop")
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	@GetMapping(value = "/sale")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<SalaDTO>> getSale(){
		System.out.println("\n\n\t------>Ulazim u GET SALE<----------");
		
		List<Sala> sale=salaService.findAll();
		List<SalaDTO> saleDTO=new ArrayList<SalaDTO>();
		for (Sala sala : sale) {
			SalaDTO filmDTO=new SalaDTO(sala);
			saleDTO.add(filmDTO);
		}
		
		return ResponseEntity.ok().body(saleDTO);
	}

}
