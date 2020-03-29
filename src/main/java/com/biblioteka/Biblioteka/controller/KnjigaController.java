package com.biblioteka.Biblioteka.controller;

import com.biblioteka.Biblioteka.model.Knjiga;
import com.biblioteka.Biblioteka.service.KnjigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

    @RequestMapping("/controller/knjige")
    @RestController
    class KnjigaController {
        @Autowired
        private KnjigaService knjigaService;
//        ResponseEntity updateKnjiga(@Valid @RequestBody Knjiga knjiga) {
//            knjigaService.updateKnjiga(knjiga);
//            return new ResponseEntity(HttpStatus.OK);
//        }

    }

