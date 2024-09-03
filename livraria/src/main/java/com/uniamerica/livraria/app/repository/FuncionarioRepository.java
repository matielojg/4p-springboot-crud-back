package com.uniamerica.livraria.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.uniamerica.livraria.app.entity.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
