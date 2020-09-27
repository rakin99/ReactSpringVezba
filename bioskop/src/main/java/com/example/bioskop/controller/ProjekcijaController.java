package com.example.bioskop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bioskop.ModelDTO.ProjekcijaDTO;
import com.example.bioskop.ModelDTO.ProjekcijaString;
import com.example.bioskop.model.Film;
import com.example.bioskop.model.Projekcija;
import com.example.bioskop.model.Sala;
import com.example.bioskop.model.TipProjekcije;
import com.example.bioskop.model.User;
import com.example.bioskop.repository.ProjekcijaRepository;
import com.example.bioskop.service.FilmService;
import com.example.bioskop.service.ProjekcijaService;
import com.example.bioskop.service.SalaService;
import com.example.bioskop.service.TipProjekcijeService;
import com.example.bioskop.service.UserService;
import com.example.bioskop.tools.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/bioskop")
public class ProjekcijaController {
	
	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private TipProjekcijeService tipProjekcijeService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ProjekcijaRepository projekcijaRepository;
	
	@GetMapping(value = "/projekcije/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ProjekcijaDTO> getProjekcije(@PathVariable("id") String id) throws ParseException{
		System.out.println("\n\n\t------>Ulazim u get Projekcije<----------");
		
		Projekcija projekcija=projekcijaService.findById(Integer.parseInt(id));
		
		
		return ResponseEntity.ok().body(new ProjekcijaDTO(projekcija));
	}
	
	@GetMapping(value = "/projekcije/{film}/{vremeOd}/{vremeDo}/{tipProjekcije}/{sala}/{cenaOd}/{cenaDo}/{sort}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<ProjekcijaDTO>> getProjekcije(@PathVariable("film")String f,@PathVariable("vremeOd")String vOd,
			@PathVariable("vremeDo")String vDo,@PathVariable("tipProjekcije")String tip,@PathVariable("sala")String sa,
			@PathVariable("cenaOd")String cOd,@PathVariable("cenaDo")String cDo,@PathVariable("sort")String s) throws ParseException{
		System.out.println("\n\n\t------------->Get projekcije filter<--------------");

		String film="";
		GregorianCalendar vremeOd=new GregorianCalendar();
		GregorianCalendar vremeDo=new GregorianCalendar();
		vremeDo.setTime(new Date(Long.MAX_VALUE));
		String tipProjekcije="";
		String sala="";
		double cenaOd=0;
		double cenaDo=Double.MAX_VALUE;
		String sort="";
		
		if(!f.equals("null")) {
			film=f;
		}
		
		if(!vOd.equals("null")) {
			vremeOd=DateUtil.convertFromDMYHM(DateUtil.convert(vOd));
		}
		
		if(!vDo.equals("null")) {
			vremeDo=DateUtil.convertFromDMYHM(DateUtil.convert(vDo));
		}
		
		if(!tip.equals("null")) {
			tipProjekcije=tip;
		}
		
		if(!sa.equals("null")) {
			sala=sa;
		}
		
		if(!cOd.equals("null")) {
			cenaOd=Double.parseDouble(cOd);
		}
		
		if(!cDo.equals("null")) {
			cenaDo=Double.parseDouble(cDo);
		}
		
		if(!s.equals("null")) {
			sort=s;
		}
		
		List<Projekcija> projekcije=projekcijaService.findAll();
		List<ProjekcijaDTO> aktivneProjekcije=new ArrayList<ProjekcijaDTO>();
		for (Projekcija p : projekcije) {
			if(p.isActive()) {
				if(p.getFilm().getNaziv().toLowerCase().contains(film.toLowerCase())) {
					System.out.println("\n\n\tPrva provera: "+DateUtil.formatTime(p.getDatumVremePrikazivanja())+" > "+DateUtil.formatTime(vremeOd)+" ? "+DateUtil.CompareDate(p.getDatumVremePrikazivanja(), vremeOd));
					System.out.println("\tDruga provera: "+DateUtil.formatTime(vremeDo)+" > "+DateUtil.formatTime(p.getDatumVremePrikazivanja())+" ? "+(DateUtil.CompareDate(vremeDo, p.getDatumVremePrikazivanja()))+"\n\n");
					if((DateUtil.CompareDate(p.getDatumVremePrikazivanja(), vremeOd)) && (DateUtil.CompareDate(vremeDo, p.getDatumVremePrikazivanja()))) {
						if(p.getTipProjekcije().getNaziv().toLowerCase().contains(tipProjekcije.toLowerCase())){
							if(p.getSala().getNaziv().toLowerCase().contains(sala.toLowerCase())) {
								if(cenaOd<=p.getCenaKarte() && p.getCenaKarte()<=cenaDo) {
									aktivneProjekcije.add(new ProjekcijaDTO(p));
								}
							}
						}
					}
				}
			}
		}
		
		switch (sort) {
		case "f-asc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getFilm,String.CASE_INSENSITIVE_ORDER));
			break;
			
		case "f-desc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getFilm,String.CASE_INSENSITIVE_ORDER).reversed());
			break;
			
		case "v-asc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getDatumVremePrikazivanja));
			break;
			
		case "v-desc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getDatumVremePrikazivanja).reversed());
			break;
			
		case "t-asc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getTipProjekcije,String.CASE_INSENSITIVE_ORDER));
			break;
			
		case "t-desc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getTipProjekcije,String.CASE_INSENSITIVE_ORDER).reversed());
			break;
			
		case "s-asc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getSala,String.CASE_INSENSITIVE_ORDER));
			break;
			
		case "s-desc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getSala,String.CASE_INSENSITIVE_ORDER).reversed());
			break;
			
		case "c-asc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getCenaKarte));
			break;
			
		case "c-desc":
			System.out.println(sort);
			aktivneProjekcije.sort(Comparator.comparing(ProjekcijaDTO::getCenaKarte).reversed());
			break;

		default:
			break;
		}
		
		return ResponseEntity.ok().body(aktivneProjekcije);
	}
	
