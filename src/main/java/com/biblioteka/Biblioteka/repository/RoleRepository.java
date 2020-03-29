package com.biblioteka.Biblioteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteka.Biblioteka.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	List<Role> findAllByDeletedIsFalse();
	Role findOneByDeletedIsFalse(Long id);
	Role save(Role role);
}
