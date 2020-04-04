package com.biblioteka.Biblioteka.dto;

import javax.validation.constraints.NotNull;

public class PrimerakDTO {

	@NotNull
	private Long inventarniBroj;

	@NotNull
	private Long knjigaId;

	public PrimerakDTO() {
	}

	public Long getInventarniBroj() {
		return inventarniBroj;
	}

	public void setInventarniBroj(Long inventarniBroj) {
		this.inventarniBroj = inventarniBroj;
	}

	public Long getKnjigaId() {
		return knjigaId;
	}

	public void setKnjigaId(Long knjigaId) {
		this.knjigaId = knjigaId;
	}

}