	@PostMapping(value = "/projekcije/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, String>> newProjekcija(@RequestBody String s) throws ParseException{
		System.out.println("\n\n\t------------>Ulazim u post projekcije<------------------");
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(s);// response will be the json String
		ProjekcijaString projekcijaString = gson.fromJson(object, ProjekcijaString.class); 
		
		Projekcija projekcija = new Projekcija();
		Film film=filmService.findById(Integer.parseInt(projekcijaString.getFilmId()));
		TipProjekcije tipProjekcije = tipProjekcijeService.findByNaziv(projekcijaString.getTipProjekcijeNaziv());
		Sala sala = salaService.findById(Integer.parseInt(projekcijaString.getSalaId()));
		User user = userService.findByUsername(projekcijaString.getNapravio());
		projekcija.setFilm(film);
		projekcija.setDatumVremePrikazivanja(DateUtil.convertFromDMYHM(DateUtil.convert(projekcijaString.getDatumVremePrikazivanja())));
		projekcija.setTipProjekcije(tipProjekcije);
		projekcija.setSala(sala);
		projekcija.setCenaKarte(Double.parseDouble(projekcijaString.getCenaKarte()));
		projekcija.setNapravio(user);
		projekcija.setActive(true);
		
		projekcija=projekcijaService.save(projekcija);
		Map<String, String> result = new HashMap<>();
		String r="";
		if(projekcija == null) {
			r="error";
		}
		else {
			String id="";
			String datum="";
			r="success";
			id=String.valueOf(projekcija.getId());
			datum=DateUtil.formatTime(projekcija.getDatumVremePrikazivanja());
			result.put("id",id);
			result.put("datum",datum);
		}
		
		result.put("result", r);
		
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping(value = "/projekcije/edit")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProjekcijaDTO> editProjekcija(@RequestBody String s) throws ParseException{
		System.out.println("\n\n\t------------>Ulazim u put filmovi<------------------");
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(s);// response will be the json String
		ProjekcijaDTO projekcijaDTO = gson.fromJson(object, ProjekcijaDTO.class);
		
		Projekcija projekcija=projekcijaService.findById(projekcijaDTO.getId());
//		projekcija.setFilm(projekcijaDTO.getFilm());
//		projekcija.setDatumVremePrikazivanja(DateUtil.convertFromDMYHM(projekcijaDTO.getDatumVremePrikazivanja()));
//		projekcija.setTipProjekcije(projekcijaDTO.getTipProjekcije());
//		projekcija.setSala(projekcijaDTO.getSala());
//		projekcija.setCenaKarte(projekcijaDTO.getCenaKarte());
//		projekcija.setNapravio(projekcijaDTO.getNapravio());
//		projekcija.setActive(projekcijaDTO.isActive());
//		
		projekcija=projekcijaService.save(projekcija);
		
		return ResponseEntity.ok().body(new ProjekcijaDTO(projekcija));
	}
	
	@DeleteMapping(value = "/projekcije/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteProjekcija(@PathVariable("id") String id){
		
		System.out.println("\n\n\t------------>Ulazim u delete projekcije<------------------");
		
		System.out.println("Id: "+id);
		
		Projekcija projekcija=projekcijaService.findById(Integer.parseInt(id));
		projekcija.setActive(false);
		projekcijaRepository.save(projekcija);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
