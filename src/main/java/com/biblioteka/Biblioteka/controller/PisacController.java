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

import com.biblioteka.Biblioteka.dto.PisacDTO;
import com.biblioteka.Biblioteka.model.Pisac;
import com.biblioteka.Biblioteka.service.PisacService;

@CrossOrigin
@RestController
@RequestMapping("api/pisac")
public class PisacController {

	@Autowired
	PisacService pisacService;

	@GetMapping("")
	public ResponseEntity<List<Pisac>> findAll() {

		List<Pisac> pisci = pisacService.findAll();

		return new ResponseEntity<List<Pisac>>(pisci, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pisac> findOne(@PathVariable Long id) {

		Pisac pisac = pisacService.findOne(id);

		if (pisac == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(pisac, HttpStatus.OK);
	}

	@GetMapping("/zanr/{zanr}")
	public ResponseEntity<List<Pisac>> findByZanr(@PathVariable String zanr) {

		List<Pisac> pisci = pisacService.findByZanr(zanr);

		return new ResponseEntity<List<Pisac>>(pisci, HttpStatus.OK);
	}

	@GetMapping("/drzava/{drzava}")
	public ResponseEntity<List<Pisac>> findByDrzava(@PathVariable String drzava) {

		List<Pisac> pisci = pisacService.findByDrzava(drzava);

		return new ResponseEntity<List<Pisac>>(pisci, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Pisac> create(@RequestBody PisacDTO dto) {

		Pisac pisci = pisacService.save(dto);

		if (pisci == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(pisci, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> update(@PathVariable Long id) {

		boolean provera = pisacService.delete(id);

		if (provera != true)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(provera, HttpStatus.OK);
	}
}
