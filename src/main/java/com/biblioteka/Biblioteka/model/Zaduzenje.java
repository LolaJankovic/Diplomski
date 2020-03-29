package com.biblioteka.Biblioteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Zaduzenje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Boolean deleted;

    @Column(nullable = false)
    private boolean zaduzena;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "primerak_id")
    private Primerak primerak;

    public Zaduzenje() {}

    public Zaduzenje(boolean zaduzena, Korisnik korisnik, Primerak primerak) {
        this.zaduzena = zaduzena;
        this.korisnik = korisnik;
        this.primerak = primerak;
    }
    

    public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isZaduzena() {
        return zaduzena;
    }

    public void setZaduzena(boolean zaduzena) {
        this.zaduzena = zaduzena;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Primerak getPrimerak() {
        return primerak;
    }

    public void setPrimerak(Primerak primerak) {
        this.primerak = primerak;
    }
}
