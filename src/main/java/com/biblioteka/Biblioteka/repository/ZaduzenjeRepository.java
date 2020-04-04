package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Zaduzenje;

@Repository
public interface ZaduzenjeRepository extends JpaRepository<Zaduzenje, Long> {

	List<Zaduzenje> findAllByDeletedIsFalse();

	Zaduzenje findByIdAndDeletedIsFalse(Long id);

	List<Zaduzenje> findByKorisnikIdAndDeletedIsFalse(Long id);
}
