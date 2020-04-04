package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Drzava;

@Repository
public interface DrzavaRepository extends JpaRepository<Drzava, Long> {
	
	List<Drzava> findAllByDeletedIsFalse();

	Drzava findByIdAndDeletedIsFalse(Long id);
}
