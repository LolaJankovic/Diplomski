package com.biblioteka.Biblioteka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteka.Biblioteka.dto.AdministratorDTO;
import com.biblioteka.Biblioteka.model.Administrator;
import com.biblioteka.Biblioteka.service.AdministratorService;

@CrossOrigin
@RestController
@RequestMapping("api/admin")
public class AdministratorController {

	@Autowired
	AdministratorService administratorService;

	@GetMapping("")
	public ResponseEntity<List<Administrator>> findAll() {
		List<Administrator> listaAdmina = administratorService.findAll();

		return new ResponseEntity<List<Administrator>>(listaAdmina, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		Optional<Administrator> admin = Optional.ofNullable(administratorService.findOne(id));

		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<Administrator> registrujAdministratora(@RequestBody AdministratorDTO dto) {

		Administrator admin = administratorService.save(dto);

		if (admin == null) {
			return new ResponseEntity<Administrator>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
	}

}
