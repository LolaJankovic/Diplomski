package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Primerak;
import com.biblioteka.Biblioteka.model.Zaduzenje;
@Repository
public interface PrimerakRepository extends JpaRepository<Primerak, Long>{
	List<Primerak> findAllByDeletedIsFalse();
	Primerak findOneByDeletedIsFalse(Long id);
	Primerak save(Primerak primerak);
	List<Zaduzenje> findByPrimerakIdAndDeletedIsFalse(Long id);
}
