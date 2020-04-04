package com.biblioteka.Biblioteka.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.dto.KnjigaDTO;
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

//dobavi sve
	@Transactional
	public List<Knjiga> findAll() {

		return knjigaRepository.findAllByDeletedIsFalse();
	}

//dobavi odredjenu
	@Transactional
	public Knjiga findOne(Long id) {

		Knjiga knjiga = knjigaRepository.findByIdAndDeletedIsFalse(id);
		if (knjiga != null)
			return knjiga;

		throw new IllegalArgumentException("Nema takve knjige u bazi");
	}

// pronadji po nazivu
	@Transactional
	public Knjiga findByNaziv(String naziv) {

		return knjigaRepository.findByNazivAndDeletedIsFalse(naziv);
	}

// pronadji po godini izdavanja
	@Transactional
	public List<Knjiga> findByGodinjaIzdavanja(String godina) {

		List<Knjiga> knjige = knjigaRepository.findByGodinaIzdavanjaAndDeletedIsFalse(godina);

		return knjige;
	}

// Pronadji knjige po id pisca
	@Transactional
	public Set<Knjiga> findByPisacId(Long id) {

		List<Pisac> pisci = pisacRepository.findAll();

		for (Pisac pisac : pisci) {
			if (id == pisac.getId()) {

				return pisac.getKnjige();
			}
		}

		Set<Knjiga> emptySet = new HashSet<Knjiga>();

		return emptySet;
	}

// Pronadji po zanru
	@Transactional
	public List<Knjiga> findByZanr(String zanr) {

		return knjigaRepository.findByZanrNazivAndDeletedIsFalse(zanr);
	}

// Pronadji po izdavacu	
	@Transactional
	public List<Knjiga> findByIzdavac(String naziv) {

		return knjigaRepository.findByIzdavacNazivAndDeletedIsFalse(naziv);
	}

// Pronadji po adminu
	@Transactional
	public List<Knjiga> findByAdministratorId(Long id) {

		return knjigaRepository.findByAdministratorIdAndDeletedIsFalse(id);
	}

// sacuvaj knjigu
	@Transactional
	public Knjiga save(KnjigaDTO knjigaDto) {

		Izdavac izdavac = izdavacRepository.findByIdAndDeletedIsFalse(knjigaDto.getIzdavacId());
		Zanr zanr = zanrRepository.findByIdAndDeletedIsFalse(knjigaDto.getZanrId());
		Optional<Administrator> admin = administratorRepository.findById(knjigaDto.getAdminId());

		Set<Pisac> pisci = new HashSet<Pisac>();

		for (Long id : knjigaDto.getPisciIds()) {
			Pisac pisac = pisacRepository.findByIdAndDeletedIsFalse(id);
			pisci.add(pisac);
		}

		Knjiga knjiga = new Knjiga();
		knjiga.setNaziv(knjigaDto.getNaziv());
		knjiga.setAdministrator(admin.get());
		knjiga.setGodinaIzdavanja(knjigaDto.getGodinaIzdavanja());
		knjiga.setIzdavac(izdavac);
		knjiga.setZanr(zanr);
		knjiga.setPisci(pisci);

		return knjigaRepository.save(knjiga);
	}

// Azuriranje knjige
	@Transactional
	public Knjiga update(Knjiga knjiga) {

		return null;
	}

//logicko brisanje
	@Transactional
	public boolean delete(Long id) {

		Knjiga knjiga = knjigaRepository.findByIdAndDeletedIsFalse(id);
		if (knjiga != null) {

			knjiga.setDeleted(true);

			return true;
		}

		throw new IllegalArgumentException("Nema takve knjige u bazi");
	}

}
