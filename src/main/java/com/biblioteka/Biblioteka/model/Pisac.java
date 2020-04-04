package com.biblioteka.Biblioteka.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pisac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String ime;

	@Column(nullable = false)
	private String prezime;

	@ManyToOne
	@JoinColumn(name = "drzava_id")
	private Drzava drzava;

	@JsonIgnore
	@ManyToMany(mappedBy = "pisci")
	private Set<Knjiga> knjige;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pisci_zanrovi", joinColumns = @JoinColumn(name = "pisac_id"), inverseJoinColumns = @JoinColumn(name = "zanr_id"))
	private Set<Zanr> zanrovi;

	@Column
	private boolean deleted;

	public Pisac() {
		super();
		this.deleted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

	public Set<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(Set<Knjiga> knjige) {
		this.knjige = knjige;
	}

	public Set<Zanr> getZanrovi() {
		return zanrovi;
	}

	public void setZanrovi(Set<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
