package com.biblioteka.Biblioteka.model;

import com.biblioteka.Biblioteka.enumeration.TipRole;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Boolean deleted;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private TipRole naziv;

    public Role(TipRole naziv) {
        this.naziv = naziv;
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

    public TipRole getNaziv() {
        return naziv;
    }

    public void setNaziv(TipRole naziv) {
        this.naziv = naziv;
    }
}
