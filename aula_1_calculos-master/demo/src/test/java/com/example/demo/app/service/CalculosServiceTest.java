package com.example.demo.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;// Importa o método assertEquals para fazer as comparações nos testes

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.entity.Entrada;
import app.entity.Resultado;
import app.service.CalculosService;

public class CalculosServiceTest {

	CalculosService calculosService;

	@BeforeEach
	public void setUp() {
		// inicializa o objeto calculosService antes de cada teste
		calculosService = new CalculosService();
	}

	@Test
	@DisplayName("Teste unitário - cenário de soma = 10")
	public void testCalcular() {
		List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);

		Entrada entrada = new Entrada();
		entrada.setLista(lista);

		Resultado resultado = calculosService.calcular(entrada);

		int somaEsperada = 15; // 1 + 2 + 3 + 4 + 5 = 15
		double mediaEsperada = 3.0; // 15 / 5 = 3.0

		// verifica se a soma calculada e igual a esperada
		assertEquals(somaEsperada, resultado.getSoma(), "A soma calculada não está correta");

		// verifica se a mddia calculada é igual a esperada
		assertEquals(mediaEsperada, resultado.getMedia(), "A média calculada não está correta");
	}

	@Test
	@DisplayName("Teste unitário - cenário de erro")
	void cenario02() {

		List<Integer> lista = new ArrayList<>();
		lista.add(4);
		lista.add(4);
		lista.add(null);

		assertThrows(Exception.class, () -> {
			int soma = calculosService.somar(lista);
		});

	}

}