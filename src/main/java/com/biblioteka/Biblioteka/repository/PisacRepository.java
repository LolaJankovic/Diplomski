package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Pisac;

@Repository
public interface PisacRepository extends JpaRepository<Pisac, Long> {

	List<Pisac> findByDrzavaNazivAndDeletedIsFalse(String naziv);

	List<Pisac> findAllByDeletedIsFalse();

	Pisac findByIdAndDeletedIsFalse(Long id);
}
