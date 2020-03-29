package com.biblioteka.Biblioteka.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
public class Korisnik extends Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Boolean deleted;
    @Column(nullable = false)
    private String adresa;

    @OneToMany(mappedBy = "korisnik")
    private List<Zaduzenje> zaduzenja;

    private boolean aktiviran;

    private boolean obrisan;

    public Korisnik() {
        this.aktiviran = false;
        this.obrisan = false;
    }

    public Korisnik(@Email String email, String ime, String prezime, String username, String password, String adresa, List<Zaduzenje> zaduzenja, boolean aktiviran, boolean obrisan) {
        super(email, ime, prezime, username, password);
        this.adresa = adresa;
        this.aktiviran = false;
        this.obrisan = false;
    }
    

    public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<Zaduzenje> getZaduzenja() {
        return zaduzenja;
    }

    public void setZaduzenja(List<Zaduzenje> zaduzenja) {
        this.zaduzenja = zaduzenja;
    }

    public boolean isAktiviran() {
        return aktiviran;
    }

    public void setAktiviran(boolean aktiviran) {
        this.aktiviran = aktiviran;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }
}
