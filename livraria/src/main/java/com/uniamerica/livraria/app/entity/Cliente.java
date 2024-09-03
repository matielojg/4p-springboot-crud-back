package com.uniamerica.livraria.app.entity;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Pattern(regexp = "[a-zA-Z]+\\s[a-zA-Z]+", message = "O nome do cliente deve conter pelo menos duas palavras")
    private String nome;

    @Min(value = 0, message = "A idade não pode ser negativa")
    private int idade;

    @Email(message = "Email inválido")
    private String email;

    @CPF(message = "CPF inválido")    
    private String cpf;

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido")
    private String cep;

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone inválido")
    private String telefone;
}
