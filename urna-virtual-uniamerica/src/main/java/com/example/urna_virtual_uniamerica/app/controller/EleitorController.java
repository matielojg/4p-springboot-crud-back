package com.example.urna_virtual_uniamerica.app.controller;

import java.util.List;

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

import com.example.urna_virtual_uniamerica.app.entity.Eleitor;
import com.example.urna_virtual_uniamerica.app.service.EleitorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/eleitores")
public class EleitorController {

    @Autowired
    private EleitorService eleitorService;

    @Operation(summary = "Cadastrar um novo eleitor", description = "Este endpoint permite cadastrar um novo eleitor no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Eleitor cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro do eleitor")
    })
    @PostMapping
    public ResponseEntity<Eleitor> cadastrarEleitor(@RequestBody Eleitor eleitor) {
        Eleitor novoEleitor = eleitorService.cadastrarEleitor(eleitor);
        return new ResponseEntity<>(novoEleitor, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os eleitores aptos e pendentes", description = "Este endpoint retorna uma lista com todos os eleitores aptos e pendentes no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de eleitores retornada com sucesso")
    })
    @GetMapping("/aptosOuPendentes")
    public ResponseEntity<List<Eleitor>> listarEleitores() {
        List<Eleitor> eleitores = eleitorService.listarEleitoresAptosOuPendentes();
        return new ResponseEntity<>(eleitores, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar dados de um eleitor", description = "Este endpoint permite atualizar os dados de um eleitor existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Eleitor atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Eleitor não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Eleitor> atualizarEleitor(@PathVariable Long id, @RequestBody Eleitor eleitor) {
        Eleitor eleitorAtualizado = eleitorService.atualizarEleitor(id, eleitor);
        return new ResponseEntity<>(eleitorAtualizado, HttpStatus.OK);
    }

    @Operation(summary = "Inativar um eleitor", description = "Este endpoint permite inativar um eleitor. Se o eleitor já votou, a inativação não será permitida.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Eleitor inativado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao inativar eleitor, pois já votou")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarEleitor(@PathVariable Long id) {
        eleitorService.inativarEleitor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}