package com.example.bioskop.ModelDTO;

public class IzvestajiDTO {

	private int id;
	private String film;
	private int brojProjekcija;
	private int brojProdatihKarata;
	private double ukupnaCena;

	public IzvestajiDTO(int id,String film, int brojProjekcija, int brojProdatihKarata, double ukupnaCena) {
		super();
		this.id=id;
		this.film = film;
		this.brojProjekcija = brojProjekcija;
		this.brojProdatihKarata = brojProdatihKarata;
		this.ukupnaCena = ukupnaCena;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public IzvestajiDTO() {
		super();
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public int getBrojProjekcija() {
		return brojProjekcija;
	}

	public void setBrojProjekcija(int brojProjekcija) {
		this.brojProjekcija = brojProjekcija;
	}

	public int getBrojProdatihKarata() {
		return brojProdatihKarata;
	}

	public void setBrojProdatihKarata(int brojProdatihKarata) {
		this.brojProdatihKarata = brojProdatihKarata;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
}
