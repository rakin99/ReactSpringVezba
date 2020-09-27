package com.example.bioskop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sediste")
public class Sediste implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "redni_br", unique = true, nullable = false)
	private Integer redniBroj;
	
	@ManyToOne
	@JoinColumn(name="sala_id", referencedColumnName="sala_id", nullable=false)
	private Sala sala;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "sediste")
	private Karta karta;

	public Integer getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(Integer redniBroj) {
		this.redniBroj = redniBroj;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Sediste(Integer redniBroj, Sala sala) {
		super();
		this.redniBroj = redniBroj;
		this.sala = sala;
	}

	public Sediste() {
		super();
	}

	@Override
	public String toString() {
		return "Sediste [redniBroj=" + redniBroj  + ", sala=" + sala + "]";
	}
}
