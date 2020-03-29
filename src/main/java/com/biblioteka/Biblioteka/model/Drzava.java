package com.biblioteka.Biblioteka.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Drzava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Boolean deleted;
    @Column(nullable = false)
    private String naziv;

    @OneToMany(mappedBy="drzava")
    private List<Pisac> pisci;

    public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<Pisac> getPisci() {
		return pisci;
	}

	public void setPisci(List<Pisac> pisci) {
		this.pisci = pisci;
	}

	public Drzava() {
    }

    public Drzava(String naziv) {
        this.naziv = naziv;
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
}
