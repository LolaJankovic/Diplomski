package com.biblioteka.Biblioteka.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Zanr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String naziv;

	@JsonIgnore
	@OneToMany(mappedBy = "zanr")
	private List<Knjiga> knjige;

	@JsonIgnore
	@ManyToMany(mappedBy = "zanrovi")
	private Set<Pisac> pisci;

	@Column
	private boolean deleted;

	public Zanr(String naziv, List<Knjiga> knjige, List<Pisac> pisci) {
		super();
		this.naziv = naziv;
		this.knjige = knjige;
		this.deleted = false;
	}

	public Zanr() {
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

	public List<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<Knjiga> knjige) {
		this.knjige = knjige;
	}

	public Set<Pisac> getPisci() {
		return pisci;
	}

	public void setPisci(Set<Pisac> pisci) {
		this.pisci = pisci;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
