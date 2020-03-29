package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Pisac;
@Repository
public interface PisacRepository extends JpaRepository<Pisac, Long>{
	List<Pisac> findByDrzavaIdAndDeletedIsFalse(Long id);
	List<Pisac> findByKnjigaIdAndDeletedIsFalse(Long id);
	List<Pisac> findByZanrIdAndDeletedIsFalse(Long id);
	List<Pisac> findAllByDeletedIsFalse();
	Pisac findOneByDeletedIsFalse(Long id);
	Pisac save(Pisac pisac);
	
}
