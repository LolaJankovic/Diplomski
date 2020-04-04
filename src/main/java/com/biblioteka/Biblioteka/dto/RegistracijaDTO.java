package com.biblioteka.Biblioteka.dto;

import javax.validation.constraints.NotNull;

public class RegistracijaDTO {

	@NotNull
	private String email;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String ime;

	@NotNull
	private String prezime;

	@NotNull
	private String adresa;

	public RegistracijaDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
