package com.example.urna_virtual_uniamerica.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.urna_virtual_uniamerica.app.entity.Candidato;
import com.example.urna_virtual_uniamerica.app.entity.enums.FuncaoCandidato;
import com.example.urna_virtual_uniamerica.app.entity.enums.StatusCandidato;

public  interface CandidatoRepository extends JpaRepository<Candidato, Long> {

	List<Candidato> findByStatus(StatusCandidato status);

	boolean existsByCpf(String cpf);

	boolean existsByNumero(Integer numero);
	
	List<Candidato> findByFuncao(FuncaoCandidato funcao);

	
}
