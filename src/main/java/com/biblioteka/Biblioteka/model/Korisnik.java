package com.biblioteka.Biblioteka.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

@Entity
public class Korisnik extends Osoba {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String adresa;

	@OneToMany(mappedBy = "korisnik")
	private List<Zaduzenje> zaduzenja;

	private String registracioniLink;

	@Column
	private boolean aktiviran;

	@Column
	private boolean deleted;

	public Korisnik() {
		this.aktiviran = false;
		this.deleted = false;
	}

	public Korisnik(@Email String email, String ime, String prezime, String username, String password, String adresa,
			List<Zaduzenje> zaduzenja, boolean aktiviran, boolean obrisan) {
		super(email, ime, prezime, username, password);
		this.adresa = adresa;
		this.aktiviran = false;
		this.deleted = false;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public List<Zaduzenje> getZaduzenja() {
		return zaduzenja;
	}

	public void setZaduzenja(List<Zaduzenje> zaduzenja) {
		this.zaduzenja = zaduzenja;
	}

	public boolean isAktiviran() {
		return aktiviran;
	}

	public void setAktiviran(boolean aktiviran) {
		this.aktiviran = aktiviran;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getRegistracioniLink() {
		return registracioniLink;
	}

	public void setRegistracioniLink(String registracioniLink) {
		this.registracioniLink = registracioniLink;
	}
}
