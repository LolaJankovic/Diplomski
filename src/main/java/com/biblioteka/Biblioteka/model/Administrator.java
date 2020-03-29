package com.biblioteka.Biblioteka.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
public class Administrator extends Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Boolean deleted;
    @OneToMany(mappedBy = "administrator")
    private List<Knjiga> knjige;

    public Administrator() {
    }

    public Administrator(@Email String email, String ime, String prezime, String username, String password) {
        super(email, ime, prezime, username, password);
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

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }
}
