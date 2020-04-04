package com.biblioteka.Biblioteka.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class PisacDTO {

	@NotNull
	private String ime;

	@NotNull
	private String prezime;

	@NotNull
	private Long drzavaId;

	private List<Long> zanrIds;

	public PisacDTO() {
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

	public Long getDrzavaId() {
		return drzavaId;
	}

	public void setDrzavaId(Long drzavaId) {
		this.drzavaId = drzavaId;
	}

	public List<Long> getZanrIds() {
		return zanrIds;
	}

	public void setZanrIds(List<Long> zanrIds) {
		this.zanrIds = zanrIds;
	}

}
