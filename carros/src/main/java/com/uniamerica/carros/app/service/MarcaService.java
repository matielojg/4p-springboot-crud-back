package com.uniamerica.carros.app.service;

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

}
