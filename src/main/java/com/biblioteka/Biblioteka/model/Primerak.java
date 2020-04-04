package com.biblioteka.Biblioteka.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Primerak {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private Long inventarniBroj;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "knjiga_id")
	private Knjiga knjiga;

	@OneToMany(mappedBy = "primerak")
	private List<Zaduzenje> zaduzenja;

	@Column
	private boolean zaduzen;

	@Column
	private boolean deleted;

	public Primerak() {
		this.deleted = false;
	}

	public Primerak(Long inventarniBroj, Knjiga knjiga, List<Zaduzenje> zaduzenja) {
		this.inventarniBroj = inventarniBroj;
		this.knjiga = knjiga;
		this.zaduzenja = zaduzenja;
		this.deleted = false;
	}

	public Long getInventarniBroj() {
		return inventarniBroj;
	}

	public void setInventarniBroj(Long inventarniBroj) {
		this.inventarniBroj = inventarniBroj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public List<Zaduzenje> getZaduzenja() {
		return zaduzenja;
	}

	public void setZaduzenja(List<Zaduzenje> zaduzenja) {
		this.zaduzenja = zaduzenja;
	}

	public boolean isZaduzen() {
		return zaduzen;
	}

	public void setZaduzen(boolean zaduzen) {
		this.zaduzen = zaduzen;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
