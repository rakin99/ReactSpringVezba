package com.example.bioskop.ModelDTO;

import java.util.List;

import com.example.bioskop.model.Sala;
import com.example.bioskop.model.Sediste;

public class SalaDTO {

	private int id;
	private String naziv;
	private String tip;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public String getTip() {
		return tip;
	}
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public SalaDTO(int id, String naziv, String tip, List<Sediste> sedista) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
	}

	public SalaDTO(Sala s) {
		this(s.getId(),s.getNaziv(),s.getTipAsString(),s.getSedista());
	}

	public SalaDTO() {
		super();
	}
}
