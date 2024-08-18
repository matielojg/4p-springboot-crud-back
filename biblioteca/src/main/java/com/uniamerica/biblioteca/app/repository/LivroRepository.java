package com.uniamerica.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniamerica.biblioteca.app.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	
}