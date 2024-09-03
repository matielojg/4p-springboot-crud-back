package com.uniamerica.livraria.app.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "A observação da venda é obrigatória")
	private String observacao;

	@ManyToOne(optional = false) // Cliente sempre presente
	private Cliente cliente;

	@ManyToOne(optional = true) // Funcionário é opcional
	private Funcionario funcionario;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@NotEmpty(message = "A lista de produtos não pode estar vazia")
//	private List<Produto> produtos;
//	
	@ManyToMany
	@NotEmpty(message = "A lista de produtos não pode estar vazia")
	@JoinTable(name = "venda_produtos")
	private List<Produto> produtos;

	private BigDecimal valorTotal;

}
