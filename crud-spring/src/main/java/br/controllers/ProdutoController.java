package br.controllers;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.dto.ProdutoDTO;
import br.dto.ProdutoRequestDTO;
import br.entities.Produto;
import br.interfaces.CalculadoraJuros;
import br.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos() {
		return ResponseEntity.ok(produtoService.getAllProducts());
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoRequestDTO produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable(value = "id") Integer id,
			@RequestBody ProdutoRequestDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.updateProduto(id, dto));
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Integer id) {
		produtoService.deleteProduto(id);
	}
}
