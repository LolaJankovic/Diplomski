package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Osoba;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {

	List<Osoba> findAll();

	Osoba findByUsername(String username);

	Osoba findByEmail(String email);
}
