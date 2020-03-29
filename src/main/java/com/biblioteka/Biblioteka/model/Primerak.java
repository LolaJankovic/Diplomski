package com.biblioteka.Biblioteka.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Primerak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Boolean deleted;

    @Column(nullable = false, unique = true)
    private Integer inventarniBroj;

    @ManyToOne
    @JoinColumn(name = "knjiga_id")
    private Knjiga knjiga;

    @OneToMany(mappedBy = "primerak")
    private List<Zaduzenje> zaduzenja;

    public Primerak() {}

    public Primerak(Integer inventarniBroj, Knjiga knjiga, List<Zaduzenje> zaduzenja) {
        this.inventarniBroj = inventarniBroj;
        this.knjiga = knjiga;
        this.zaduzenja = zaduzenja;
    }
    
    public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getInventarniBroj() {
		return inventarniBroj;
	}

	public void setInventarniBroj(Integer inventarniBroj) {
		this.inventarniBroj = inventarniBroj;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public List<Zaduzenje> getZaduzenja() {
        return zaduzenja;
    }

    public void setZaduzenja(List<Zaduzenje> zaduzenja) {
        this.zaduzenja = zaduzenja;
    }
}
