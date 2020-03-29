package com.biblioteka.Biblioteka.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Osoba;
import com.biblioteka.Biblioteka.model.Pisac;
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
//sacuvaj 
	@Transactional
	public Zanr save(Zanr zanr) {
		if (!(zanr.getNaziv()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}	
		List<Zanr> getAll = findAll();
		for(Zanr z : getAll) {
			if(z.getNaziv().equals(zanr.getNaziv())) {
				throw new IllegalArgumentException("Postojeci zanr");
			}
		}
		return zanrRepository.save(zanr);
	}

//dobavi sve
	@Transactional
	public List<Zanr> findAll() {
		List<Zanr> list = zanrRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Zanr findOne(Long id) {
		if(id!=null) {
			Zanr zanr = zanrRepository.findByIdAndDeletedIsFalse(id);
			return zanr;
		}
		throw new IllegalArgumentException("nepostojeci");
	}

//logicko brisanje
	public void delete(Long id) {
		Zanr zanr = findOne(id);
		zanr.setDeleted(true);
		save(zanr);
	}
	
	public List<Zanr> findByPisacId(Long id){
		List<Pisac> list = pisacRepository.findAllByDeletedIsFalse();
		List<Zanr> zanroviByPisac = new ArrayList<Zanr>();
		for(Pisac p : list) {
			List<Knjiga> knjige = p.getKnjige();
			for(Knjiga k : knjige) {
				zanroviByPisac.add(k.getZanr());
			}
			return zanroviByPisac;
		}
		
		throw new IllegalArgumentException("nema zaduzenih primeraka");
	}
}
