package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Osoba;
import com.biblioteka.Biblioteka.model.Primerak;
import com.biblioteka.Biblioteka.model.Zaduzenje;
import com.biblioteka.Biblioteka.repository.PrimerakRepository;
import com.biblioteka.Biblioteka.repository.ZaduzenjeRepository;

@Service
@Transactional
public class PrimerakService {
	@Autowired
	PrimerakRepository primerakRepository;
	@Autowired
	ZaduzenjeRepository zaduzenjeRepository;
//sacuvaj
	@Transactional
	public Primerak save(Primerak primerak) {
		if (!(primerak.getInventarniBroj()  instanceof Integer)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(primerak.getKnjiga().getId()  instanceof Long)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		
		List<Primerak> getAll = findAll();
		for(Primerak p : getAll) {
			if(p.getInventarniBroj().equals(primerak.getInventarniBroj())) {
				throw new IllegalArgumentException("Postojeci inventarni broj");
			}

		}
		return primerakRepository.save(primerak);
	}

//dobavi sve
	@Transactional
	public List<Primerak> findAll() {
		List<Primerak> list = primerakRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Primerak findOne(Long id) {
		if(id!=null) {
			Primerak primerak = primerakRepository.findByIdAndDeletedIsFalse(id);
			return primerak;
		}
		throw new IllegalArgumentException("nepostojeci");
	}

//obrisi odredjenu
	public void delete(Long id) {
		Primerak primerak = findOne(id);
		primerak.setDeleted(true);
		save(primerak);
	}
	

}
