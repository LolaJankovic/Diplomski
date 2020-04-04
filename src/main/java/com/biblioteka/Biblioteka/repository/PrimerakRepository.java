package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.biblioteka.Biblioteka.model.Primerak;

@Repository
public interface PrimerakRepository extends JpaRepository<Primerak, Long> {

	List<Primerak> findAllByDeletedIsFalse();
	Primerak findByIdAndDeletedIsFalse(Long id);
}
