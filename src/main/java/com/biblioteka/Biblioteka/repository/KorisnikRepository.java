package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

	List<Korisnik> findAllByDeletedIsFalse();

	Korisnik findByIdAndDeletedIsFalse(Long id);

	Korisnik findByEmailAndDeletedIsFalse(String email);

	Korisnik findByRegistracioniLink(String registracioniLink);

	Korisnik findByUsernameAndDeletedIsFalse(String username);
}
