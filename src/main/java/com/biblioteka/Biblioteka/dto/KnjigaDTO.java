package com.biblioteka.Biblioteka.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class KnjigaDTO {

	@NotNull
	private String naziv;

	@NotNull
	private String godinaIzdavanja;

	@NotNull
	private Long izdavacId;

	@NotNull
	private Long zanrId;

	@NotNull
	private Long adminId;

	private List<Long> pisciIds;

	public KnjigaDTO() {
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

	public Long getIzdavacId() {
		return izdavacId;
	}

	public void setIzdavacId(Long izdavacId) {
		this.izdavacId = izdavacId;
	}

	public Long getZanrId() {
		return zanrId;
	}

	public void setZanrId(Long zanrId) {
		this.zanrId = zanrId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public List<Long> getPisciIds() {
		return pisciIds;
	}

	public void setPisciIds(List<Long> pisciIds) {
		this.pisciIds = pisciIds;
	}

}
