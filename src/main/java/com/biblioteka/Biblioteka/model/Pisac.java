package com.biblioteka.Biblioteka.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pisac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Boolean deleted;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;

    @ManyToOne
    @JoinColumn(name="drzava_id")
    private Drzava drzava;
    
    @ManyToOne
    @JoinColumn(name="knjiga_id")
    private Knjiga knjiga;
    
    @ManyToOne
    @JoinColumn(name="zanr_id")
    private Zanr zanr;

    public Zanr getZanr() {
		return zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	@OneToMany(mappedBy = "pisac")
    private List<Knjiga> knjige;
	
	@OneToMany(mappedBy = "pisac")
	private List<Zanr> zanrovi;

    public List<Zanr> getZanrovi() {
		return zanrovi;
	}

	public void setZanrovi(List<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}

	public Pisac() {
    }

    public Pisac(String ime, String prezime, Drzava drzava) {
        this.ime = ime;
        this.prezime = prezime;
        this.drzava = drzava;
    }

    public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
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

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }
}
