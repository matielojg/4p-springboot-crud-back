package com.uniamerica.carros.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniamerica.carros.app.entity.Acessorio;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {
	
}