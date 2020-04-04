package com.biblioteka.Biblioteka.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.biblioteka.Biblioteka.enumeration.TipRole;
import com.biblioteka.Biblioteka.model.Administrator;
import com.biblioteka.Biblioteka.model.Role;
import com.biblioteka.Biblioteka.repository.AdministratorRepository;

@Component
public class ApplicationLoader implements ApplicationRunner {

	@Autowired
	private AdministratorRepository adminRepository;

	public ApplicationLoader() {
		super();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Kreiraj admina ako ne postoji ni jedna u bazi
		if (adminRepository.findAll().size() == 0) {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			Role admin_role = new Role();
			admin_role.setNaziv(TipRole.ROLE_ADMINISTRATOR);
			Set<Role> roles = new HashSet<>();
			roles.add(admin_role);

			Administrator admin = new Administrator();

			admin.setEmail("admin@email.com");
			admin.setUsername("password");
			admin.setPassword(encoder.encode("admin"));
			admin.setIme("System");
			admin.setPrezime("Admin");
			admin.setRoles(roles);

			adminRepository.save(admin);
			System.out.println("Admin kreiran!");
		}

	}
}
