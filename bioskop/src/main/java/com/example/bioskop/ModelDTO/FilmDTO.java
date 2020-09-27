package com.example.bioskop.ModelDTO;

import java.io.Serializable;

import com.example.bioskop.model.Film;


public class FilmDTO implements Serializable{
	
	private Integer id;
	private String naziv;
	private String reziser;
	private String glumci;
	private String zanrovi;
	private Integer trajanje;
	private String distributer;
	private String zemljaPorekla;
	private Integer godinaProizvodnje;
	private String opis;
	private boolean active;
	
	public FilmDTO() {
		super();
	}

	public FilmDTO(Integer id, String naziv, String reziser, String glumci, String zanrovi, Integer trajanje,
			String distributer, String zemljaPorekla, Integer godinaProizvodnje, String opis, boolean active) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.reziser = reziser;
		this.glumci = glumci;
		this.zanrovi = zanrovi;
		this.trajanje = trajanje;
		this.distributer = distributer;
		this.zemljaPorekla = zemljaPorekla;
		this.godinaProizvodnje = godinaProizvodnje;
		this.opis = opis;
		this.active = active;
	}
	
	public FilmDTO(Film film) {
		this(film.getId(),film.getNaziv(),film.getReziser(),
				film.getGlumci(),film.getZanrovi(),film.getTrajanje(),film.getDistributer(),film.getZemljaPorekla(),
				film.getGodinaProizvodnje(),film.getOpis(),film.isActive());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getReziser() {
		return reziser;
	}

	public void setReziser(String reziser) {
		this.reziser = reziser;
	}

	public String getGlumci() {
		return glumci;
	}

	public void setGlumci(String glumci) {
		this.glumci = glumci;
	}

	public String getZanrovi() {
		return zanrovi;
	}

	public void setZanrovi(String zanrovi) {
		this.zanrovi = zanrovi;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public String getDistributer() {
		return distributer;
	}

	public void setDistributer(String distributer) {
		this.distributer = distributer;
	}

	public String getZemljaPorekla() {
		return zemljaPorekla;
	}

	public void setZemljaPorekla(String zemljaPorekla) {
		this.zemljaPorekla = zemljaPorekla;
	}

	public Integer getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(Integer godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "FilmDTO [id=" + id + ", naziv=" + naziv + ", reziser=" + reziser + ", glumci=" + glumci + ", zanrovi="
				+ zanrovi + ", trajanje=" + trajanje + ", distributer=" + distributer + ", zemljaPorekla="
				+ zemljaPorekla + ", godinaProizvodnje=" + godinaProizvodnje + ", opis=" + opis + ", active=" + active
				+ "]";
	}
}
