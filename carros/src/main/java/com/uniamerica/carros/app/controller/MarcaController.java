package com.uniamerica.carros.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.service.MarcaService;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/marca")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Marca marca) {
		try {
			String mensagem = this.marcaService.save(marca);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
