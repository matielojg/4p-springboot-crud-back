package com.example.urna_virtual_uniamerica.app.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.entity.Voto;
import com.example.urna_virtual_uniamerica.app.entity.dto.ResultadoVotacaoDTO;
import com.example.urna_virtual_uniamerica.app.repository.CandidatoRepository;
import com.example.urna_virtual_uniamerica.app.repository.VotoRepository;

public class ApuracaoServiceTest {

	@Mock
	private VotoRepository votoRepository;

	@Mock
	private CandidatoRepository candidatoRepository;

	@InjectMocks
	private ApuracaoService apuracaoService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testApurarVotosVereador() {
		Candidato candidato1 = new Candidato();
		candidato1.setNomeCompleto("Carlos Santos");
		candidato1.setNumero(1);

		Candidato candidato2 = new Candidato();
		candidato2.setNomeCompleto("Beatriz Lima");
		candidato2.setNumero(2);

		Voto voto1 = new Voto();
		voto1.setCandidatoVereador(candidato1);

		Voto voto2 = new Voto();
		voto2.setCandidatoVereador(candidato2);

		Voto voto3 = new Voto();
		voto3.setCandidatoVereador(candidato1);

		when(votoRepository.findAll()).thenReturn(Arrays.asList(voto1, voto2, voto3));

		List<ResultadoVotacaoDTO> resultado = apuracaoService.apurarVotosVereador();

		assertEquals(2, resultado.size());

		ResultadoVotacaoDTO resultadoCandidato1 = resultado.stream()
				.filter(r -> r.getNomeCandidato().equals("Carlos Santos")).findFirst().orElse(null);

		ResultadoVotacaoDTO resultadoCandidato2 = resultado.stream()
				.filter(r -> r.getNomeCandidato().equals("Beatriz Lima")).findFirst().orElse(null);

		assertEquals(2, resultadoCandidato1.getNumeroVotos());
		assertEquals(1, resultadoCandidato2.getNumeroVotos());

		verify(candidatoRepository, times(1)).save(candidato1);
		verify(candidatoRepository, times(1)).save(candidato2);
	}

	@Test
	public void testApurarVotosPrefeito() {
		Candidato candidato1 = new Candidato();
		candidato1.setNomeCompleto("Carlos Santos");
		candidato1.setNumero(1);

		Candidato candidato2 = new Candidato();
		candidato2.setNomeCompleto("Beatriz Lima");
		candidato2.setNumero(2);

		Voto voto1 = new Voto();
		voto1.setCandidatoPrefeito(candidato1);

		Voto voto2 = new Voto();
		voto2.setCandidatoPrefeito(candidato2);

		Voto voto3 = new Voto();
		voto3.setCandidatoPrefeito(candidato1);

		when(votoRepository.findAll()).thenReturn(Arrays.asList(voto1, voto2, voto3));

		List<ResultadoVotacaoDTO> resultado = apuracaoService.apurarVotosPrefeito();

		assertEquals(2, resultado.size());

		ResultadoVotacaoDTO resultadoCandidato1 = resultado.stream()
				.filter(r -> r.getNomeCandidato().equals("Carlos Santos")).findFirst().orElse(null);

		ResultadoVotacaoDTO resultadoCandidato2 = resultado.stream()
				.filter(r -> r.getNomeCandidato().equals("Beatriz Lima")).findFirst().orElse(null);

		assertEquals(2, resultadoCandidato1.getNumeroVotos());
		assertEquals(1, resultadoCandidato2.getNumeroVotos());

		verify(candidatoRepository, times(1)).save(candidato1);
		verify(candidatoRepository, times(1)).save(candidato2);
	}
}