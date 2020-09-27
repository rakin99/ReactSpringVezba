package com.example.bioskop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bioskop.ModelDTO.SedisteDTO;
import com.example.bioskop.model.Karta;
import com.example.bioskop.model.Projekcija;
import com.example.bioskop.model.Sediste;
import com.example.bioskop.repository.SedisteRepository;
import com.example.bioskop.service.KartaService;
import com.example.bioskop.service.ProjekcijaService;
import com.example.bioskop.tools.DateUtil;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/bioskop")
public class SedistaController {
	
	@Autowired
	private SedisteRepository sedisteRepository;
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	private KartaService kartaService;
	
	@GetMapping(value = "/sedista/{projekcijaId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<SedisteDTO>> getSale(@PathVariable("projekcijaId") int projekcijaId) throws ParseException{
		System.out.println("\n\n\t------>Ulazim u GET sedista<----------");
		
		Projekcija projekcija=projekcijaService.findById(projekcijaId);
		
		List<Sediste> sedista=sedisteRepository.findBySala_id(projekcija.getSala().getId());
		List<Karta> karte = kartaService.findByProjekcija_id(projekcija.getId());
		List<SedisteDTO> sedistaKarata= new ArrayList<SedisteDTO>();
		for (Karta karta : karte) {
			sedistaKarata.add(new SedisteDTO(karta.getSediste()));
		}
		List<SedisteDTO> sedistaDTO=new ArrayList<SedisteDTO>();
		for (Sediste s : sedista) {
			if(sedistaKarata.size()!=0) {
				if(provera(sedistaKarata,s)) {
					SedisteDTO sedisteDTO=new SedisteDTO(s);
					sedistaDTO.add(sedisteDTO);
				}
			}else {
				SedisteDTO sedisteDTO=new SedisteDTO(s);
				sedistaDTO.add(sedisteDTO);
			}
		}
		
		return ResponseEntity.ok().body(sedistaDTO);
	}
	
	private boolean provera(List<SedisteDTO> sedista,Sediste sediste) {
		boolean provera = true;
		for (SedisteDTO sUKartama : sedista) {
			if(sUKartama.getRedniBroj()==sediste.getRedniBroj()) {
				provera = false;
			}
		}
		return provera;
	}

}
