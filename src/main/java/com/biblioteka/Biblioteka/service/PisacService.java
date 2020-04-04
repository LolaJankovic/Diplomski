package com.biblioteka.Biblioteka.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteka.Biblioteka.dto.PisacDTO;
import com.biblioteka.Biblioteka.model.Drzava;
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

//dobavi sve
	@Transactional
	public List<Pisac> findAll() {

		return pisacRepository.findAllByDeletedIsFalse();
	}

//dobavi odredjenu
	public Pisac findOne(Long id) {

		return pisacRepository.findByIdAndDeletedIsFalse(id);
	}

// Dobavi sve po zanru
	public List<Pisac> findByZanr(String naziv) {

		List<Pisac> pisci = new ArrayList<Pisac>();

		for (Pisac pisac : findAll()) {
			for (Zanr zanr : pisac.getZanrovi()) {
				if (zanr.getNaziv().equals(naziv))
					pisci.add(pisac);
			}
		}

		return pisci;
	}

// Dobavi sve po drzavi
	public List<Pisac> findByDrzava(String drzava) {

		return pisacRepository.findByDrzavaNazivAndDeletedIsFalse(drzava);
	}

//sacuvaj
	@Transactional
	public Pisac save(PisacDTO pisacDto) {

		Drzava drzava = drzavaRepository.getOne(pisacDto.getDrzavaId());

		if (drzava == null) {

			throw new IllegalArgumentException("Nepostojeca drzava");
		}

		Set<Zanr> zanrovi = new HashSet<Zanr>();

		for (Long id : pisacDto.getZanrIds()) {
			Zanr zanr = zanrRepository.findByIdAndDeletedIsFalse(id);

			if (zanr != null)
				zanrovi.add(zanr);
		}

		Pisac pisac = new Pisac();
		pisac.setIme(pisacDto.getIme());
		pisac.setPrezime(pisacDto.getPrezime());
		pisac.setDrzava(drzava);
		pisac.setZanrovi(zanrovi);

		return pisacRepository.save(pisac);
	}

//logicko brisanje
	public boolean delete(Long id) {

		Pisac pisac = findOne(id);
		pisac.setDeleted(true);
		pisacRepository.save(pisac);

		return true;
	}

}
