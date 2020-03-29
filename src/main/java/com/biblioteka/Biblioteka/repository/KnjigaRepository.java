package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Knjiga;
@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {
    List<Knjiga> findAllByDeletedIsFalse();
    Knjiga findByIdAndDeletedIsFalse(Long id);
    List<Knjiga> findByIzdavacIdAndDeletedIsFalse(Long id);
    List<Knjiga> findByPisacIdAndDeletedIsFalse(Long id);
    List<Knjiga> findByZanrIdAndDeletedIsFalse(Long id);
    List<Knjiga> findByAdministratorIdAndDeletedIsFalse(Long id);
    Knjiga findByIdAndPisacIdAndDeletedIsFalse(Long idK, Long idP);
	//Page<Knjiga> findAll(int page);
}
