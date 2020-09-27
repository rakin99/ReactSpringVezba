package com.example.bioskop.ModelDTO;

import com.example.bioskop.model.Sediste;

public class SedisteDTO {
	
	private int redniBroj;
	private int idSala;
	
	public SedisteDTO(int redniBroj, int idSala) {
		super();
		this.redniBroj = redniBroj;
		this.idSala = idSala;
	}

	public SedisteDTO(Sediste s) {
		this(s.getRedniBroj(),s.getSala().getId());
	}

	public SedisteDTO() {
		super();
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
}
