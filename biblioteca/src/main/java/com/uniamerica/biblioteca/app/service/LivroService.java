package com.uniamerica.biblioteca.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniamerica.biblioteca.app.entity.Livro;
import com.uniamerica.biblioteca.app.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository carroRepository;

	public String save(Livro carro) {

		this.carroRepository.save(carro);
		return carro.getNome() + " salvo com sucesso";
	}

	public boolean verificarNomeCarro(String nome, int ano) {
		if (nome.equals("Jeep Compass") && ano < 2006)
			throw new RuntimeException();

		return true;
	}

	public String update(long id, Livro carro) {

		carro.setId(id);
		this.carroRepository.save(carro);
		return carro.getNome() + " atualizado com sucesso";
	}

	public List<Livro> listAll() {
		return this.carroRepository.findAll();
	}

	public Livro findById(long idCarro) {

		Livro carro = this.carroRepository.findById(idCarro).get();
		return carro;

	}

	public String delete(long idCarro) {
		this.carroRepository.deleteById(idCarro);
		return "Carro deletado com sucesso!";

	}
}