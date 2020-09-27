package com.example.bioskop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bioskop.ModelDTO.FilmDTO;
import com.example.bioskop.ModelDTO.KartaDTO;
import com.example.bioskop.model.Authority;

import com.example.bioskop.model.Karta;
import com.example.bioskop.model.Projekcija;
import com.example.bioskop.model.Sediste;
import com.example.bioskop.model.User;
import com.example.bioskop.repository.SedisteRepository;
import com.example.bioskop.service.KartaService;
import com.example.bioskop.service.ProjekcijaService;
import com.example.bioskop.service.UserService;
import com.example.bioskop.tools.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/bioskop")
public class KartaController {

	@Autowired
	private KartaService kartaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	private SedisteRepository sedisteRepository;
	
	@GetMapping(value = "/karte/{username}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<KartaDTO>> getKarte(@PathVariable("username") String username) throws ParseException{
		System.out.println("\n\n\t------>Ulazim u get Karte<----------");
		
		System.out.println("Username: "+username);
		List<Karta> karte=kartaService.findByKupac_username(username);
		System.out.println("Username: "+username);
		System.out.println("Broj karata: "+karte.size());
		List<KartaDTO> karteDTO=new ArrayList<KartaDTO>();
		for (Karta karta : karte) {
			KartaDTO kartaDTO=new KartaDTO(karta);
			karteDTO.add(kartaDTO);	
		}
		
		karteDTO.sort(Comparator.comparing(KartaDTO::getDatumVremeProdaje,String.CASE_INSENSITIVE_ORDER).reversed());
		
		return ResponseEntity.ok().body(karteDTO);
	}
	
	@PostMapping(value = "/karte/add")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Map<String, String>> newKarta(@RequestBody String s) throws ParseException{
		System.out.println("\n\n\t-------------->Post karta<---------------");
		String r="";
		Map<String, String> result = new HashMap<>();
		try {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject) parser.parse(s);
			KartaDTO kartaDTO = gson.fromJson(object, KartaDTO.class);
			
			User user = userService.findByUsername(kartaDTO.getKupac());
			
			Projekcija projekcija = projekcijaService.findById(Integer.parseInt(kartaDTO.getProjekcija()));

			Karta karta = new Karta();
			karta.setDatumVremeProdaje(DateUtil.convertFromDMYHM(DateUtil.convert(kartaDTO.getDatumVremeProdaje())));
			karta.setKupac(user);
			karta.setProjekcija(projekcija);
	  
			Sediste sediste=sedisteRepository.findByRedniBroj(Integer.parseInt(kartaDTO.getSediste()));
			karta.setSediste(sediste);
			karta = kartaService.save(karta);
			System.out.println("Sacuvana karta: "+karta.getId());
			
			r="succes";
		}catch (Exception e){
			r="error";
		}
		
		result.put("result", r);
		return ResponseEntity.accepted().body(result);
	}
	
}
