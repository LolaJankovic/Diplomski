package com.biblioteka.Biblioteka.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

import com.biblioteka.Biblioteka.enumeration.TipRole;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	private TipRole naziv;

	public Role() {
	}

	public Role(TipRole naziv) {
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipRole getNaziv() {
		return naziv;
	}

	public void setNaziv(TipRole naziv) {
		this.naziv = naziv;
	}
}
