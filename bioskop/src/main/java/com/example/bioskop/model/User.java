package com.example.bioskop.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.bioskop.enumeracija.Uloga;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "lozinka", unique = false, nullable = false)
	private String lozinka;
	
	@Column(name = "datum_registracije", unique = false, nullable = false)
	private GregorianCalendar datumRegistracije;
	
	@Column(name = "last_password_reset_date", unique = false, nullable = false)
    private Timestamp lastPasswordResetDate;
	
	@Column(name = "active", unique = false, nullable = false)
	private boolean active;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="napravio")
//	private List<Projekcija> projekcija=new ArrayList<Projekcija>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "kupac")
	private List<Karta> karte=new ArrayList<Karta>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

	//Geteri i seteri
	//---------------------
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String lozinka) {
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
        this.lozinka = lozinka;
    }

	public GregorianCalendar getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(GregorianCalendar datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

//	public List<Projekcija> getProjekcija() {
//		return projekcija;
//	}
//
//	public void setProjekcija(List<Projekcija> projekcija) {
//		for (Projekcija projekcija2 : projekcija) {
//			projekcija2.setNapravio(this);
//		}
//		this.projekcija = projekcija;
//	}

	public List<Karta> getKarte() {
		return karte;
	}

	public void setKarte(List<Karta> karte) {
		for (Karta karta : karte) {
			karta.setKupac(this);
		}
		this.karte = karte;
	}

	public void setEnable(boolean active) {
		this.active = active;
	}

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	
	@JsonIgnore
    public String getAuthoritiesAsString() {
    	StringBuilder sb = new StringBuilder();
    	
    	for (Authority authority : this.authorities) {
    		sb.append(authority.getName() + " ");
    	}
    	
    	return sb.toString();
    }
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
	
	public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

	@Override
	public String getPassword() {
		return lozinka;
	}

	@JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

	@JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

	@JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	@Override
	public boolean isEnabled() {
		return active;
	}

	//Konstruktori
	//-----------------------
	public User(String korisnicko, String lozinka, GregorianCalendar datumRegistracije, Uloga uloga,
			List<Projekcija> projekcija, List<Karta> karte) {
		super();
		this.username = korisnicko;
		this.lozinka = lozinka;
		this.datumRegistracije = datumRegistracije;
	//	this.projekcija = projekcija;
		this.karte = karte;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", lozinka=" + lozinka + ", datumRegistracije=" + datumRegistracije
				+ ", lastPasswordResetDate=" + lastPasswordResetDate + ", active=" + active + ", karte=" + karte
				+ ", authorities=" + authorities + "]";
	}
}
