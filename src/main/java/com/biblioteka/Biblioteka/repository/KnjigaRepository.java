package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Knjiga;

@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga, Long> {

	List<Knjiga> findAllByDeletedIsFalse();

	Knjiga findByIdAndDeletedIsFalse(Long id);

	Knjiga findByNazivAndDeletedIsFalse(String naziv);

	List<Knjiga> findByGodinaIzdavanjaAndDeletedIsFalse(String godina);

	List<Knjiga> findByIzdavacNazivAndDeletedIsFalse(String naziv);

	List<Knjiga> findByZanrNazivAndDeletedIsFalse(String zanr);

	List<Knjiga> findByAdministratorIdAndDeletedIsFalse(Long id);

}
