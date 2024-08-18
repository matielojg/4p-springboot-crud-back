package com.uniamerica.biblioteca.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniamerica.biblioteca.app.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
