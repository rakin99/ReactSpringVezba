package com.example.bioskop.ModelDTO;

import java.text.ParseException;

import com.example.bioskop.model.Film;
import com.example.bioskop.model.Projekcija;
import com.example.bioskop.model.Sala;
import com.example.bioskop.model.TipProjekcije;
import com.example.bioskop.model.User;
import com.example.bioskop.tools.DateUtil;

public class ProjekcijaString {

	private String id;
	private String film;
	private String tipProjekcije;
	private String sala;
	private String datumVremePrikazivanja;
	private String cenaKarte;
	private String napravio;
	private boolean active;
	
	public ProjekcijaString(String id, String film, String tipProjekcije, String sala,
			String datumVremePrikazivanja, String cenaKarte, String napravio,boolean active) {
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

	public ProjekcijaString() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilmId() {
		return film;
	}

	public void setFilmId(String film) {
		this.film = film;
	}

	public String getTipProjekcijeNaziv() {
		return tipProjekcije;
	}

	public void setTipProjekcijeNaziv(String tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public String getSalaId() {
		return sala;
	}

	public void setSalaId(String sala) {
		this.sala = sala;
	}

	public String getDatumVremePrikazivanja() {
		return datumVremePrikazivanja;
	}

	public void setDatumVremePrikazivanja(String datumVremePrikazivanja) {
		this.datumVremePrikazivanja = datumVremePrikazivanja;
	}

	public String getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(String cenaKarte) {
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
