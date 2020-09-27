package com.example.bioskop.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "karta")
public class Karta implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "karta_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "projekcija_id", referencedColumnName = "projekcija_id", nullable = false)
	private Projekcija projekcija;
	
	@OneToOne
	@JoinColumn(name = "redni_br", referencedColumnName = "redni_br", nullable = false)
	private Sediste sediste;
	
	@Column(name = "datum_i_vreme_prodaje", unique = false, nullable = false)
	private GregorianCalendar datumVremeProdaje;
	
	@ManyToOne
	@JoinColumn(name = "kupac", referencedColumnName = "username", nullable = false)
	private User kupac;

	
	//Geteri i seteri
	//----------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	public GregorianCalendar getDatumVremeProdaje() {
		return datumVremeProdaje;
	}

	public void setDatumVremeProdaje(GregorianCalendar datumVremeProdaje) {
		this.datumVremeProdaje = datumVremeProdaje;
	}

	public User getKupac() {
		return kupac;
	}

	//Konstruktori
	//*************************
	public void setKupac(User kupac) {
		this.kupac = kupac;
	}

	public Karta(Integer id, Projekcija projekcija, Sediste sediste, GregorianCalendar datumVremeProdaje, User kupac) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datumVremeProdaje = datumVremeProdaje;
		this.kupac = kupac;
	}

	public Karta() {
		super();
	}

	@Override
	public String toString() {
		return "Karta [id=" + id + ", projekcija=" + projekcija + ", sediste=" + sediste + ", datumVremeProdaje="
				+ datumVremeProdaje + ", kupac=" + kupac + "]";
	}
}
