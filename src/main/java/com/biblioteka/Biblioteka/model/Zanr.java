package com.biblioteka.Biblioteka.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Zanr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Boolean deleted;

	@Column(nullable = false, unique = true)
	private String naziv;

	@OneToMany(mappedBy = "zanr")
	private List<Knjiga> knjige;
	
	@OneToMany(mappedBy = "zanr")
	private List<Pisac> pisci;

	@ManyToOne
	private Pisac pisac;
	
	
	public Pisac getPisac() {
		return pisac;
	}

	public void setPisac(Pisac pisac) {
		this.pisac = pisac;
	}

	public List<Pisac> getPisci() {
		return pisci;
	}

	public void setPisci(List<Pisac> pisci) {
		this.pisci = pisci;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Zanr() {
	}

	public Zanr(String naziv) {
		this.naziv = naziv;
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
}
