package com.example.urna_virtual_uniamerica.app.entity;

import lombok.Builder;
import java.util.List;

@Builder
public record Apuracao(
    Integer totalVotos, 
    List<Candidato> candidatosPrefeito, 
    List<Candidato> candidatosVereador
) {}
