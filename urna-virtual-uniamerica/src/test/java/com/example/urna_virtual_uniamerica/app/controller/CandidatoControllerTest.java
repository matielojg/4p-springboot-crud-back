package com.example.urna_virtual_uniamerica.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.service.CandidatoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CandidatoController.class)
public class CandidatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidatoService candidatoService;

    @Autowired
    private ObjectMapper objectMapper;

    // 1. Testar o cadastro de um novo candidato
    @Test
    public void testCadastrarCandidato() throws Exception {
        Candidato candidato = new Candidato();
        candidato.setNomeCompleto("João");

        Mockito.when(candidatoService.cadastrarCandidato(any(Candidato.class))).thenReturn(candidato);

        mockMvc.perform(post("/api/candidatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidato)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeCompleto").value("João"));
    }

    // 3. Testar listagem de candidatos ativos
    @Test
    public void testListarCandidatosAtivos() throws Exception {
        Candidato candidato1 = new Candidato();
        candidato1.setNomeCompleto("João");
        Candidato candidato2 = new Candidato();
        candidato2.setNomeCompleto("Maria");

        List<Candidato> candidatosAtivos = Arrays.asList(candidato1, candidato2);
        Mockito.when(candidatoService.listarCandidatosAtivos()).thenReturn(candidatosAtivos);

        mockMvc.perform(get("/api/candidatos/ativos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nomeCompleto").value("João"))
                .andExpect(jsonPath("$[1].nomeCompleto").value("Maria"));
    }

    // 4. Testar buscar candidato por ID (caso encontrado)
    @Test
    public void testBuscarCandidatoPorId_Encontrado() throws Exception {
        Candidato candidato = new Candidato();
        candidato.setId(1L);
        candidato.setNomeCompleto("João");

        Mockito.when(candidatoService.buscarCandidatoPorId(anyLong())).thenReturn(Optional.of(candidato));

        mockMvc.perform(get("/api/candidatos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCompleto").value("João"));
    }

    // 4. Testar buscar candidato por ID (caso não encontrado)
    @Test
    public void testBuscarCandidatoPorId_NaoEncontrado() throws Exception {
        Mockito.when(candidatoService.buscarCandidatoPorId(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/candidatos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // 5. Testar atualização de candidato
    @Test
    public void testAtualizarCandidato() throws Exception {
        Candidato candidatoAtualizado = new Candidato();
        candidatoAtualizado.setId(1L);
        candidatoAtualizado.setNomeCompleto("João Atualizado");

        Mockito.when(candidatoService.atualizarCandidato(anyLong(), any(Candidato.class))).thenReturn(candidatoAtualizado);

        mockMvc.perform(put("/api/candidatos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidatoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCompleto").value("João Atualizado"));
    }

    // 6. Testar inativação de candidato (caso sucesso)
    @Test
    public void testInativarCandidato_Sucesso() throws Exception {
        Mockito.doNothing().when(candidatoService).inativarCandidato(anyLong());

        mockMvc.perform(delete("/api/candidatos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // 6. Testar inativação de candidato (caso não encontrado)
    @Test
    public void testInativarCandidato_NaoEncontrado() throws Exception {
        Mockito.doThrow(new RuntimeException("Candidato não encontrado")).when(candidatoService).inativarCandidato(anyLong());

        mockMvc.perform(delete("/api/candidatos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}