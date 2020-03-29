package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Korisnik;
import com.biblioteka.Biblioteka.model.Osoba;
@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	List<Korisnik> findAllByDeletedIsFalse();
	Korisnik findOneByDeletedIsFalse(Long id);
	Korisnik save(Korisnik Korisnik);
	Korisnik findByEmailAndDeletedIsFalse(String email);
	
}
