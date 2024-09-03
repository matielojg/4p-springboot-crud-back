package com.uniamerica.livraria.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniamerica.livraria.app.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}