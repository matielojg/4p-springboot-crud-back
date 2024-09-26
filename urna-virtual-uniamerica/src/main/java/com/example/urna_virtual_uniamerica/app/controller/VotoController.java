package com.example.urna_virtual_uniamerica.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urna_virtual_uniamerica.app.entity.Voto;
import com.example.urna_virtual_uniamerica.app.entity.request.VotoRequest;
import com.example.urna_virtual_uniamerica.app.service.VotoService;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

	@Autowired
	private VotoService votoService;

	@PostMapping
	public ResponseEntity<Voto> registrarVoto(@RequestBody VotoRequest voto) {
		try {
			Voto novoVoto = votoService.registrarVoto(voto.getEleitorId(), voto.getCandidatoPrefeitoId(),
					voto.getCandidatoVereadorId());
			return new ResponseEntity<>(novoVoto, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	 // Endpoint para gerar votação automática
    @PostMapping("/gerar-votacao")
    public ResponseEntity<Void> gerarVotacaoAutomatica() {
        votoService.gerarVotacaoAutomatica();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}