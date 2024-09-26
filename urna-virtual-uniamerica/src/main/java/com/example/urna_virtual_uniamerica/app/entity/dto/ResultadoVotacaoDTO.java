package com.example.urna_virtual_uniamerica.app.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultadoVotacaoDTO {
    private String nomeCandidato;
    private String funcao;
    private int numero;
    private Integer numeroVotos;

}
