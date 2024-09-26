package com.example.urna_virtual_uniamerica.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.entity.Voto;
import com.example.urna_virtual_uniamerica.app.entity.dto.ResultadoVotacaoDTO;
import com.example.urna_virtual_uniamerica.app.repository.CandidatoRepository;
import com.example.urna_virtual_uniamerica.app.repository.VotoRepository;

@Service
public class ApuracaoService {

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private CandidatoRepository candidatoRepository;

	public List<ResultadoVotacaoDTO> apurarVotosPrefeito() {
	    // Lista para armazenar o resultado final da apuração
	    List<ResultadoVotacaoDTO> resultadoVotacao = new ArrayList<>();

	    // Recupera todos os votos
	    List<Voto> votos = votoRepository.findAll();

	    // Filtra votos que não têm um candidato prefeito associado (evita NullPointerException)
	    Map<Candidato, Long> votosPrefeitos = votos.stream()
	            .filter(voto -> voto.getCandidatoPrefeito() != null) // Filtra votos sem candidato prefeito
	            .collect(Collectors.groupingBy(Voto::getCandidatoPrefeito, Collectors.counting()));

	    // Atualiza os votos para os candidatos a prefeito e adiciona ao resultado
	    votosPrefeitos.forEach((candidato, contagem) -> {
	        candidato.setVotosApurados(contagem.intValue());
	        candidatoRepository.save(candidato); // Atualiza no banco de dados
	        
	        // Adiciona o resultado para este candidato no DTO
	        resultadoVotacao.add(new ResultadoVotacaoDTO(
	            candidato.getNomeCompleto(),
	            "Prefeito",
	            candidato.getNumero(),
	            contagem.intValue()
	        ));
	    });

	    // Retorna a lista com o resultado final da votação
	    return resultadoVotacao;
	}
	
	public List<ResultadoVotacaoDTO> apurarVotosVereador() {
        // Lista para armazenar o resultado final da apuração
        List<ResultadoVotacaoDTO> resultadoVotacao = new ArrayList<>();

        // Recupera todos os votos
        List<Voto> votos = votoRepository.findAll();

        Map<Candidato, Long> votosVereadores = votos.stream()
                .collect(Collectors.groupingBy(Voto::getCandidatoVereador, Collectors.counting()));

        // Atualiza os votos para os candidatos a vereador e adiciona ao resultado
        votosVereadores.forEach((candidato, contagem) -> {
            candidato.setVotosApurados(contagem.intValue());
            candidatoRepository.save(candidato); // Atualiza no banco de dados
            
            // Adiciona o resultado para este candidato no DTO
            resultadoVotacao.add(new ResultadoVotacaoDTO(
                candidato.getNomeCompleto(),
                "Vereador",
                candidato.getNumero(),
                contagem.intValue()
            ));
        });

        // Retorna a lista com o resultado final da votação
        return resultadoVotacao;
    }

}