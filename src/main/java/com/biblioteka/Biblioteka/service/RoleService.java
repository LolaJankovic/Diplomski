package com.biblioteka.Biblioteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.model.Osoba;
import com.biblioteka.Biblioteka.model.Role;
import com.biblioteka.Biblioteka.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	@Autowired
	RoleRepository roleRepository;

//sacuvaj
	@Transactional
	public Role save(Role role) {
		if (!(role.getNaziv()  instanceof Enum)) {
			throw new IllegalArgumentException("neodgovarajuci tip podatka");
		}
		
		List<Role> getAll = findAll();
		for(Role r : getAll) {
			if(!r.getNaziv().equals(role.getNaziv())) {
				throw new IllegalArgumentException("Nepostojeca rola");
			}
			
		}
		return roleRepository.save(role);
	}

//dobavi sve
	@Transactional
	public List<Role> findAll() {
		List<Role> list = roleRepository.findAllByDeletedIsFalse();
		return list;
	}

//dobavi odredjenu
	public Role findOne(Long id) {
		if(id!=null) {
			Role role = roleRepository.findOneByDeletedIsFalse(id);
			return role;
		}
		throw new IllegalArgumentException("Nepostojeci");
	}

//logicko brisanje
	public void delete(Long id) {
		Role role = findOne(id);
		role.setDeleted(true);
		save(role);
	}

}
