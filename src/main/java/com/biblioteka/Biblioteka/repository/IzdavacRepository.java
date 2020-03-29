package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Izdavac;
@Repository
public interface IzdavacRepository extends JpaRepository<Izdavac, Long>{
	List<Izdavac> findAllByDeletedIsFalse();
	Izdavac findOneByDeletedIsFalse(Long id);
	Izdavac save(Izdavac izdavac);
	Izdavac findByNazivAndDeletedIsFalse(String naziv);
}
