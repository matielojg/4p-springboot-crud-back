package com.example.urna_virtual_uniamerica.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.entity.enums.FuncaoCandidato;
import com.example.urna_virtual_uniamerica.app.entity.enums.StatusCandidato;

@DataJpaTest
@ActiveProfiles("test")  // Use um profile de test para não interferir no banco real
public class CandidatoRepositoryTest {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Test
    public void testFindByStatus() {
        // Cria e salva alguns candidatos com diferentes status
        Candidato candidatoAtivo = new Candidato();
        candidatoAtivo.setNomeCompleto("João");
        candidatoAtivo.setStatus(StatusCandidato.ATIVO);
        candidatoRepository.save(candidatoAtivo);

        Candidato candidatoInativo = new Candidato();
        candidatoInativo.setNomeCompleto("Maria");
        candidatoInativo.setStatus(StatusCandidato.INATIVO);
        candidatoRepository.save(candidatoInativo);

        // Busca candidatos com status ATIVO
        List<Candidato> candidatosAtivos = candidatoRepository.findByStatus(StatusCandidato.ATIVO);

        // Verificações
        assertThat(candidatosAtivos).hasSize(1);
        assertThat(candidatosAtivos.get(0).getNomeCompleto()).isEqualTo("João");
    }

    @Test
    public void testExistsByCpf() {
        // Cria e salva um candidato com um CPF específico
        Candidato candidato = new Candidato();
        candidato.setNomeCompleto("João");
        candidato.setCpf("12345678900");
        candidatoRepository.save(candidato);

        // Verifica se o CPF existe
        boolean exists = candidatoRepository.existsByCpf("12345678900");

        // Verificação
        assertThat(exists).isTrue();
    }

    @Test
    public void testExistsByNumero() {
        // Cria e salva um candidato com um número específico
        Candidato candidato = new Candidato();
        candidato.setNomeCompleto("João");
        candidato.setNumero(100);
        candidatoRepository.save(candidato);

        // Verifica se o número existe
        boolean exists = candidatoRepository.existsByNumero(100);

        // Verificação
        assertThat(exists).isTrue();
    }

    @Test
    public void testFindByFuncao() {
        // Cria e salva candidatos com diferentes funções
        Candidato candidato1 = new Candidato();
        candidato1.setNomeCompleto("João");
        candidato1.setFuncao(FuncaoCandidato.PREFEITO);
        candidatoRepository.save(candidato1);

        Candidato candidato2 = new Candidato();
        candidato2.setNomeCompleto("Maria");
        candidato2.setFuncao(FuncaoCandidato.VEREADOR);
        candidatoRepository.save(candidato2);

        // Busca candidatos pela função
        List<Candidato> presidentes = candidatoRepository.findByFuncao(FuncaoCandidato.PREFEITO);

        // Verificações
        assertThat(presidentes).hasSize(1);
        assertThat(presidentes.get(0).getNomeCompleto()).isEqualTo("João");
    }
}