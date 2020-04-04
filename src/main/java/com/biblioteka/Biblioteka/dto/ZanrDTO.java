package com.biblioteka.Biblioteka.dto;

import javax.validation.constraints.NotNull;

public class ZanrDTO {

	@NotNull
	private String naziv;

	public ZanrDTO() {
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
