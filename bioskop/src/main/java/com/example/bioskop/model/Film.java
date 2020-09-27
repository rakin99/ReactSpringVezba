package com.example.bioskop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "film")
public class Film implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "naziv_filma", unique = false, nullable = false)
	private String naziv;
	
	@Column(name = "reziser_filma", unique = false, nullable = false)
	private String reziser;
	
	@Column(name = "glumci_filma", unique = false, nullable = false)
	private String glumci;
	
	@Column(name = "zanrovi_filma", unique = false, nullable = false)
	private String zanrovi;
	
	@Column(name = "trajanje_filma", unique = false, nullable = false)
	private Integer trajanje;
	
	@Column(name = "distributer_filma", unique = false, nullable = false)
	private String distributer;
	
	@Column(name = "zemlja_porekla_filma", unique = false, nullable = false)
	private String zemljaPorekla;
	
	@Column(name = "godina_proizvodnje_filma", unique = false, nullable = false)
	private Integer godinaProizvodnje;
	
	@Column(name = "opis_filma", unique = false, nullable = false)
	private String opis;
	
	@Column(name = "active", unique = false, nullable = false)
	private boolean active;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="film")
	private List<Projekcija> projekcije = new ArrayList<Projekcija>();
	
	//Konstruktori
	//-------------------
	public Film() {
		super();
	}
	
	public Film(Integer id, String naziv, String reziser, String glumci, String zanrovi, Integer trajanje,
			String distributer, String zemljaPorekla, Integer godinaProizvodnje, String opis, boolean active,
			List<Projekcija> projekcije) {
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
		this.projekcije = projekcije;
	}

	//Geteri i seteri
	//-------------------------------
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

	public List<Projekcija> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(List<Projekcija> projekcije) {
		for (Projekcija projekcija : projekcije) {
			projekcija.setFilm(this);
		}
		this.projekcije = projekcije;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
