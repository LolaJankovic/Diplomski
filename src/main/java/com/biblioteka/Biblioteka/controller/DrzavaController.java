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

import com.biblioteka.Biblioteka.dto.DrzavaDTO;
import com.biblioteka.Biblioteka.model.Drzava;
import com.biblioteka.Biblioteka.service.DrzavaService;

@CrossOrigin
@RestController
@RequestMapping(value = "api/drzava")
public class DrzavaController {

	@Autowired
	DrzavaService drzavaService;

	@GetMapping("")
	public ResponseEntity<List<Drzava>> findAll() {

		List<Drzava> drzave = drzavaService.findAll();

		return new ResponseEntity<>(drzave, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Drzava> findOne(@PathVariable Long id) {

		Drzava drzava = drzavaService.findOne(id);

		return new ResponseEntity<>(drzava, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Drzava> create(@RequestBody DrzavaDTO dto) {

		Drzava drzava = drzavaService.save(dto);
		if (drzava == null) {

			return new ResponseEntity<Drzava>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Drzava>(drzava, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Drzava> update(@RequestBody Drzava drzava) {

		Drzava novaDrzava = drzavaService.update(drzava);
		if (novaDrzava == null) {

			return new ResponseEntity<Drzava>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Drzava>(novaDrzava, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		boolean provera = drzavaService.delete(id);
		if (provera != true) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
