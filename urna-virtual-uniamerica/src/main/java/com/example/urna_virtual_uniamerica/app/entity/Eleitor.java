package com.example.urna_virtual_uniamerica.app.entity;

import com.example.urna_virtual_uniamerica.app.entity.enums.EleitorStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Eleitor extends Pessoa {
    private String profissaoEleitor;
    @Enumerated(EnumType.STRING)
    private EleitorStatus eleitorStatus;
}
