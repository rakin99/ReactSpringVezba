package com.example.bioskop.ModelDTO;

import java.text.ParseException;
import java.util.GregorianCalendar;

import com.example.bioskop.model.Karta;
import com.example.bioskop.model.Projekcija;
import com.example.bioskop.model.Sediste;
import com.example.bioskop.model.User;
import com.example.bioskop.tools.DateUtil;

public class KartaDTO {

	private String id;
	private String projekcija;
	private String sediste;
	private String datumVremeProdaje;
	private String kupac;
	
	public KartaDTO(String id, String projekcija, String sediste, String datumVremeProdaje,
			String kupac) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datumVremeProdaje = datumVremeProdaje;
		this.kupac = kupac;
	}

	public KartaDTO() {
		super();
	}
	
	public KartaDTO(Karta karta) throws ParseException {
		this(String.valueOf(karta.getId()),String.valueOf(karta.getProjekcija().getId()),String.valueOf(karta.getSediste().getRedniBroj()),DateUtil.formatTime(karta.getDatumVremeProdaje()),karta.getKupac().getUsername());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(String projekcija) {
		this.projekcija = projekcija;
	}

	public String getSediste() {
		return sediste;
	}

	public void setSediste(String sediste) {
		this.sediste = sediste;
	}

	public String getDatumVremeProdaje() {
		return datumVremeProdaje;
	}

	public void setDatumVremeProdaje(String datumVremeProdaje) {
		this.datumVremeProdaje = datumVremeProdaje;
	}

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

	@Override
	public String toString() {
		return "KartaDTO [id=" + id + ", projekcija=" + projekcija + ", sediste=" + sediste + ", datumVremeProdaje="
				+ datumVremeProdaje + ", kupac=" + kupac + "]";
	}
}
