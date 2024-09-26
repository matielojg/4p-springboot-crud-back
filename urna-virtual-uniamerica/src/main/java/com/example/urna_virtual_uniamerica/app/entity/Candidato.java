package com.example.urna_virtual_uniamerica.app.entity;

import com.example.urna_virtual_uniamerica.app.entity.enums.FuncaoCandidato;
import com.example.urna_virtual_uniamerica.app.entity.enums.StatusCandidato;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Candidato extends Pessoa {
    private Integer numero;
    
    @Enumerated(EnumType.STRING)
    private FuncaoCandidato funcao; // 0 para prefeito, 1 para vereador
    
    @Enumerated(EnumType.STRING)  
    private StatusCandidato status;  
    @Transient
    private Integer votosApurados;
}
