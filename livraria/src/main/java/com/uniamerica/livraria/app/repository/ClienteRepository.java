package com.uniamerica.livraria.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.uniamerica.livraria.app.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
