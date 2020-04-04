package com.biblioteka.Biblioteka.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Izdavac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String naziv;

	@Column(nullable = false)
	private String grad;

	@JsonIgnore
	@OneToMany(mappedBy = "izdavac")
	private List<Knjiga> knjige;

	@Column
	private boolean deleted;

	public Izdavac() {
		this.deleted = false;
	}

	public Izdavac(Long id, String naziv, String grad, List<Knjiga> knjige) {
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.knjige = knjige;
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

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public List<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<Knjiga> knjige) {
		this.knjige = knjige;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
