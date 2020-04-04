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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteka.Biblioteka.dto.ZaduzenjeDTO;
import com.biblioteka.Biblioteka.model.Zaduzenje;
import com.biblioteka.Biblioteka.service.ZaduzenjeService;

@CrossOrigin
@RestController
@RequestMapping("api/zaduzenje")
public class ZaduzenjeController {

	@Autowired
	ZaduzenjeService zaduzenjeService;

	@GetMapping("")
	public ResponseEntity<List<Zaduzenje>> findAll() {

		List<Zaduzenje> zaduzenja = zaduzenjeService.findAll();

		return new ResponseEntity<List<Zaduzenje>>(zaduzenja, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Zaduzenje> findOne(@PathVariable Long id) {

		Zaduzenje zaduzenje = zaduzenjeService.findOne(id);

		if (zaduzenje == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(zaduzenje, HttpStatus.OK);
	}

	@GetMapping("/korisnik/{id}")
	public ResponseEntity<List<Zaduzenje>> findByKorisnikId(@PathVariable Long id) {

		List<Zaduzenje> zaduzenja = zaduzenjeService.findByKorisnikId(id);

		return new ResponseEntity<List<Zaduzenje>>(zaduzenja, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Zaduzenje> create(@RequestBody ZaduzenjeDTO dto) {

		Zaduzenje zaduzenje = zaduzenjeService.save(dto);

		if (zaduzenje == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(zaduzenje, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> update(@PathVariable Long id) {

		boolean provera = zaduzenjeService.delete(id);

		if (provera != true)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(provera, HttpStatus.OK);
	}

}
