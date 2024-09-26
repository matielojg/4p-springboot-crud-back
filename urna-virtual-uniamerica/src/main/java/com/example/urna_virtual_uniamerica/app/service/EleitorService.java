package com.example.urna_virtual_uniamerica.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.urna_virtual_uniamerica.app.entity.Eleitor;
import com.example.urna_virtual_uniamerica.app.entity.enums.EleitorStatus;
import com.example.urna_virtual_uniamerica.app.repository.EleitorRepository;

@Service
public class EleitorService {

    @Autowired
    private EleitorRepository eleitorRepository;

    // Criar um novo eleitor
    @Transactional
    public Eleitor cadastrarEleitor(Eleitor eleitor) {
        validarDadosEleitor(eleitor);
        processarStatusEleitor(eleitor);
        return eleitorRepository.save(eleitor);
    }

    // Listar todos os eleitores aptos e pendentes
    public List<Eleitor> listarEleitoresAptosOuPendentes() {
        return eleitorRepository.findByEleitorStatusIn(Arrays.asList(EleitorStatus.APTO, EleitorStatus.PENDENTE));
    }

    // Atualizar um eleitor
    @Transactional
    public Eleitor atualizarEleitor(Long id, Eleitor dadosAtualizados) {
        Optional<Eleitor> eleitorOptional = eleitorRepository.findById(id);
        if (eleitorOptional.isEmpty()) {
            throw new RuntimeException("Eleitor não encontrado.");
        }
        
        Eleitor eleitor = eleitorOptional.get();

        if (eleitor.getEleitorStatus() != EleitorStatus.INATIVO) {
            eleitor.setNomeCompleto(dadosAtualizados.getNomeCompleto());
            eleitor.setProfissaoEleitor(dadosAtualizados.getProfissaoEleitor());
            eleitor.setCpf(dadosAtualizados.getCpf());
            eleitor.setTelefoneCelular(dadosAtualizados.getTelefoneCelular());
            eleitor.setTelefoneFixo(dadosAtualizados.getTelefoneFixo());
            eleitor.setEmail(dadosAtualizados.getEmail());
            
            processarStatusEleitor(eleitor); // Revalidar o status após a atualização
        }

        return eleitorRepository.save(eleitor);
    }

    // Inativar um eleitor
    @Transactional
    public void inativarEleitor(Long id) {
        Optional<Eleitor> eleitorOptional = eleitorRepository.findById(id);
        if (eleitorOptional.isEmpty()) {
            throw new RuntimeException("Eleitor não encontrado.");
        }

        Eleitor eleitor = eleitorOptional.get();

        if (eleitor.getEleitorStatus() == EleitorStatus.VOTOU) {
            throw new RuntimeException("Usuário já votou. Não foi possível inativá-lo.");
        }

        eleitor.setEleitorStatus(EleitorStatus.INATIVO);
        eleitorRepository.save(eleitor);
    }

    // Método auxiliar para validar os dados do eleitor
    private void validarDadosEleitor(Eleitor eleitor) {
        if (eleitor.getNomeCompleto() == null || eleitor.getNomeCompleto().isEmpty()) {
            throw new RuntimeException("Nome completo é obrigatório.");
        }
        if (eleitor.getProfissaoEleitor() == null || eleitor.getProfissaoEleitor().isEmpty()) {
            throw new RuntimeException("Profissão é obrigatória.");
        }
        if (eleitor.getTelefoneCelular() == null || eleitor.getTelefoneCelular().isEmpty()) {
            throw new RuntimeException("Telefone celular é obrigatório.");
        }
    }

    // Processa o status do eleitor conforme as regras
    private void processarStatusEleitor(Eleitor eleitor) {
        if (eleitor.getCpf() == null || eleitor.getEmail() == null) {
            eleitor.setEleitorStatus(EleitorStatus.PENDENTE);
        } else {
            eleitor.setEleitorStatus(EleitorStatus.APTO);
        }
    }
}