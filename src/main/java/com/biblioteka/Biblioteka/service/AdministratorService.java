package com.biblioteka.Biblioteka.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteka.Biblioteka.dto.AdministratorDTO;
import com.biblioteka.Biblioteka.enumeration.TipRole;
import com.biblioteka.Biblioteka.model.Administrator;
import com.biblioteka.Biblioteka.model.Role;
import com.biblioteka.Biblioteka.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	PasswordEncoder encoder;

//sacuvaj administratora
	@Transactional
	public Administrator save(AdministratorDTO administrator) {
		if (!isValidEmail(administrator.getEmail())) {
			throw new IllegalArgumentException("Neodgovarajuca email adresa");
		}
		if (administrator.getPassword().length() < 8) {
			throw new IllegalArgumentException("Lozinka ne sme biti kraca od 8 karaktera");
		}
		List<Administrator> adminList = findAll();
		for (Administrator admin : adminList) {
			if (admin.getEmail().equals(administrator.getEmail())) {
				throw new IllegalArgumentException("Postojeca email adresa");
			}
			if (admin.getUsername().equals(administrator.getUsername())) {
				throw new IllegalArgumentException("Postojeci username");
			}
		}
		Administrator admin = new Administrator();
		admin.setEmail(administrator.getEmail());
		admin.setUsername(administrator.getUsername());
		admin.setPassword(encoder.encode(administrator.getPassword()));
		admin.setIme(administrator.getIme());
		admin.setPrezime(administrator.getPrezime());

		Role role = new Role(TipRole.ROLE_ADMINISTRATOR);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		admin.setRoles(roles);

		return administratorRepository.save(admin);
	}

//dobavi sve
	@Transactional
	public List<Administrator> findAll() {
		return administratorRepository.findAll();
	}

//dobavi odredjenu
	public Administrator findOne(Long id) throws IllegalArgumentException {

		return administratorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Administrator nije pronadjen"));
	}

	public boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pattern.matcher(email).matches();
	}
}
