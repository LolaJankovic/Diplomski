package com.biblioteka.Biblioteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Zaduzenje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;

	@ManyToOne
	@JoinColumn(name = "primerak_id")
	private Primerak primerak;

	@Column
	private boolean deleted;

	public Zaduzenje() {
		this.deleted = false;
	}

	public Zaduzenje(Korisnik korisnik, Primerak primerak) {
		this.korisnik = korisnik;
		this.primerak = primerak;
		this.deleted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Primerak getPrimerak() {
		return primerak;
	}

	public void setPrimerak(Primerak primerak) {
		this.primerak = primerak;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
