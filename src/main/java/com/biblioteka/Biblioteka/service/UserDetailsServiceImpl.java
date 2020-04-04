package com.biblioteka.Biblioteka.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biblioteka.Biblioteka.model.Osoba;
import com.biblioteka.Biblioteka.repository.OsobaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private OsobaRepository osobaRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Osoba osoba = osobaRepository.findByUsername(username);

		return UserPrinciple.build(osoba);
	}
}
