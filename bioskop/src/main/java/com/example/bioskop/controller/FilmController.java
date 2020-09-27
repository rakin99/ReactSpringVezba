package com.example.bioskop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
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

import com.example.bioskop.ModelDTO.FilmDTO;
import com.example.bioskop.model.Film;
import com.example.bioskop.service.FilmService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/bioskop")
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping(value = "/filmovi")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<FilmDTO>> getFilmovi(){
		System.out.println("\n\n\t------>Ulazim u getFilmovi<----------");
		
		List<Film> filmovi=filmService.findAll();
		List<FilmDTO> filmoviDTO=new ArrayList<FilmDTO>();
		for (Film film : filmovi) {
			if(film.isActive()) {
				FilmDTO filmDTO=new FilmDTO(film);
				filmoviDTO.add(filmDTO);
			}
		}
		
		return ResponseEntity.ok().body(filmoviDTO);
	}
	
	@GetMapping(value = "/filmovi/{naziv}/{zanr}/{od}/{do}/{dist}/{zemlja}/{godOd}/{godDo}/{sort}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<FilmDTO>> getFilmovi(@PathVariable("naziv")String n,@PathVariable("zanr")String z,
			@PathVariable("od")String o,@PathVariable("do")String d,@PathVariable("dist")String dist,
			@PathVariable("zemlja")String zemlj,@PathVariable("godOd")String gO,@PathVariable("godDo")String gD,@PathVariable("sort")String s) throws ParseException{
		System.out.println("\n\n\t------------->Get filmovi filter<--------------");

		String naziv="";
		String zanr="";
		int od=0;
		int dO=Integer.MAX_VALUE;
		String distributer="";
		String zemlja="";
		int godOd=0;
		int godDo=Integer.MAX_VALUE;
		String sort="";
		
		if(!n.equals("null")) {
			naziv=n;
		}
		
		if(!z.equals("null")) {
			zanr=z;
		}
		
		if(!o.equals("null")) {
			od=Integer.parseInt(o);
		}
		
		if(!d.equals("null")) {
			dO=Integer.parseInt(d);
		}
		
		if(!dist.equals("null")) {
			distributer=dist;
		}
		
		if(!zemlj.equals("null")) {
			zemlja=zemlj;
		}
		
		if(!gO.equals("null")) {
			godOd=Integer.parseInt(gO);
		}
		
		if(!gD.equals("null")) {
			godDo=Integer.parseInt(gD);
		}
		
		if(!s.equals("null")) {
			sort=s;
		}
		
		List<Film> filmovi=filmService.findAll();
		List<FilmDTO> aktivniFilmovi=new ArrayList<FilmDTO>();
		for (Film film : filmovi) {
			if(film.isActive()) {
				if(film.getNaziv().toLowerCase().contains(naziv.toLowerCase())) {
					if(film.getZanrovi().toLowerCase().contains(zanr.toLowerCase())) {
						if(od<=film.getTrajanje() && film.getTrajanje()<=dO)
						{
							if(film.getDistributer().toLowerCase().contains(distributer.toLowerCase())) {
								if(film.getZemljaPorekla().toLowerCase().contains(zemlja.toLowerCase())) {
									if(godOd<=film.getGodinaProizvodnje() && film.getGodinaProizvodnje()<=godDo) {
										aktivniFilmovi.add(new FilmDTO(film));
									}
								}
							}
						}
					}
				}
			}
		}
		
		switch (sort) {
		case "n-asc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getNaziv,String.CASE_INSENSITIVE_ORDER));
			break;
			
		case "n-desc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getNaziv,String.CASE_INSENSITIVE_ORDER).reversed());
			break;
			
		case "z-asc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getZanrovi).reversed());
			break;
			
		case "z-desc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getZanrovi));
			break;
			
		case "t-asc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getTrajanje));
			break;
			
		case "t-desc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getTrajanje).reversed());
			break;
			
		case "d-asc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getDistributer,String.CASE_INSENSITIVE_ORDER));
			break;
			
		case "d-desc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getDistributer,String.CASE_INSENSITIVE_ORDER).reversed());
			break;
			
		case "zm-asc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getZemljaPorekla,String.CASE_INSENSITIVE_ORDER));
			break;
			
		case "zm-desc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getZemljaPorekla,String.CASE_INSENSITIVE_ORDER).reversed());
			break;
			
		case "g-asc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getGodinaProizvodnje));
			break;
			
		case "g-desc":
			System.out.println(sort);
			aktivniFilmovi.sort(Comparator.comparing(FilmDTO::getGodinaProizvodnje).reversed());
			break;

		default:
			break;
		}
		
		return ResponseEntity.ok().body(aktivniFilmovi);
	}
	
	@PostMapping(value = "/filmovi/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, String>> newFilm(@RequestBody String s){
		System.out.println("\n\n\t------------>Ulazim u post filmovi<------------------");
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(s);// response will be the json String
		FilmDTO filmDTO = gson.fromJson(object, FilmDTO.class); 
		System.out.println("Ulazni string: "+s);
		Map<String, String> result = new HashMap<>();
		StringBuilder r=new StringBuilder();
		Film film = new Film();
		film.setNaziv(filmDTO.getNaziv());
		film.setGlumci(filmDTO.getGlumci());
		film.setReziser(filmDTO.getReziser());
		film.setZemljaPorekla(filmDTO.getZemljaPorekla());
		film.setZanrovi(filmDTO.getZanrovi());
		if(filmDTO.getGodinaProizvodnje() < 0) {
			r.append("--Godina proizvodnje filma mora biti veca od nula!--\n");
		}else {
			film.setGodinaProizvodnje(filmDTO.getGodinaProizvodnje());
		}
		film.setDistributer(filmDTO.getDistributer());
		film.setActive(true);
		film.setOpis(filmDTO.getOpis());
		if(filmDTO.getTrajanje() < 0) {
			r.append("--Trajanje filma mora biti vece od nula!--\n");
		}else {
			film.setTrajanje(filmDTO.getTrajanje());
		}
		if(r.toString().equals("")) {
			film=filmService.save(film);
			r.append("success");
			result.put("id",String.valueOf(film.getId()));
		}
		
		result.put("result",r.toString());
		
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping(value = "/filmovi/edit")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<FilmDTO> editFilm(@RequestBody String s) throws ParseException{
		System.out.println("\n\n\t------------>Ulazim u put filmovi<------------------");
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(s);// response will be the json String
		FilmDTO filmDTO = gson.fromJson(object, FilmDTO.class);
		
		Film film=filmService.findById(filmDTO.getId());
		film.setNaziv(filmDTO.getNaziv());
		film.setReziser(filmDTO.getReziser());
		film.setGlumci(filmDTO.getGlumci());
		film.setZanrovi(filmDTO.getZanrovi());
		film.setTrajanje(filmDTO.getTrajanje());
		film.setDistributer(filmDTO.getDistributer());
		film.setZemljaPorekla(filmDTO.getZemljaPorekla());
		film.setGodinaProizvodnje(filmDTO.getGodinaProizvodnje());
		film.setOpis(filmDTO.getOpis());
		
		film=filmService.save(film);
		
		return ResponseEntity.ok().body(new FilmDTO(film));
	}
	
	@DeleteMapping(value = "/filmovi/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") String id){
		
		System.out.println("\n\n\t------------>Ulazim u delete filmovi<------------------");
		
		System.out.println("Id: "+id);
		
		Film film=filmService.findById(Integer.parseInt(id));
		film.setActive(false);
		filmService.save(film);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
