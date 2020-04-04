package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Zanr;

@Repository
public interface ZanrRepository extends JpaRepository<Zanr, Long> {

	List<Zanr> findAllByDeletedIsFalse();

	Zanr findByIdAndDeletedIsFalse(Long id);

	Zanr findByNazivAndDeletedIsFalse(String naziv);
}
