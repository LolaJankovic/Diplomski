package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.dto.PrimerakDTO;
import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Primerak;
import com.biblioteka.Biblioteka.repository.KnjigaRepository;
import com.biblioteka.Biblioteka.repository.PrimerakRepository;

@Service
@Transactional
public class PrimerakService {

	@Autowired
	PrimerakRepository primerakRepository;

	@Autowired
	KnjigaRepository knjigaRepository;

// dobavi sve
	@Transactional
	public List<Primerak> findAll() {

		return primerakRepository.findAllByDeletedIsFalse();

	}

// dobavi odredjenu
	public Primerak findOne(Long id) {

		Primerak primerak = primerakRepository.findByIdAndDeletedIsFalse(id);
		if (primerak != null) {

			return primerak;
		}
		throw new IllegalArgumentException("Nepostojeci primerak");
	}

//sacuvaj
	@Transactional
	public Primerak save(PrimerakDTO primerakDto) {

		List<Primerak> primerci = findAll();
		for (Primerak primerak : primerci) {
			if (primerak.getInventarniBroj().equals(primerakDto.getInventarniBroj())) {

				throw new IllegalArgumentException("Postojeci inventarni broj");
			}
		}

		Knjiga knjiga = knjigaRepository.findByIdAndDeletedIsFalse(primerakDto.getKnjigaId());

		Primerak primerak = new Primerak();
		primerak.setInventarniBroj(primerakDto.getInventarniBroj());
		primerak.setKnjiga(knjiga);
		primerak.setZaduzen(false);

		return primerakRepository.save(primerak);
	}

//obrisi odredjenu
	public boolean delete(Long id) {

		Primerak primerak = findOne(id);
		primerak.setDeleted(true);
		primerakRepository.save(primerak);

		return true;
	}

}
