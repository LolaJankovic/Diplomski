 package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Administrator;
import com.biblioteka.Biblioteka.model.Drzava;

import com.biblioteka.Biblioteka.repository.DrzavaRepository;
@Service
@Transactional
public class DrzavaService {
	@Autowired
	DrzavaRepository drzavaRepository;

//sacuvaj knjigu
	@Transactional
	public Drzava save(Drzava drzava) {
		if (!(drzava.getNaziv()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		List<Drzava> getAll = findAll();
		for(Drzava d : getAll) {
			if(d.getNaziv().equals(drzava.getNaziv())) {
				throw new IllegalArgumentException("Drzava vec postoji u bazi");
			}
		}
		return drzavaRepository.save(drzava);
	}

//dobavi sve
	@Transactional
	public List<Drzava> findAll() {
		List<Drzava> list = drzavaRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Drzava findOne(Long id) {
		if(id!=null) {
			Drzava drzava = drzavaRepository.findByIdAndDeletedIsFalse(id);
			return drzava;
		}
		throw new IllegalArgumentException("Ne postoji u bazi");
		
	}

//logicko brisanje
	public void delete(Long id) {
		Drzava drzava = findOne(id);
		drzava.setDeleted(true);
		save(drzava);
	}

}
