package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Korisnik;
import com.biblioteka.Biblioteka.model.Primerak;
import com.biblioteka.Biblioteka.model.Zaduzenje;
@Repository
public interface ZaduzenjeRepository extends JpaRepository<Zaduzenje, Long> {
	List<Zaduzenje> findAllByDeletedIsFalse();
	Zaduzenje findOneByDeletedIsFalse(Long id);
	Zaduzenje save(Zaduzenje zaduzenje);
	List<Zaduzenje> findByKorisnikIdAndDeletedIsFalse(Long id);

}
