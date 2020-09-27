package com.example.bioskop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Projekcija implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projekcija_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="film_id", referencedColumnName="film_id", nullable=false)
	private Film film;
	
	@ManyToOne
	@JoinColumn(name = "tip_projekcije_id", referencedColumnName = "tip_projekcije_id", nullable = false)
	private TipProjekcije tipProjekcije;
	
	@ManyToOne
	@JoinColumn(name = "sala_id", referencedColumnName = "sala_id", nullable = false)
	private Sala sala;
	
	@Column(name = "datum_vreme_prikazivanja", unique = false, nullable = false)
	private GregorianCalendar datumVremePrikazivanja;

	@Column(name = "cena_karte", unique = false, nullable = false)
	private double cenaKarte;
	
	@ManyToOne
	@JoinColumn(name = "username",unique = false, nullable = false)
	private User napravio;
	
	@Column(name = "active", unique = false, nullable = false)
	private boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projekcija")
	private List<Karta> karte=new ArrayList<Karta>();
	
	//Geteri i seteri
	//---------------------------------
	
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		film.getProjekcije().add(this);
		this.film = film;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipProjekcije getTipProjekcije() {
		return tipProjekcije;
	}

	public void setTipProjekcije(TipProjekcije tipProjekcije) {
		this.tipProjekcije = tipProjekcije;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public GregorianCalendar getDatumVremePrikazivanja() {
		return datumVremePrikazivanja;
	}

	public void setDatumVremePrikazivanja(GregorianCalendar datumVremePrikazivanja) {
		this.datumVremePrikazivanja = datumVremePrikazivanja;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public User getNapravio() {
		return napravio;
	}

	public void setNapravio(User napravio) {
		this.napravio = napravio;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	//Konstruktori
	public Projekcija(Integer id, Film film, TipProjekcije tipProjekcije, Sala sala,
			GregorianCalendar datumVremePrikazivanja, double cenaKarte, User napravio) {
		super();
		this.id = id;
		this.film = film;
		this.tipProjekcije = tipProjekcije;
		this.sala = sala;
		this.datumVremePrikazivanja = datumVremePrikazivanja;
		this.cenaKarte = cenaKarte;
		this.napravio = napravio;
	}

	public Projekcija() {
		super();
	}

	@Override
	public String toString() {
		return "Projekcija [id=" + id + ", film=" + film + ", tipProjekcije=" + tipProjekcije + ", sala=" + sala
				+ ", datumVremePrikazivanja=" + datumVremePrikazivanja + ", cenaKarte=" + cenaKarte + ", napravio="
				+ napravio + ", active=" + active + ", karte=" + karte + "]";
	}
}
