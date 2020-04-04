package com.biblioteka.Biblioteka.model;

import java.util.List;
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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Knjiga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String naziv;

	@Column(nullable = false)
	private String godinaIzdavanja;

	@OneToMany(mappedBy = "knjiga")
	private List<Primerak> primerci;

	@ManyToOne
	@JoinColumn(name = "izdavac_id")
	private Izdavac izdavac;

	@ManyToOne
	@JoinColumn(name = "zanr_id")
	private Zanr zanr;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "pisci_knjige", joinColumns = @JoinColumn(name = "knjiga_id"), inverseJoinColumns = @JoinColumn(name = "pisac_id"))
	private Set<Pisac> pisci;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Administrator administrator;

	@Column
	private boolean deleted;

	public Knjiga() {
		this.deleted = false;
	}

	public Knjiga(String naziv, String godinaIzdavanja, Pisac pisac, List<Primerak> primerci, Izdavac izdavac,
			Zanr zanr, Administrator administrator) {
		this.naziv = naziv;
		this.godinaIzdavanja = godinaIzdavanja;
		this.primerci = primerci;
		this.izdavac = izdavac;
		this.zanr = zanr;
		this.administrator = administrator;
		this.deleted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getGodinaIzdavanja() {
		return godinaIzdavanja;
	}

	public void setGodinaIzdavanja(String godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}

	public List<Primerak> getPrimerci() {
		return primerci;
	}

	public void setPrimerci(List<Primerak> primerci) {
		this.primerci = primerci;
	}

	public Izdavac getIzdavac() {
		return izdavac;
	}

	public void setIzdavac(Izdavac izdavac) {
		this.izdavac = izdavac;
	}

	public Set<Pisac> getPisci() {
		return pisci;
	}

	public void setPisci(Set<Pisac> pisci) {
		this.pisci = pisci;
	}

	public Zanr getZanr() {
		return zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
