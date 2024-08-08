package com.uniamerica.carros.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniamerica.carros.app.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

	
}
