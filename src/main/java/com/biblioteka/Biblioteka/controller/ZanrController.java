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

import com.biblioteka.Biblioteka.dto.ZanrDTO;
import com.biblioteka.Biblioteka.model.Zanr;
import com.biblioteka.Biblioteka.service.ZanrService;

@CrossOrigin
@RestController
@RequestMapping("api/zanr")
public class ZanrController {

	@Autowired
	ZanrService zanrService;

	@GetMapping("")
	public ResponseEntity<List<Zanr>> findAll() {

		List<Zanr> zanrovi = zanrService.findAll();

		return new ResponseEntity<List<Zanr>>(zanrovi, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Zanr> findOne(@PathVariable Long id) {

		Zanr zanr = zanrService.findOne(id);
		if (zanr == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Zanr>(zanr, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Zanr> create(@RequestBody ZanrDTO zanrDto) {

		Zanr zanr = zanrService.save(zanrDto);
		if (zanr == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Zanr>(zanr, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Zanr> update(@RequestBody Zanr zanr) {

		Zanr noviZanr = zanrService.update(zanr);
		if (noviZanr == null) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Zanr>(noviZanr, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> update(@PathVariable Long id) {

		boolean provera = zanrService.delete(id);

		if (provera != true)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(provera, HttpStatus.OK);
	}

}
