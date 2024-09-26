package com.example.urna_virtual_uniamerica.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.entity.Eleitor;
import com.example.urna_virtual_uniamerica.app.entity.Voto;
import com.example.urna_virtual_uniamerica.app.entity.enums.FuncaoCandidato;
import com.example.urna_virtual_uniamerica.app.repository.CandidatoRepository;
import com.example.urna_virtual_uniamerica.app.repository.EleitorRepository;
import com.example.urna_virtual_uniamerica.app.repository.VotoRepository;

@Service
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private EleitorRepository eleitorRepository;

	@Autowired
	private CandidatoRepository candidatoRepository;

	private Random random = new Random();

	public Voto registrarVoto(Long eleitorId, Long candidatoPrefeitoId, Long candidatoVereadorId) {
		// Verifica se o eleitor existe
		Optional<Eleitor> eleitor = eleitorRepository.findById(eleitorId);
		if (eleitor.isEmpty()) {
			throw new RuntimeException("Eleitor não encontrado");
		}

		// Verifica se o candidato a prefeito existe
		Optional<Candidato> candidatoPrefeito = candidatoRepository.findById(candidatoPrefeitoId);
		if (candidatoPrefeito.isEmpty()) {
			throw new RuntimeException("Candidato a prefeito não encontrado");
		}

		// Verifica se o candidato a vereador existe
		Optional<Candidato> candidatoVereador = candidatoRepository.findById(candidatoVereadorId);
		if (candidatoVereador.isEmpty()) {
			throw new RuntimeException("Candidato a vereador não encontrado");
		}
		return null;
	}

//        Voto voto = new Voto(eleitor.get(), candidatoPrefeito.get(), candidatoVereador.get());
//        return votoRepository.save(voto);

	// Método para gerar votos automáticos
	public void gerarVotacaoAutomatica() {
		// Lista todos os eleitores
		List<Eleitor> eleitores = eleitorRepository.findAll();

		// Lista todos os candidatos a prefeito (ID 17 e 19)
		List<Candidato> candidatosPrefeitos = candidatoRepository.findByFuncao(FuncaoCandidato.PREFEITO);

		// Lista todos os candidatos a vereador (ID 1 a 16)
		List<Candidato> candidatosVereadores = candidatoRepository.findByFuncao(FuncaoCandidato.VEREADOR);

		// Loop para cada eleitor
		for (Eleitor eleitor : eleitores) {
			// Seleciona aleatoriamente um candidato a prefeito
			Candidato candidatoPrefeito = candidatosPrefeitos.get(random.nextInt(candidatosPrefeitos.size()));

			// Seleciona aleatoriamente um candidato a vereador
			Candidato candidatoVereador = candidatosVereadores.get(random.nextInt(candidatosVereadores.size()));

			// Gera um voto para esse eleitor
			Voto voto = Voto.builder().eleitor(eleitor).candidatoPrefeito(candidatoPrefeito)
					.candidatoVereador(candidatoVereador).dataHora(LocalDateTime.now().toString())
					.hashComprovante(UUID.randomUUID().toString()).build();

			// Salva o voto
			votoRepository.save(voto);
		}

	}
}
