package com.example.urna_virtual_uniamerica.app.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.entity.enums.StatusCandidato;
import com.example.urna_virtual_uniamerica.app.repository.CandidatoRepository;

@ExtendWith(MockitoExtension.class)
public class CandidatoServiceTest {

    @Mock
    private CandidatoRepository candidatoRepository;

    @InjectMocks
    private CandidatoService candidatoService;

    @Test
    public void testValidarDadosCandidato_CpfJaCadastrado() {
        // Configura os mocks
        Candidato candidato = new Candidato();
        candidato.setCpf("12345678900");
        candidato.setNumero(123);
        
        // Mocka que o CPF já está cadastrado
        when(candidatoRepository.existsByCpf(candidato.getCpf())).thenReturn(true);

        // Verifica se a exceção é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            candidatoService.validarDadosCandidato(candidato);
        });

        assertEquals("CPF já cadastrado.", exception.getMessage());
    }

    @Test
    public void testValidarDadosCandidato_NumeroJaCadastrado() {
        // Configura os mocks
        Candidato candidato = new Candidato();
        candidato.setCpf("12345678900");
        candidato.setNumero(123);

        // Mocka que o número do candidato já está cadastrado
        when(candidatoRepository.existsByCpf(candidato.getCpf())).thenReturn(false);
        when(candidatoRepository.existsByNumero(candidato.getNumero())).thenReturn(true);

        // Verifica se a exceção é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            candidatoService.validarDadosCandidato(candidato);
        });

        assertEquals("Número de candidato já cadastrado.", exception.getMessage());
    }

    @Test
    public void testValidarDadosCandidato_Sucesso() {
        // Configura os mocks
        Candidato candidato = new Candidato();
        candidato.setCpf("12345678900");
        candidato.setNumero(123);

        // Mocka que CPF e número não estão cadastrados
        when(candidatoRepository.existsByCpf(candidato.getCpf())).thenReturn(false);
        when(candidatoRepository.existsByNumero(candidato.getNumero())).thenReturn(false);

        // Não deve lançar exceção
        assertDoesNotThrow(() -> candidatoService.validarDadosCandidato(candidato));
    }
    
    @Test
    public void testCadastrarCandidato_Sucesso() {
        Candidato candidato = new Candidato();
        candidato.setCpf("12345678900");
        candidato.setNumero(123);
        
        // Mocka validação e salvamento
        when(candidatoRepository.existsByCpf(anyString())).thenReturn(false);
        when(candidatoRepository.existsByNumero(anyInt())).thenReturn(false);
        when(candidatoRepository.save(ArgumentMatchers.<Candidato>any())).thenReturn(candidato);

        // Executa o método de cadastro
        Candidato resultado = candidatoService.cadastrarCandidato(candidato);

        // Verifica se o status foi definido como ATIVO
        assertEquals(StatusCandidato.ATIVO, resultado.getStatus());
        verify(candidatoRepository, times(1)).save(candidato);
    }

    @Test
    public void testCadastrarCandidato_CpfJaCadastrado() {
        Candidato candidato = new Candidato();
        candidato.setCpf("12345678900");
        candidato.setNumero(123);

        // Mocka CPF já cadastrado
        when(candidatoRepository.existsByCpf(anyString())).thenReturn(true);

        // Verifica se a exceção é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            candidatoService.cadastrarCandidato(candidato);
        });

        assertEquals("CPF já cadastrado.", exception.getMessage());
    }
}