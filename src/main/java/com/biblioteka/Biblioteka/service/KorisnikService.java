package com.biblioteka.Biblioteka.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Administrator;
import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Korisnik;
import com.biblioteka.Biblioteka.repository.KorisnikRepository;


@Service
@Transactional
public class KorisnikService {
	@Autowired
	KorisnikRepository korisnikRepository;

//sacuvaj
	@Transactional
	public Korisnik save(Korisnik korisnik) {
		
			if (!(korisnik.getAdresa()  instanceof String)) {
				throw new IllegalArgumentException("neodgovarajuci tip podatka");
			}
			if (!(korisnik.getIme()  instanceof String)) {
				throw new IllegalArgumentException("neodgovarajuci tip podatka");
			}
			if (!(korisnik.getPrezime()  instanceof String)) {
				throw new IllegalArgumentException("neodgovarajuci tip podatka");
			}
			if (!(korisnik.getPassword()  instanceof String)) {
				throw new IllegalArgumentException("neodgovarajuci tip podatka");
			}
			if (!(korisnik.getUsername()  instanceof String)) {
				throw new IllegalArgumentException("neodgovarajuci tip podatka");
			}
			if (!(korisnik.getEmail()  instanceof String)) {
				throw new IllegalArgumentException("neodgovarajuci tip podatka");
			}
	
			List<Korisnik> getAll = findAll();
			for(Korisnik k : getAll) {
				if(k.getEmail().equals(korisnik.getEmail())) {
					throw new IllegalArgumentException("Postojeca email adresa");
				}
				if(k.getUsername().equals(korisnik.getUsername())) {
					throw new IllegalArgumentException("Postojeci username");
				}
			}
		return korisnikRepository.save(korisnik);
	}

//dobavi sve
	@Transactional
	public List<Korisnik> findAll() {
		List<Korisnik> list = korisnikRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Korisnik findOne(Long id) {
		if(id!=null) {
			Korisnik korisnik = korisnikRepository.findByIdAndDeletedIsFalse(id);
			return korisnik;
		}
		throw new IllegalArgumentException("Postojeci username");
	}

//logicko brisanje
	public void delete(Long id) {
		Korisnik korisnik = findOne(id);
		korisnik.setDeleted(true);
		save(korisnik);
	}
	Korisnik findByEmailAndDeletedIsFalse(String email) {
		if(!email.equals(null)) {
			if(email instanceof String) {
				List<Korisnik> korisnici = findAll();
				for(Korisnik k : korisnici) {
					if(k.getEmail().equals(email))
						return k;
				}
				throw new IllegalArgumentException("neodgovarajuci tip unetog podatka");
			}
		}
		throw new IllegalArgumentException("Nepostojaca email adresa");
	}
}
