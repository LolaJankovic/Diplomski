package com.biblioteka.Biblioteka.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.biblioteka.Biblioteka.model.Osoba;
import com.biblioteka.Biblioteka.repository.OsobaRepository;

public class LoginService {
	@Autowired
	  private OsobaRepository osobaRepository;
	
	   public Osoba login(Osoba osoba) {
	        final String email = osoba.getEmail();
	        final Osoba findByEmail = osobaRepository.findByEmailAndDeletedIsFalse(email);
	        if (findByEmail == null) {
	            return null;
	        }
	        if (!findByEmail.getPassword().equals(osoba.getPassword())) {
	            return null;
	        }
	        
	        return findByEmail;
	    }
}
