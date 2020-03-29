package com.biblioteka.Biblioteka.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteka.Biblioteka.model.Drzava;
import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Osoba;
import com.biblioteka.Biblioteka.model.Pisac;
import com.biblioteka.Biblioteka.model.Zanr;
import com.biblioteka.Biblioteka.repository.DrzavaRepository;
import com.biblioteka.Biblioteka.repository.PisacRepository;
import com.biblioteka.Biblioteka.repository.ZanrRepository;

@Service
@Transactional
public class PisacService {
	@Autowired
	PisacRepository pisacRepository;
	@Autowired
	ZanrRepository zanrRepository;
	@Autowired
	DrzavaRepository drzavaRepository;

//sacuvaj
	@Transactional
	public Pisac save(Pisac pisac) {
		if (!(pisac.getIme()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(pisac.getPrezime()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(pisac.getDrzava().getId()  instanceof Long)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		return pisacRepository.save(pisac);
	}

//dobavi sve
	@Transactional
	public List<Pisac> findAll() {
		List<Pisac> list = pisacRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Pisac findOne(Long id) {
		Pisac pisac = pisacRepository.findByIdAndDeletedIsFalse(id);
		return pisac;
	}

//logicko brisanje
	public void delete(Long id) {
		Pisac pisac = findOne(id);
		pisac.setDeleted(true);
		save(pisac);
	}
	
	List<Pisac> findByZanrIdAndDeletedIsFalse(Long id){
		List<Pisac> pisci = findAll();
		List<Zanr> zanrovi = zanrRepository.findAll();
		List<Knjiga> knjige = new ArrayList<Knjiga>();
		if(id!=null) {
			for(Zanr z : zanrovi) {
				knjige = z.getKnjige();
				for(Knjiga k : knjige) {
					pisci.add(k.getPisac());
				}
			}
			return pisci;
		}
		throw new IllegalArgumentException("Nema pisaca pokrivenih ovim zanrom");
	}
	List<Pisac> findByDrzavaIdAndDeletedIsFalse(Long id){
		List<Pisac> pisci = findAll();
		List<Drzava> drzave = drzavaRepository.findAll();
		List<Pisac> byDrzava = new ArrayList<Pisac>();
		if(id!=null) {
			for(Drzava d : drzave) {
				byDrzava = d.getPisci();
				for(Pisac p : byDrzava) {
					pisci.add(p);
				}
			}
			return pisci;
		}
		throw new IllegalArgumentException("U bazi nema knjiga zadatog pisca");
	}
}
