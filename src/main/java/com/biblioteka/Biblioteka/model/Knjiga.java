package com.biblioteka.Biblioteka.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Knjiga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Boolean deleted;
    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String godinaIzdavanja;

    @ManyToOne
    @JoinColumn(name = "pisac_id")
    private Pisac pisac;

    @OneToMany(mappedBy = "knjiga")
    private List<Primerak> primerci;
    
    @OneToMany(mappedBy = "knjiga")
    private List<Pisac> pisci;

    @ManyToOne
    @JoinColumn(name = "izdavac_id")
    private Izdavac izdavac;

    @ManyToOne
    @JoinColumn(name = "zanr_id")
    private Zanr zanr;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Administrator administrator;

    public Knjiga() {}

    public Knjiga(String naziv, String godinaIzdavanja, Pisac pisac, List<Primerak> primerci, Izdavac izdavac, Zanr zanr, Administrator administrator) {
        this.naziv = naziv;
        this.godinaIzdavanja = godinaIzdavanja;
        this.pisac = pisac;
        this.primerci = primerci;
        this.izdavac = izdavac;
        this.zanr = zanr;
        this.administrator = administrator;
    }

    public List<Pisac> getPisci() {
		return pisci;
	}

	public void setPisci(List<Pisac> pisci) {
		this.pisci = pisci;
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

    public Pisac getPisac() {
        return pisac;
    }

    public void setPisac(Pisac pisac) {
        this.pisac = pisac;
    }

    public List<Primerak> getPrimerci() {
        return primerci;
    }

    public void setPrimerci(List<Primerak> primerci) {
        this.primerci = primerci;
    }

    public Izdavac getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(Izdavac izdavac) {
        this.izdavac = izdavac;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
