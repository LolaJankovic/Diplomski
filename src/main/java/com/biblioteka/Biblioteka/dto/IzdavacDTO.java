package com.biblioteka.Biblioteka.dto;

import javax.validation.constraints.NotNull;

public class IzdavacDTO {

	@NotNull
	private String naziv;

	@NotNull
	private String grad;

	public IzdavacDTO() {
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

}
