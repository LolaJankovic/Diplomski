package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

	List<Administrator> findAll();
}
