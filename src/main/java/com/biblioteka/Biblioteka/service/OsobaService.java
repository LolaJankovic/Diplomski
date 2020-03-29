package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Korisnik;
import com.biblioteka.Biblioteka.model.Osoba;
import com.biblioteka.Biblioteka.repository.OsobaRepository;

@Service
@Transactional
public class OsobaService {
	@Autowired
	OsobaRepository osobaRepository;

//sacuvaj
	@Transactional
	public Osoba save(Osoba osoba) {
		
		if (!(osoba.getIme()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(osoba.getPrezime()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(osoba.getPassword()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(osoba.getUsername()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(osoba.getEmail()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}

		List<Osoba> getAll = findAll();
		for(Osoba o : getAll) {
			if(o.getEmail().equals(osoba.getEmail())) {
				throw new IllegalArgumentException("Postojeca email adresa");
			}
			if(o.getUsername().equals(osoba.getUsername())) {
				throw new IllegalArgumentException("Postojeci username");
			}
		}
		return osobaRepository.save(osoba);
	}

//dobavi sve
	@Transactional
	public List<Osoba> findAll() {
		List<Osoba> list = osobaRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Osoba findOne(Long id) {
		if(id!=null) {
			Osoba osoba = osobaRepository.findByIdAndDeletedIsFalse(id);
			return osoba;
		}
		throw new IllegalArgumentException("nepostojeci");
	}

//logicko brisanje
	public void delete(Long id) {
		Osoba osoba = findOne(id);
		osoba.setDeleted(true);
		save(osoba);
	}

}
