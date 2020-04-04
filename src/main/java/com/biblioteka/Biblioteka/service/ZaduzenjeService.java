package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.dto.ZaduzenjeDTO;
import com.biblioteka.Biblioteka.model.Korisnik;
import com.biblioteka.Biblioteka.model.Primerak;
import com.biblioteka.Biblioteka.model.Zaduzenje;
import com.biblioteka.Biblioteka.repository.KorisnikRepository;
import com.biblioteka.Biblioteka.repository.PrimerakRepository;
import com.biblioteka.Biblioteka.repository.ZaduzenjeRepository;

@Service
@Transactional
public class ZaduzenjeService {

	@Autowired
	ZaduzenjeRepository zaduzenjeRepository;

	@Autowired
	KorisnikRepository korisnikRepository;

	@Autowired
	PrimerakRepository primerakRepository;

// dobavi sve
	@Transactional
	public List<Zaduzenje> findAll() {

		return zaduzenjeRepository.findAllByDeletedIsFalse();
	}

// dobavi odredjenu
	@Transactional
	public Zaduzenje findOne(Long id) {

		Zaduzenje zaduzenje = zaduzenjeRepository.findByIdAndDeletedIsFalse(id);
		if (zaduzenje != null) {

			return zaduzenje;
		}
		throw new IllegalArgumentException("Nepostojece zaduzenje");
	}

// Nadji po korsniku
	@Transactional
	public List<Zaduzenje> findByKorisnikId(Long id) {

		return zaduzenjeRepository.findByKorisnikIdAndDeletedIsFalse(id);
	}

//sacuvaj
	@Transactional
	public Zaduzenje save(ZaduzenjeDTO zaduzenjeDto) {

		Korisnik korisnik = korisnikRepository.findByUsernameAndDeletedIsFalse(zaduzenjeDto.getUsername());
		Primerak primerak = primerakRepository.findByIdAndDeletedIsFalse(zaduzenjeDto.getPrimerakId());

		Zaduzenje zaduzenje = new Zaduzenje();
		primerak.setZaduzen(true);
		zaduzenje.setPrimerak(primerak);
		zaduzenje.setKorisnik(korisnik);

		primerakRepository.save(primerak);
		return zaduzenjeRepository.save(zaduzenje);
	}

//logicko brisanje
	@Transactional
	public boolean delete(Long id) {

		Zaduzenje zaduzenje = findOne(id);
		Primerak primerak = primerakRepository.findByIdAndDeletedIsFalse(zaduzenje.getPrimerak().getId());
		primerak.setZaduzen(false);
		zaduzenje.setDeleted(true);

		primerakRepository.save(primerak);
		zaduzenjeRepository.save(zaduzenje);

		return true;
	}

}
