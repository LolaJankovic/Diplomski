package com.biblioteka.Biblioteka.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteka.Biblioteka.dto.IzdavacDTO;
import com.biblioteka.Biblioteka.model.Izdavac;
import com.biblioteka.Biblioteka.repository.IzdavacRepository;

@Service
@Transactional
public class IzdavacService {

	@Autowired
	IzdavacRepository izdavacRepository;

	@Transactional
	public List<Izdavac> findAll() {

		return izdavacRepository.findAllByDeletedIsFalse();
	}

	@Transactional
	public Izdavac findOne(Long id) {

		Izdavac izdavac = izdavacRepository.findByIdAndDeletedIsFalse(id);
		if (izdavac != null) {
			return izdavac;
		}

		throw new IllegalArgumentException("Trazeni izdavac ne postoji");
	}

	@Transactional
	public Izdavac findByNaziv(String naziv) {

		Izdavac izdavac = izdavacRepository.findByNazivAndDeletedIsFalse(naziv);
		if (izdavac != null) {
			return izdavac;
		}

		throw new IllegalArgumentException("Izdavac sa tim imenom ne postoji");
	}

	@Transactional
	public Izdavac save(IzdavacDTO izdavacDto) {

		List<Izdavac> getAll = findAll();
		for (Izdavac izdavac : getAll) {
			if (izdavac.getNaziv().equals(izdavacDto.getNaziv())) {
				throw new IllegalArgumentException("Postojeci izdavac");
			}
		}

		Izdavac izdavac = new Izdavac();
		izdavac.setNaziv(izdavacDto.getNaziv());
		izdavac.setGrad(izdavacDto.getGrad());

		return izdavacRepository.save(izdavac);
	}

	@Transactional
	public Izdavac update(Izdavac izdavac) {

		Izdavac noviIzdavac = izdavacRepository.findByIdAndDeletedIsFalse(izdavac.getId());
		if (noviIzdavac != null) {

			noviIzdavac.setGrad(izdavac.getGrad());
			noviIzdavac.setNaziv(izdavac.getNaziv());

			return izdavac;
		}

		throw new IllegalArgumentException("Pokusaj azuriranja nepostojeceg");
	}

	@Transactional
	public boolean delete(Long id) {

		Izdavac izdavac = izdavacRepository.findByIdAndDeletedIsFalse(id);
		if (izdavac != null) {

			izdavac.setDeleted(true);
			izdavacRepository.save(izdavac);

			return true;
		}

		throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg");
	}

}
