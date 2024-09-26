package com.example.urna_virtual_uniamerica.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urna_virtual_uniamerica.app.entity.dto.ResultadoVotacaoDTO;
import com.example.urna_virtual_uniamerica.app.service.ApuracaoService;

@RestController
@RequestMapping("/api/apuracao")
public class ApuracaoController {

    @Autowired
    private ApuracaoService apuracaoService;

    // Endpoint para apurar os votos e retornar o resultado
    @PostMapping("/apurarPrefeito")
    public ResponseEntity<List<ResultadoVotacaoDTO>> apurarVotosPrefeito() {
        List<ResultadoVotacaoDTO> resultado = apuracaoService.apurarVotosPrefeito();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    
    @PostMapping("/apurarVereador")
    public ResponseEntity<List<ResultadoVotacaoDTO>> apurarVotosVereador() {
        List<ResultadoVotacaoDTO> resultado = apuracaoService.apurarVotosVereador();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}