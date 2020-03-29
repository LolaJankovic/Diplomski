package com.biblioteka.Biblioteka.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteka.Biblioteka.model.Administrator;
import com.biblioteka.Biblioteka.model.Izdavac;
import com.biblioteka.Biblioteka.repository.IzdavacRepository;
@Service
@Transactional
public class IzdavacService {
	@Autowired
	IzdavacRepository izdavacRepository;

	@Transactional
	public List<Izdavac> findAll() {
		List<Izdavac> lista = izdavacRepository.findAllByDeletedIsFalse();
		return lista;
	}

	@Transactional
	public Izdavac findOne(Long id) {
		if(id!=null) {
			Izdavac izdavac = izdavacRepository.findOneByDeletedIsFalse(id);
			return izdavac;
		}
		
		throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg");
	}

	@Transactional
	public Izdavac save(Izdavac izdavac) {
		if (!(izdavac.getGrad()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(izdavac.getNaziv()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		
		List<Izdavac> getAll = findAll();
		for(Izdavac i : getAll) {
			if(i.getNaziv().equals(izdavac.getNaziv())) {
				throw new IllegalArgumentException("Postojeci izdavac");
			}
		}
		return izdavacRepository.save(izdavac);
	}

	@Transactional
	public void delete(Long id) {
		Izdavac izdavac = findOne(id);
		izdavac.setDeleted(true);
		save(izdavac);

	}
	
	Izdavac findByNazivAndDeletedIsFalse(String naziv) {
		List<Izdavac> getAll = findAll();
		if(naziv instanceof String) {
			
		}
		for(Izdavac i : getAll) {
				if(i.getNaziv().equalsIgnoreCase(naziv)) {
				return i;
			}
		}
		throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg");
	}

}
