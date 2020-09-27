package com.example.bioskop.service;

import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bioskop.model.Projekcija;
import com.example.bioskop.repository.ProjekcijaRepository;
import com.example.bioskop.tools.DateUtil;

@Service
public class ProjekcijaService implements ProjekcijaServiceInterface{

	@Autowired
	ProjekcijaRepository projekcijaRepository;
	
	@Override
	public Projekcija save(Projekcija projekcija) {
		boolean provera = false;
		Projekcija povratnaVrednost = new Projekcija();
		System.out.println("Save u ProjekcijaService");
		List<Projekcija> projekcijeSaSalom=projekcijaRepository.findBySala_id(projekcija.getSala().getId());
		for (Projekcija p : projekcijeSaSalom) {
			if(p.isActive()) {
				System.out.println("\nSala: "+p.getSala().getNaziv());
				GregorianCalendar pocetakFilma = p.getDatumVremePrikazivanja();
				GregorianCalendar pocetakNovogFilma = projekcija.getDatumVremePrikazivanja();
				GregorianCalendar krajFilma=new GregorianCalendar();
				GregorianCalendar krajNovogFilma=new GregorianCalendar();
				try {
					krajFilma = DateUtil.uvecajDatum(p.getDatumVremePrikazivanja(), p.getFilm().getTrajanje());
					krajNovogFilma = DateUtil.uvecajDatum(projekcija.getDatumVremePrikazivanja(), projekcija.getFilm().getTrajanje());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				try {
					System.out.println("\n\nPocetak filma: "+DateUtil.formatTime(pocetakFilma));
					System.out.println("Kraj filma: "+DateUtil.formatTime(krajFilma));
					System.out.println("Pocetak novog filma: "+DateUtil.formatTime(pocetakNovogFilma));
					System.out.println("Kraj novog filma: "+DateUtil.formatTime(krajNovogFilma)+"\n\n");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if((DateUtil.CompareDate(pocetakNovogFilma, pocetakFilma) && DateUtil.CompareDate(krajFilma, pocetakNovogFilma)) || 
						(DateUtil.CompareDate(krajNovogFilma, pocetakFilma) && DateUtil.CompareDate(krajFilma, krajNovogFilma))) {
							provera=true;
					}
			}
		}
		System.out.println("Provera: "+provera+"\n");
		if(provera) {
			povratnaVrednost=null;
		}else {
			System.out.println("Dodajem/Brisem projekciju");
			povratnaVrednost = projekcijaRepository.save(projekcija);
		}
		return povratnaVrednost;
		
	}

	@Override
	public List<Projekcija> findAll() {
		return projekcijaRepository.findAll();
	}

	@Override
	public Projekcija findById(Integer id) {
		return projekcijaRepository.findById(id);
	}
	
}
