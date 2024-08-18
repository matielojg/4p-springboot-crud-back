package com.uniamerica.biblioteca.app.controller;

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

import com.uniamerica.biblioteca.app.entity.Livro;
import com.uniamerica.biblioteca.app.service.LivroService;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin("*")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Livro livro) {

		try {

			String mensagem = this.livroService.save(livro);
			return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Livro carro, @PathVariable int id) {

		try {

			String mensagem = this.livroService.update(id, carro);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Livro>> listAll() {

		try {

			List<Livro> lista = this.livroService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/findById/{idLivro}")
	public ResponseEntity<Livro> findById(@PathVariable long idLivro) {

		try {

			Livro livro = this.livroService.findById(idLivro);
			return new ResponseEntity<>(livro, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete/{idCarro}")
	public ResponseEntity<String> delete(@PathVariable long idCarro) {

		try {

			String mensagem = this.livroService.delete(idCarro);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("Deu esse erro aqui: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}