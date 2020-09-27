package com.example.bioskop.ModelDTO;

import java.text.ParseException;
import java.util.GregorianCalendar;

import com.example.bioskop.model.Film;
import com.example.bioskop.model.Projekcija;
import com.example.bioskop.model.Sala;
import com.example.bioskop.model.TipProjekcije;
import com.example.bioskop.model.User;
import com.example.bioskop.tools.DateUtil;

public class ProjekcijaDTO {

	private Integer id;
	private String film;
	private String tipProjekcije;
	private String sala;
	private String datumVremePrikazivanja;
	private double cenaKarte;
	private String napravio;
	private boolean active;
	
	public ProjekcijaDTO(Integer id, String film, String tipProjekcije, String sala,
			String datumVremePrikazivanja, double cenaKarte, String napravio,boolean active) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumVremePrikazivanja = datumVremePrikazivanja;
		this.cenaKarte = cenaKarte;
		this.napravio = napravio;
		this.active = active;
	}

	public ProjekcijaDTO() {
		super();
	}
	
	public ProjekcijaDTO(Projekcija p) throws ParseException {
		this(p.getId(),p.getFilm().getNaziv(),p.getTipProjekcije().getNaziv(),p.getSala().getNaziv(),
				DateUtil.formatTime(p.getDatumVremePrikazivanja()),p.getCenaKarte(),p.getNapravio().getUsername(),p.isActive());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public String getTipProjekcije() {
		return tipProjekcije;
	}

	public void setTipProjekcije(String tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getDatumVremePrikazivanja() {
		return datumVremePrikazivanja;
	}

	public void setDatumVremePrikazivanja(String datumVremePrikazivanja) {
		this.datumVremePrikazivanja = datumVremePrikazivanja;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getNapravio() {
		return napravio;
	}

	public void setNapravio(String napravio) {
		this.napravio = napravio;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
