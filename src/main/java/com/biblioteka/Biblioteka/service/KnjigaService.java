package com.biblioteka.Biblioteka.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Administrator;
import com.biblioteka.Biblioteka.model.Izdavac;
import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Pisac;
import com.biblioteka.Biblioteka.model.Zanr;
import com.biblioteka.Biblioteka.repository.AdministratorRepository;
import com.biblioteka.Biblioteka.repository.IzdavacRepository;
import com.biblioteka.Biblioteka.repository.KnjigaRepository;
import com.biblioteka.Biblioteka.repository.PisacRepository;
import com.biblioteka.Biblioteka.repository.ZanrRepository;

@Service
@Transactional
public class KnjigaService {
	@Autowired
	KnjigaRepository knjigaRepository;
	@Autowired
	PisacRepository pisacRepository;
	@Autowired
	ZanrRepository zanrRepository;
	@Autowired
	IzdavacRepository izdavacRepository;
	@Autowired
	AdministratorRepository administratorRepository;

//sacuvaj knjigu
	@Transactional
	public Knjiga save(Knjiga knjiga) {
		if (!(knjiga.getGodinaIzdavanja() instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(knjiga.getNaziv() instanceof String)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(knjiga.getIzdavac().getId() instanceof Long)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(knjiga.getAdministrator().getId() instanceof Long)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(knjiga.getPisac().getId() instanceof Long)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		if (!(knjiga.getZanr().getId() instanceof Long)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}

		return knjigaRepository.save(knjiga);
	}

//dobavi sve
	@Transactional
	public List<Knjiga> findAll() {
		List<Knjiga> list = knjigaRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Knjiga findOne(Long id) {
		Knjiga knjiga = knjigaRepository.findByIdAndDeletedIsFalse(id);
		return knjiga;
	}

//logicko brisanje
	public void delete(Long id) {
		Knjiga knjiga = findOne(id);
		knjiga.setDeleted(true);
		save(knjiga);
	}

//pronadji po nazivu i piscu
	public Knjiga findByIdAndPisacId(Long idK, Long idP) {
		List<Knjiga> getAll = findAll();
		Knjiga findBy = new Knjiga();
		
		if (idK != null && idP != null) {
			for(Knjiga k : getAll) {
				if(findOne(idK).equals(k)
					&& findOne(idK).getPisac().getId()==idP){
					findBy = k;
				}
			}
			return findBy;
		}
			
		throw new IllegalArgumentException("Nema takve knjige u bazi");
		
	}

	public List<Knjiga> findByPisacId(Long id) {
		List<Pisac> getAll = pisacRepository.findAll();
		if (id != null) {
			for (Pisac p : getAll) {
				if (id == p.getId()) {
					return p.getKnjige();
				}
			}
		}
		throw new IllegalArgumentException("U bazi nema knjiga navedenog pisca");
	}

	public List<Knjiga> findByZanrId(Long id) {
		List<Zanr> getAll = zanrRepository.findAllByDeletedIsFalse();
		if (id != null) {
			for (Zanr z : getAll) {
				if (id == z.getId())
					return z.getKnjige();
			}
		}
		throw new IllegalArgumentException("U Bazi nema knjiga navedenog zanra");
	}

	public List<Knjiga> findByIzdavacId(Long id) {
		List<Izdavac> getAll = izdavacRepository.findAllByDeletedIsFalse();
		if (id != null) {
			for (Izdavac i : getAll) {
				if (id == i.getId()) {
					return i.getKnjige();
				}
			}
		}
		throw new IllegalArgumentException("Nema knjiga ovog izdavaca");
	}

	public List<Knjiga> findByAdministratorId(Long id) {
		List<Administrator> getAll = administratorRepository.findAllByDeletedIsFalse();
		if (id != null) {
			for (Administrator a : getAll) {
				if (id == a.getId()) {
					return a.getKnjige();
				}
			}
		}
		throw new IllegalArgumentException("Admin nije uneo nijednu knjigu");
	}
//izmena ce se raditi u kontroleru

}
