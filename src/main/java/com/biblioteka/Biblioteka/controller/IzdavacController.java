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

import com.biblioteka.Biblioteka.dto.IzdavacDTO;
import com.biblioteka.Biblioteka.model.Izdavac;
import com.biblioteka.Biblioteka.service.IzdavacService;

@CrossOrigin
@RestController
@RequestMapping(value = "api/izdavac")
public class IzdavacController {

	@Autowired
	IzdavacService izdavacService;

	@GetMapping("")
	public ResponseEntity<List<Izdavac>> findAll() {

		List<Izdavac> izdavaci = izdavacService.findAll();

		return new ResponseEntity<List<Izdavac>>(izdavaci, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Izdavac> findOne(@PathVariable Long id) {

		Izdavac izdavac = izdavacService.findOne(id);

		if (izdavac == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Izdavac>(izdavac, HttpStatus.OK);
	}

	@GetMapping("/name/{naziv}")
	public ResponseEntity<Izdavac> findByNaziv(@PathVariable String naziv) {

		Izdavac izdavac = izdavacService.findByNaziv(naziv);

		if (izdavac == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Izdavac>(izdavac, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Izdavac> create(@RequestBody IzdavacDTO izdavacDto) {

		Izdavac izdavac = izdavacService.save(izdavacDto);

		if (izdavac == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Izdavac>(izdavac, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Izdavac> update(@RequestBody Izdavac izdavac) {

		Izdavac noviIzdavac = izdavacService.update(izdavac);

		if (noviIzdavac == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Izdavac>(izdavac, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		boolean provera = izdavacService.delete(id);

		if (provera == false) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
