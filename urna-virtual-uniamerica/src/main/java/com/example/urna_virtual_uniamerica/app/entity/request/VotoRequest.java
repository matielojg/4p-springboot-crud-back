package com.example.urna_virtual_uniamerica.app.entity.request;

import lombok.Data;

@Data
public class VotoRequest {
    private Long eleitorId;
    private Long candidatoPrefeitoId;
    private Long candidatoVereadorId;
    
}
