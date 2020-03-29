package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Administrator;
import com.biblioteka.Biblioteka.repository.AdministratorRepository;
@Service
@Transactional
public class AdministratorService {
	@Autowired
	AdministratorRepository administratorRepository;

//sacuvaj knjigu
	@Transactional
	public Administrator save(Administrator administrator) {
		if (!(administrator.getEmail()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(administrator.getIme()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(administrator.getPrezime()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(administrator.getPassword()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(administrator.getUsername()  instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		List<Administrator> getAll = findAll();
		for(Administrator a : getAll) {
			if(a.getEmail().equals(administrator.getEmail())) {
				throw new IllegalArgumentException("Postojeca email adresa");
			}
			if(a.getUsername().equals(administrator.getUsername())) {
				throw new IllegalArgumentException("Postojeci username");
			}
		}
		return administratorRepository.save(administrator);
	}

//dobavi sve
	@Transactional
	public List<Administrator> findAll() {
		List<Administrator> list = administratorRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Administrator findOne(Long id) {
		if(id!=null) {
			Administrator administrator = administratorRepository.findOneByDeletedIsFalse(id);
			return administrator;
		}
		throw new IllegalArgumentException("Nepostojeci");
	}

//logicko brisanje
	public void delete(Long id) {
		Administrator administrator = findOne(id);
		administrator.setDeleted(true);
		save(administrator);
	}
}
