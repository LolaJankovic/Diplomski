package com.biblioteka.Biblioteka.controller;

import java.util.List;
import java.util.Set;

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

import com.biblioteka.Biblioteka.dto.KnjigaDTO;
import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.service.KnjigaService;

@CrossOrigin
@RestController
@RequestMapping("api/knjiga")
public class KnjigaController {

	@Autowired
	KnjigaService knjigaService;

	@GetMapping("")
	public ResponseEntity<List<Knjiga>> findAll() {

		List<Knjiga> knjige = knjigaService.findAll();

		return new ResponseEntity<>(knjige, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Knjiga> findOne(@PathVariable Long id) {

		Knjiga knjiga = knjigaService.findOne(id);

		if (knjiga == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Knjiga>(knjiga, HttpStatus.OK);
	}

	@GetMapping("/naziv/{naziv}")
	public ResponseEntity<Knjiga> findByNaziv(@PathVariable String naziv) {

		Knjiga knjiga = knjigaService.findByNaziv(naziv);

		if (knjiga == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Knjiga>(knjiga, HttpStatus.OK);
	}

	@GetMapping("/godina/{godinaIzdanja}")
	public ResponseEntity<List<Knjiga>> findByGodinaIzdavanja(@PathVariable String godinaIzdanja) {

		List<Knjiga> knjige = knjigaService.findByGodinjaIzdavanja(godinaIzdanja);

		return new ResponseEntity<>(knjige, HttpStatus.OK);
	}

	@GetMapping("/pisac/{id}")
	public ResponseEntity<?> findByPisacId(@PathVariable Long id) {

		Set<Knjiga> knjige = knjigaService.findByPisacId(id);

		return new ResponseEntity<>(knjige, HttpStatus.OK);
	}

	@GetMapping("/zanr/{zanr}")
	public ResponseEntity<?> findByZanr(@PathVariable String zanr) {

		List<Knjiga> knjige = knjigaService.findByZanr(zanr);

		return new ResponseEntity<>(knjige, HttpStatus.OK);
	}

	@GetMapping("/izdavac/{izdavac}")
	public ResponseEntity<?> findByIzdavac(@PathVariable String izdavac) {

		List<Knjiga> knjige = knjigaService.findByIzdavac(izdavac);

		return new ResponseEntity<>(knjige, HttpStatus.OK);
	}

	@GetMapping("/admin/{id}")
	public ResponseEntity<?> findByAdminId(@PathVariable Long id) {

		List<Knjiga> knjige = knjigaService.findByAdministratorId(id);

		return new ResponseEntity<>(knjige, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody KnjigaDTO dto) {

		Knjiga knjiga = knjigaService.save(dto);
		if (knjiga == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(knjiga, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		boolean provera = knjigaService.delete(id);
		if (provera != true) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(provera, HttpStatus.OK);
	}

}
