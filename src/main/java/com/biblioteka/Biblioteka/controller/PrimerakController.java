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

import com.biblioteka.Biblioteka.dto.PrimerakDTO;
import com.biblioteka.Biblioteka.model.Primerak;
import com.biblioteka.Biblioteka.service.PrimerakService;

@CrossOrigin
@RestController
@RequestMapping("api/primerak")
public class PrimerakController {

	@Autowired
	PrimerakService primerakService;

	@GetMapping("")
	public ResponseEntity<List<Primerak>> findAll() {

		List<Primerak> primerci = primerakService.findAll();

		return new ResponseEntity<List<Primerak>>(primerci, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Primerak> findOne(@PathVariable Long id) {

		Primerak primerak = primerakService.findOne(id);

		if (primerak == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(primerak, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Primerak> create(@RequestBody PrimerakDTO dto) {

		Primerak primerak = primerakService.save(dto);

		if (primerak == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(primerak, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> update(@PathVariable Long id) {

		boolean provera = primerakService.delete(id);

		if (provera != true)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(provera, HttpStatus.OK);
	}
}
