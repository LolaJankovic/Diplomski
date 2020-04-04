package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.dto.ZanrDTO;
import com.biblioteka.Biblioteka.model.Zanr;
import com.biblioteka.Biblioteka.repository.PisacRepository;
import com.biblioteka.Biblioteka.repository.ZanrRepository;

@Service
@Transactional
public class ZanrService {

	@Autowired
	ZanrRepository zanrRepository;

	@Autowired
	PisacRepository pisacRepository;

//dobavi sve
	@Transactional
	public List<Zanr> findAll() {

		return zanrRepository.findAllByDeletedIsFalse();
	}

//dobavi odredjenu
	@Transactional
	public Zanr findOne(Long id) {

		Zanr zanr = zanrRepository.findByIdAndDeletedIsFalse(id);
		if (zanr != null) {

			return zanr;
		}

		throw new IllegalArgumentException("Zanr ne postoji");
	}

//sacuvaj 
	@Transactional
	public Zanr save(ZanrDTO zanrDto) {

		Zanr zanr = zanrRepository.findByNazivAndDeletedIsFalse(zanrDto.getNaziv());

		if (zanr == null) {

			Zanr noviZanr = new Zanr();
			noviZanr.setNaziv(zanrDto.getNaziv());

			return zanrRepository.save(noviZanr);
		}

		throw new IllegalArgumentException("Zanr vec postoji");
	}

// azuriraj zanr
	@Transactional
	public Zanr update(Zanr zanr) {

		Zanr noviZanr = zanrRepository.findByIdAndDeletedIsFalse(zanr.getId());
		if (noviZanr != null) {

			noviZanr.setNaziv(zanr.getNaziv());
			return zanrRepository.save(noviZanr);
		}

		throw new IllegalArgumentException("Zanr ne postoji");
	}

//logicko brisanje
	@Transactional
	public boolean delete(Long id) {

		Zanr zanr = findOne(id);
		zanr.setDeleted(true);
		zanrRepository.save(zanr);
		return true;

	}

}
