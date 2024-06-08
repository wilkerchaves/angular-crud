package br.dto;

import org.springframework.stereotype.Component;

import br.entities.Produto;

@Component
public class ProdutoMapper {

	public Produto toProduto(ProdutoRequestDTO dto) {
		Produto produto = new Produto();
		produto.setNome(dto.nome());
		produto.setValor(dto.valor());
		return produto;
	}
	
	public ProdutoDTO toDto (Produto produto) {
		return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getValor());
	}
}
