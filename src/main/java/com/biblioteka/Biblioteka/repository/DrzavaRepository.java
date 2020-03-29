package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Drzava;
import com.biblioteka.Biblioteka.model.Izdavac;
@Repository
public interface DrzavaRepository extends JpaRepository<Drzava, Long> {
	List<Drzava> findAllByDeletedIsFalse();
	Drzava findOneByDeletedIsFalse(Long id);
	Drzava save(Drzava drzava);
	
}