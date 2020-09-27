package com.example.bioskop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sala")
public class Sala implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="sala_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="naziv", unique = false, nullable = false)
	private String naziv;
	
	@ManyToMany(mappedBy = "sale")
	private List<TipProjekcije> tip;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="sala")
	private List<Sediste> sedista = new ArrayList<Sediste>();
	
	@OneToMany(mappedBy = "sala",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Projekcija> projekcija=new ArrayList<Projekcija>();
	
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

	public List<TipProjekcije> getTip() {
		return tip;
	}
	
	@JsonIgnore
    public String getTipAsString() {
    	StringBuilder sb = new StringBuilder();
    	
    	for (TipProjekcije tipProjekcije : this.tip) {
    		sb.append(tipProjekcije.getNaziv() + " ");
    	}
    	
    	return sb.toString();
    }

	public void setTip(List<TipProjekcije> tip) {
		for (TipProjekcije tipProjekcije : tip) {
			tipProjekcije.getSale().add(this);
		}
		this.tip = tip;
	}
	
	public List<Sediste> getSedista() {
		return sedista;
	}

	public List<Projekcija> getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(List<Projekcija> projekcija) {
		for (Projekcija projekcija2 : projekcija) {
			projekcija2.setSala(this);
		}
		this.projekcija = projekcija;
	}

	public Sala(Integer id, String naziv, List<TipProjekcije> tip) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
	}

	public Sala() {
		super();
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", naziv=" + naziv + ", tip=" + tip + ", sedista=" + sedista + "]";
	}
}
