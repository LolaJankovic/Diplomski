package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.dto.DrzavaDTO;
import com.biblioteka.Biblioteka.model.Drzava;
import com.biblioteka.Biblioteka.repository.DrzavaRepository;

@Service
@Transactional
public class DrzavaService {

	@Autowired
	DrzavaRepository drzavaRepository;

//sacuvaj knjigu
	@Transactional
	public Drzava save(DrzavaDTO drzavaDto) {

		List<Drzava> getAll = findAll();
		for (Drzava d : getAll) {
			if (d.getNaziv().equals(drzavaDto.getNaziv())) {
				throw new IllegalArgumentException("Drzava vec postoji u bazi");
			}
		}

		Drzava drzava = new Drzava();
		drzava.setNaziv(drzavaDto.getNaziv());

		return drzavaRepository.save(drzava);
	}

// Azuriraj drzavu
	public Drzava update(Drzava drzava) {

		Drzava novaDrzava = drzavaRepository.findByIdAndDeletedIsFalse(drzava.getId());
		if (novaDrzava != null) {
			novaDrzava.setNaziv(drzava.getNaziv());

			return drzavaRepository.save(novaDrzava);
		}
		throw new IllegalArgumentException("Drzava ne postoji u bazi");
	}

//dobavi sve
	@Transactional
	public List<Drzava> findAll() {

		List<Drzava> list = drzavaRepository.findAllByDeletedIsFalse();

		return list;
	}

//dobavi odredjenu
	public Drzava findOne(Long id) {

		Drzava drzava = drzavaRepository.findByIdAndDeletedIsFalse(id);
		if (drzava != null) {

			return drzava;
		}
		throw new IllegalArgumentException("Drzava ne postoji u bazi");
	}

//logicko brisanje
	public boolean delete(Long id) {

		Drzava drzava = drzavaRepository.findByIdAndDeletedIsFalse(id);

		if (drzava != null) {
			drzava.setDeleted(true);
			drzavaRepository.save(drzava);

			return true;
		}
		throw new IllegalArgumentException("Drzava ne postoji u bazi");
	}

}
