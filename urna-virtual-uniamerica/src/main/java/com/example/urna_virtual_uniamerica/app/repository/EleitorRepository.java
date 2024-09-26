package com.example.urna_virtual_uniamerica.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.urna_virtual_uniamerica.app.entity.Eleitor;
import com.example.urna_virtual_uniamerica.app.entity.enums.EleitorStatus;

@Repository
public interface EleitorRepository extends JpaRepository<Eleitor, Long> {
    
    // buscar todos os eleitores com status APTO || PENDENTE
    List<Eleitor> findByEleitorStatusIn(List<EleitorStatus> status);
}