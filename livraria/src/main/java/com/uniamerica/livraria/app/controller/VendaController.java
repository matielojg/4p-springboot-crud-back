package com.uniamerica.livraria.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniamerica.livraria.app.entity.Cliente;
import com.uniamerica.livraria.app.entity.Venda;
import com.uniamerica.livraria.app.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

	@Autowired
	private VendaService vendaService;

	@PostMapping
	public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
		Venda novaVenda = vendaService.salvarVenda(venda);
		return ResponseEntity.ok(novaVenda);
	}

	@GetMapping
	public ResponseEntity<List<Venda>> listarTodas() {
		List<Venda> vendas = vendaService.listarTodas();
		return ResponseEntity.ok(vendas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
		Optional<Venda> venda = vendaService.buscarPorId(id);
		if (venda.isPresent()) {
			return ResponseEntity.ok(venda.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
		vendaService.deletarVenda(id);
		return ResponseEntity.noContent().build();
	}

}