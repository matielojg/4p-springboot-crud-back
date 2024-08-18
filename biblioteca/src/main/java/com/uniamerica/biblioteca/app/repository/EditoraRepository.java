package com.uniamerica.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniamerica.biblioteca.app.entity.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
	
}