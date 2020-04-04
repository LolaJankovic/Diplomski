package com.biblioteka.Biblioteka.dto;

import javax.validation.constraints.NotNull;

public class DrzavaDTO {

	@NotNull
	private String naziv;

	public DrzavaDTO() {
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
