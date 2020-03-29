package com.biblioteka.Biblioteka.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Izdavac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Boolean deleted;
    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String grad;

    @OneToMany(mappedBy = "izdavac")
    private List<Knjiga> knjige;

    public Izdavac() {}

    public Izdavac(Long id, String naziv, String grad, List<Knjiga> knjiga) {
        this.id = id;
        this.naziv = naziv;
        this.grad = grad;
        this.knjige = knjiga;
    }

    public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<Knjiga> knjige) {
		this.knjige = knjige;
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

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public List<Knjiga> getKnjiga() {
        return knjige;
    }

    public void setKnjiga(List<Knjiga> knjiga) {
        this.knjige = knjiga;
    }
}
