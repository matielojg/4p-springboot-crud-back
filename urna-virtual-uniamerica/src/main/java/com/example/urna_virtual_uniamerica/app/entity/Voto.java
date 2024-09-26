package com.example.urna_virtual_uniamerica.app.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String dataHora; 
    
    @ManyToOne
    private Eleitor eleitor;
    
    @ManyToOne
    private Candidato candidatoPrefeito; 
    
    @ManyToOne
    private Candidato candidatoVereador; 
    
    private String hashComprovante;
    
 // Construtor que ignora o id, dataHora e hashComprovante
    public Voto(Eleitor eleitor, Candidato candidatoPrefeito, Candidato candidatoVereador) {
        this.eleitor = eleitor;
        this.candidatoPrefeito = candidatoPrefeito;
        this.candidatoVereador = candidatoVereador;
        this.dataHora = LocalDateTime.now().toString();
        this.hashComprovante = UUID.randomUUID().toString();
    }
    
}
