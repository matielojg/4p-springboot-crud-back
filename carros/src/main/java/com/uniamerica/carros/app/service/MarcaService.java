package com.uniamerica.carros.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniamerica.carros.app.entity.Marca;
import com.uniamerica.carros.app.repository.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository marcaRepository;

	public String save(Marca marca) {

		this.marcaRepository.save(marca);
		return marca.getNome() + " salvo com sucesso";
	}

	public List<Marca> listAll() {
		return this.marcaRepository.findAll();

	}

	public Marca findById(long idMarca) {
		Marca marca = this.marcaRepository.findById(idMarca).get();
		return marca;
	}

	public String delete(long idMarca) {
		this.marcaRepository.deleteById(idMarca);
		return "Marca deletado com sucesso!";
	}
}
