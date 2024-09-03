package com.uniamerica.livraria.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniamerica.livraria.app.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}