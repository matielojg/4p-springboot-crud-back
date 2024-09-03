package com.uniamerica.livraria.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniamerica.livraria.app.entity.Funcionario;
import com.uniamerica.livraria.app.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario salvarFuncionario(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}

	public Optional<Funcionario> buscarPorId(Long id) {
		return funcionarioRepository.findById(id);
	}

	public void deletarFuncionario(Long id) {
		funcionarioRepository.deleteById(id);
	}
}