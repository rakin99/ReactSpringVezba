package com.example.bioskop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bioskop.ModelDTO.IzvestajiDTO;
import com.example.bioskop.model.Film;
import com.example.bioskop.model.Karta;
import com.example.bioskop.model.Projekcija;
import com.example.bioskop.service.FilmService;
import com.example.bioskop.service.KartaService;
import com.example.bioskop.service.ProjekcijaService;
import com.example.bioskop.tools.DateUtil;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/bioskop")
public class IzvestajiController {
	
	@Autowired
	ProjekcijaService projekcijaService;
	
	@Autowired
	FilmService filmService;
	
	@Autowired
	KartaService kartaService;

	@GetMapping(value = "/izvestaji/{vremeProjekcijeOd}/{vremeProjekcijeDo}/{sort}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<IzvestajiDTO>> getFilmovi(@PathVariable("vremeProjekcijeOd")String vOd,@PathVariable("vremeProjekcijeDo")String vDo,
			@PathVariable("sort")String s) throws ParseException{
		System.out.println("\n\n\t------------->Get izvestaji<--------------");

		GregorianCalendar vremeOd=new GregorianCalendar();
		GregorianCalendar vremeDo=new GregorianCalendar();
		vremeDo.setTime(new Date(Long.MAX_VALUE));
		String sort="";
		
		if(!vOd.equals("null")) {
			vremeOd=DateUtil.convertFromDMYHM(DateUtil.convert(vOd));
		}
		
		if(!vDo.equals("null")) {
			vremeDo=DateUtil.convertFromDMYHM(DateUtil.convert(vDo));
		}
		
		if(!s.equals("null")) {
			sort=s;
		}
		
		List<IzvestajiDTO> izvestaji = new ArrayList<IzvestajiDTO>();
		List<Projekcija> projekcije = projekcijaService.findAll();
		List<Film> filmovi = filmService.findAll();
		List<Karta> karte = kartaService.findAll();
		
		int id=0;
		for (Film f : filmovi) {
			if(f.isActive()) {
				IzvestajiDTO izvestajiDTO=null;
				int brProjekcija=0;
				int brKarata=0;
				double ukupnaCena=0;
				if(projekcije.size()==0) {
					izvestajiDTO= new IzvestajiDTO(id++, f.getNaziv(), brProjekcija, brKarata, ukupnaCena);
				}
				for (Projekcija p : projekcije) {
					if(p.isActive()) {
						if(f.getId() == p.getFilm().getId()) {
							if((DateUtil.CompareDate(p.getDatumVremePrikazivanja(), vremeOd)) && (DateUtil.CompareDate(vremeDo, p.getDatumVremePrikazivanja()))) {
								brProjekcija++;
								for (Karta k : karte) {
									if(k.getProjekcija().getId() == p.getId()) {
										brKarata++;
										ukupnaCena=ukupnaCena + p.getCenaKarte();
									}
								}
								izvestajiDTO= new IzvestajiDTO(id++, f.getNaziv(), brProjekcija, brKarata, ukupnaCena);
							}
						}
					}
				}
				if(izvestajiDTO != null) {
					izvestaji.add(izvestajiDTO);
				}
			}
		}
		
		switch (sort) {
		case "brProjekcija-asc":
			System.out.println(sort);
			izvestaji.sort(Comparator.comparing(IzvestajiDTO::getBrojProjekcija));
			break;
			
		case "brProjekcija-desc":
			System.out.println(sort);
			izvestaji.sort(Comparator.comparing(IzvestajiDTO::getBrojProjekcija).reversed());
			break;
			
		case "brProdatihKarata-asc":
			System.out.println(sort);
			izvestaji.sort(Comparator.comparing(IzvestajiDTO::getBrojProdatihKarata));
			break;
			
		case "brProdatihKarata-desc":
			System.out.println(sort);
			izvestaji.sort(Comparator.comparing(IzvestajiDTO::getBrojProdatihKarata).reversed());
			break;
			
		case "ukupnaCenaProdatihKarata-asc":
			System.out.println(sort);
			izvestaji.sort(Comparator.comparing(IzvestajiDTO::getUkupnaCena));
			break;
			
		case "ukupnaCenaProdatihKarata-desc":
			System.out.println(sort);
			izvestaji.sort(Comparator.comparing(IzvestajiDTO::getUkupnaCena).reversed());
			break;

		default:
			break;
		}
		
		return ResponseEntity.ok().body(izvestaji);
	}
}
