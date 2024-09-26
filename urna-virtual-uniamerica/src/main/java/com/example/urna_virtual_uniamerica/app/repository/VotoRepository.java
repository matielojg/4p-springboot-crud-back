package com.example.urna_virtual_uniamerica.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.urna_virtual_uniamerica.app.entity.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
	

}
