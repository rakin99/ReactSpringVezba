package com.example.bioskop.ModelDTO;

import java.text.ParseException;
import java.util.GregorianCalendar;

import com.example.bioskop.enumeracija.Uloga;
import com.example.bioskop.model.Authority;
import com.example.bioskop.model.User;
import com.example.bioskop.tools.DateUtil;

public class UserDTO {
	
	private String korisnickoIme;
	private String lozinka;
	private String datumRegistracije;
	private String authorities;
	private boolean active;
	
	//Kostruktori
	public UserDTO(String korisnickoIme, String lozinka, 
			String datumRegistracije, String uloga,
			boolean active) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.datumRegistracije = datumRegistracije;
		this.authorities = uloga;
		this.active = active;
	}

	public UserDTO() {
		super();
	}
	
	public UserDTO(User user) throws ParseException {
		this(user.getUsername(),user.getPassword(),
				DateUtil.format(user.getDatumRegistracije()),user.getAuthoritiesAsString(),user.isEnabled());
	}

	//Geteri i seteri
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPassword() {
		return lozinka;
	}

	public void setPassword(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(String s) throws ParseException {
		this.datumRegistracije = s;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String uloga) {
		this.authorities = uloga;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserDTO [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", datumRegistracije="
				+ datumRegistracije + ", uloga=" + authorities + ", active=" + active + "]";
	}
}
