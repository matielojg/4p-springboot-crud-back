package com.example.urna_virtual_uniamerica.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.service.CandidatoService;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    // 1. Cadastrar um novo candidato
    @PostMapping
    public ResponseEntity<Candidato> cadastrarCandidato(@RequestBody Candidato candidato) {
        Candidato novoCandidato = candidatoService.cadastrarCandidato(candidato);
        return new ResponseEntity<>(novoCandidato, HttpStatus.CREATED);
    }

    // 3. Listar apenas candidatos ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<Candidato>> listarCandidatosAtivos() {
        List<Candidato> candidatosAtivos = candidatoService.listarCandidatosAtivos();
        return new ResponseEntity<>(candidatosAtivos, HttpStatus.OK);
    }

    // 4. Buscar um candidato por ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidato> buscarCandidatoPorId(@PathVariable Long id) {
        Optional<Candidato> candidato = candidatoService.buscarCandidatoPorId(id);
        if (candidato.isPresent()) {
            return new ResponseEntity<>(candidato.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. Atualizar dados de um candidato
    @PutMapping("/{id}")
    public ResponseEntity<Candidato> atualizarCandidato(@PathVariable Long id, @RequestBody Candidato candidatoAtualizado) {
        try {
            Candidato candidatoAtualizadoRetorno = candidatoService.atualizarCandidato(id, candidatoAtualizado);
            return new ResponseEntity<>(candidatoAtualizadoRetorno, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 6. Inativar candidato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarCandidato(@PathVariable Long id) {
        try {
            candidatoService.inativarCandidato(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}