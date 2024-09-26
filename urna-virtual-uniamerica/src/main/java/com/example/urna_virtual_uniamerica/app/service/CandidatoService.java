package com.example.urna_virtual_uniamerica.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.entity.enums.StatusCandidato;
import com.example.urna_virtual_uniamerica.app.repository.CandidatoRepository;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    // Cadastrar um novo candidato
    public Candidato cadastrarCandidato(Candidato candidato) {
        validarDadosCandidato(candidato);
        candidato.setStatus(StatusCandidato.ATIVO); // Define como ATIVO
        return candidatoRepository.save(candidato);
    }

    // Listar apenas candidatos ativos
    public List<Candidato> listarCandidatosAtivos() {
        return candidatoRepository.findByStatus(StatusCandidato.ATIVO);
    }

    // Buscar um candidato por ID
    public Optional<Candidato> buscarCandidatoPorId(Long id) {
        return candidatoRepository.findById(id);
    }

    // Atualizar dados do candidato
    public Candidato atualizarCandidato(Long id, Candidato dadosAtualizados) {
        Optional<Candidato> candidatoOptional = candidatoRepository.findById(id);
        if (candidatoOptional.isEmpty()) {
            throw new RuntimeException("Candidato não encontrado.");
        }

        Candidato candidato = candidatoOptional.get();
        // Atualiza os campos permitidos
        candidato.setNomeCompleto(dadosAtualizados.getNomeCompleto());
        candidato.setCpf(dadosAtualizados.getCpf());
        candidato.setStatus(dadosAtualizados.getStatus());

        return candidatoRepository.save(candidato);
    }

    // Inativar candidato
    public void inativarCandidato(Long id) {
        Optional<Candidato> candidatoOptional = candidatoRepository.findById(id);
        if (candidatoOptional.isEmpty()) {
            throw new RuntimeException("Candidato não encontrado.");
        }

        Candidato candidato = candidatoOptional.get();
        candidato.setStatus(StatusCandidato.INATIVO);
        candidatoRepository.save(candidato);
    }

    // Verificar se o CPF já está cadastrado
    public boolean cpfJaCadastrado(String cpf) {
        return candidatoRepository.existsByCpf(cpf);
    }

    // Verificar se o número do candidato já está cadastrado
    public boolean numeroCandidatoJaCadastrado(Integer numero) {
        return candidatoRepository.existsByNumero(numero);
    }

    // Validação de dados do candidato (exemplo simples)
    void validarDadosCandidato(Candidato candidato) {
        if (cpfJaCadastrado(candidato.getCpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }
        if (numeroCandidatoJaCadastrado(candidato.getNumero())) {
            throw new RuntimeException("Número de candidato já cadastrado.");
        }
    }
}
