package com.biblioteka.Biblioteka.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class AdministratorDTO {

	@NotBlank
	private String email;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String ime;

	@NotBlank
	private String prezime;

	private Set<String> role;

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

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

}
