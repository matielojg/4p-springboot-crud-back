package com.uniamerica.livraria.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniamerica.livraria.app.entity.Cliente;
import com.uniamerica.livraria.app.entity.Produto;
import com.uniamerica.livraria.app.entity.Venda;
import com.uniamerica.livraria.app.repository.ClienteRepository;
import com.uniamerica.livraria.app.repository.ProdutoRepository;
import com.uniamerica.livraria.app.repository.VendaRepository;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Venda> listarTodas() {
		return vendaRepository.findAll();
	}

	public Optional<Venda> buscarPorId(Long id) {
		return vendaRepository.findById(id);
	}

	public void deletarVenda(Long id) {
		vendaRepository.deleteById(id);
	}

	public Venda salvarVenda(Venda venda) {
		// Recupera e valida os produtos da venda
		List<Produto> produtos = recuperarEValidarProdutos(venda.getProdutos());

		// Calcula o valor total da venda
		BigDecimal valorTotal = calcularValorTotal(produtos);

		// Recupera e valida o cliente da venda
		Cliente clienteRecuperado = validarCliente(venda.getCliente().getId());

		// Verifica as restrições de negócio
		verificarRestricoesDeNegocio(clienteRecuperado, valorTotal);

		// Atualiza a venda com os produtos e valor total calculado
		venda.setProdutos(produtos);
		venda.setValorTotal(valorTotal);

		// Salva a venda no repositório
		return vendaRepository.save(venda);
	}

	private List<Produto> recuperarEValidarProdutos(List<Produto> produtos) {
		List<Produto> produtosValidados = new ArrayList<>();
		for (Produto produto : produtos) {
			Produto produtoExistente = produtoRepository.findById(produto.getId())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + produto.getId()));
			produtosValidados.add(produtoExistente);
		}
		return produtosValidados;
	}

	private BigDecimal calcularValorTotal(List<Produto> produtos) {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (Produto produto : produtos) {
			valorTotal = valorTotal.add(produto.getPreco());
		}
		return valorTotal;
	}

	private Cliente validarCliente(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado: ID " + clienteId));
	}

	private void verificarRestricoesDeNegocio(Cliente cliente, BigDecimal valorTotal) {
		if (cliente.getIdade() < 18 && valorTotal.compareTo(new BigDecimal("500")) > 0) {
			throw new RuntimeException("O valor total não pode exceder R$ 500 para clientes menores de 18 anos.");
		}
	}

}