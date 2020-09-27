package com.example.bioskop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
import javax.persistence.Table;

@Entity
@Table(name = "tip_projekcije")
public class TipProjekcije implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tip_projekcije_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "naziv", unique = false, nullable = false)
	private String naziv;
	
	@ManyToMany
	@JoinTable(name = "sala_tip", joinColumns = {@JoinColumn(name="tip_projekcije_id")}, inverseJoinColumns = {@JoinColumn(name="sala_id")})
	private List<Sala> sale= new ArrayList<Sala>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tipProjekcije")
	private List<Projekcija> projekcija=new ArrayList<Projekcija>();
	
	public void add(Sala s) {
		s.getTip().add(this);
		sale.add(s);
	}
	
	public void remove(Sala s) {
		s.getTip().remove(this);
		sale.remove(s);
	}
	
	public TipProjekcije(Integer id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public TipProjekcije() {
		super();
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
	
	public List<Sala> getSale() {
		return sale;
	}

	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}
	
	public List<Projekcija> getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(List<Projekcija> projekcija) {
		for (Projekcija projekcija2 : projekcija) {
			projekcija2.setTipProjekcije(this);
		}
		this.projekcija = projekcija;
	}

	@Override
	public String toString() {
		return "TipProjekcije [id=" + id + ", naziv=" + naziv + "]";
	}
	
	
}
