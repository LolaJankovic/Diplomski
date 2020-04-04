package com.biblioteka.Biblioteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteka.Biblioteka.dto.JwtResponse;
import com.biblioteka.Biblioteka.dto.LoginDTO;
import com.biblioteka.Biblioteka.dto.PasswordUpdate;
import com.biblioteka.Biblioteka.dto.RegistracijaDTO;
import com.biblioteka.Biblioteka.model.Korisnik;
import com.biblioteka.Biblioteka.service.KorisnikService;

@CrossOrigin
@RestController
@RequestMapping("api/korisnik")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;

	@GetMapping("")
	public ResponseEntity<List<Korisnik>> findAll() {

		List<Korisnik> korisnik = korisnikService.findAll();

		return new ResponseEntity<>(korisnik, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Korisnik> findOne(@PathVariable Long id) {

		Korisnik korisnik = korisnikService.findOne(id);

		if (korisnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registrujKorisnika(@RequestBody RegistracijaDTO dto) {

		Korisnik korisnik = korisnikService.registracija(dto);
		if (korisnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(korisnik, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Korisnik> update(@RequestBody Korisnik korisnik) {

		Korisnik noviKorisnik = korisnikService.update(korisnik);
		if (noviKorisnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(noviKorisnik, HttpStatus.OK);
	}

	@PutMapping("/password")
	public ResponseEntity<Boolean> update(@RequestBody PasswordUpdate passwordUpdate) {

		boolean provera = korisnikService.updatePassword(passwordUpdate);
		if (provera != true) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(provera, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO dto) {

		JwtResponse response = korisnikService.login(dto);
		if (response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/aktiviraj/{registracioniLink}")
	public ResponseEntity<Boolean> aktivirajKorisnika(@PathVariable String registracioniLink) {

		boolean provera = korisnikService.aktivirajKorisnika(registracioniLink);

		if (provera != true) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(provera, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {

		boolean provera = korisnikService.delete(id);

		if (provera != true) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(provera, HttpStatus.OK);
	}
}
