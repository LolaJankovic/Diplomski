package com.biblioteka.Biblioteka.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Korisnik;
import com.biblioteka.Biblioteka.model.Osoba;
import com.biblioteka.Biblioteka.model.Zaduzenje;
import com.biblioteka.Biblioteka.repository.KorisnikRepository;
import com.biblioteka.Biblioteka.repository.ZaduzenjeRepository;

@Service
@Transactional
public class ZaduzenjeService {
	@Autowired
	ZaduzenjeRepository zaduzenjeRepository;
	@Autowired
	KorisnikRepository korisnikRepository;

//sacuvaj
	@Transactional
	public Zaduzenje save(Zaduzenje zaduzenje) {
		if (!(zaduzenje.getKorisnik().getId()  instanceof Long)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(zaduzenje.getPrimerak().getId()  instanceof Long)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		return zaduzenjeRepository.save(zaduzenje);
	}

//dobavi sve
	@Transactional
	public List<Zaduzenje> findAll() {
		List<Zaduzenje> list = zaduzenjeRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Zaduzenje findOne(Long id) {
		if(id!=null) {
			Zaduzenje zaduzenje = zaduzenjeRepository.findByIdAndDeletedIsFalse(id);
			return zaduzenje;
		}
		throw new IllegalArgumentException("nepostojeci");
	}

//logicko brisanje
	public void delete(Long id) {
		Zaduzenje zaduzenje = findOne(id);
		zaduzenje.setDeleted(true);
		save(zaduzenje);
	}
	
	public List<Zaduzenje> findByKorisnikId(Long id){
		List<Korisnik> list = korisnikRepository.findAll();
		for(Korisnik k : list) {
			for(Zaduzenje z : k.getZaduzenja()) {
				if(z.isZaduzena()==(findOne(id).isZaduzena()))
					return k.getZaduzenja();
			}
		}
		throw new IllegalArgumentException("nema zaduzenih korisnika");
	}


}
