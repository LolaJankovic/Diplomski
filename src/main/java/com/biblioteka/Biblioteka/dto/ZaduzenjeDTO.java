package com.biblioteka.Biblioteka.dto;

import javax.validation.constraints.NotNull;

public class ZaduzenjeDTO {

	@NotNull
	private String username;

	@NotNull
	private Long primerakId;

	public ZaduzenjeDTO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getPrimerakId() {
		return primerakId;
	}

	public void setPrimerakId(Long primerakId) {
		this.primerakId = primerakId;
	}

}
